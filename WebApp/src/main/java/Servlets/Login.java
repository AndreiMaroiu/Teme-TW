package Servlets;

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

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String button = request.getParameter("button");

        switch (button)
        {
        case "Login":
            login(request, response);
            break;
        case "Forgot Password":
            redirectPassword(request, response);
            break;
        case "Sign Up":
            redirectSignUp(request, response);
            break;
        }
    }

    private void redirectSignUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher("SignUp.jsp");
        dispatcher.forward(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String email = request.getParameter("username");
        String pass = request.getParameter("pass");

        User user = UserDao.getUser(email, pass);

        if (user != null)
        {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/Details.jsp");
            dispatcher.forward(request, response);
        }
        else
        {
            reloadPage(request, response, "Username or Password incorrect");
        }
    }

    private void redirectPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");

        if (username == null || username.isEmpty())
        {
            reloadPage(request, response, "Enter a username!");
        }
        else if (UserDao.findUserByName(username) == null)
        {
            reloadPage(request, response, "Enter a valid username!");
        }
        else
        {
            session.setAttribute("username", username);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ForgotPassword.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void reloadPage(HttpServletRequest request, HttpServletResponse response, String message) throws IOException, ServletException
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.include(request, response);
        response.getWriter().println("<p class='warning'>" + message + "</p>");
    }
}