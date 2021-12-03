import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "Details", value = "/Details")
public class Details extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
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
            response.getWriter().println(validator.getOutMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Details.jsp");
            dispatcher.include(request, response);
            return;
        }

        user.setBirthday(newDate);

        if (Validate.updateUser(user))
        {
            response.getWriter().println("Details updated successfully!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Details.jsp");
            dispatcher.include(request, response);
        }
        else
        {
            response.getWriter().println("Failed to update details!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Details.jsp");
            dispatcher.include(request, response);
        }
    }
}
