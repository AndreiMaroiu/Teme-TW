package Servlets;

import DAO.DateValidator;
import DAO.User;
import DAO.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SignUp", value = "/SignUp")
public class SignUp extends HttpServlet
{
    private HttpSession session;
    private HttpServletResponse response;
    private HttpServletRequest request;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPass = request.getParameter("confirmPass");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String date = request.getParameter("date");

        this.session = request.getSession();
        this.response = response;
        this.request = request;

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setAddress(address);
        user.setBirthday(DateValidator.GetDate(date));

        if (isNullOrEmpty(username))
        {
            reloadPage("Enter a valid username!", user);
            return;
        }

        if (isNullOrEmpty(password))
        {
            reloadPage("Enter a valid password!", user);
            return;
        }

        if (isNullOrEmpty(confirmPass) || !confirmPass.equals(password))
        {
            reloadPage("Password does not match!", user);
            return;
        }

        DateValidator validator = new DateValidator();
        if (!validator.validateDate(user.getBirthday()))
        {
            reloadPage(validator.getOutMessage(), user);
            return;
        }

        boolean updatedSuccessful = UserDao.addUser(user);
        if (updatedSuccessful)
        {
            session.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Details.jsp");
            dispatcher.forward(request, response);
        }
        else
        {
            reloadPage("Username already exists!", user);
        }
    }

    private void reloadPage(String message, User user) throws IOException, ServletException
    {
        session.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("SignUp.jsp");
        dispatcher.include(request, response);
        response.getWriter().println("<p class='warning'>" + message + "</p>");
    }

    private static boolean isNullOrEmpty(String str)
    {
        return str == null || str.isEmpty();
    }
}
