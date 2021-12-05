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
import java.util.Date;

@WebServlet(name = "Details", value = "/Details")
public class Details extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        switch (request.getParameter("button"))
        {
        case "Save":
            updateDetails(request, response);
            break;
        case "Log out":
            new Logout().doGet(request, response);
            break;
        }
    }

    private void updateDetails(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String day = request.getParameter("date");

        user.setName(name);
        user.setAddress(address);

        Date newDate = DateValidator.GetDate(day);
        DateValidator validator = new DateValidator();
        if (!validator.validateDate(newDate))
        {
            reload(request, response, validator.getOutMessage());
            return;
        }

        user.setBirthday(newDate);

        if (UserDao.updateUser(user))
        {
            reload(request, response, "Details updated successfully!");
        }
        else
        {
            reload(request, response, "Failed to update details!");
        }
    }

    private void reload(HttpServletRequest request, HttpServletResponse response, String s) throws ServletException, IOException
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Details.jsp");
        dispatcher.include(request, response);
        response.getWriter().println("<p>" + s + "</p>");
    }
}
