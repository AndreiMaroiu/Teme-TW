import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

@WebServlet("/Details")
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
        String day = request.getParameter("day");
        String month = request.getParameter("month");
        String year = request.getParameter("year");

        user.setName(name);
        user.setAddress(address);
        user.setBirthday(getDate(day, month, year));

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

    private Date getDate(String day, String month, String year)
    {
        try
        {
            int yearInt = Integer.parseInt(year);
            int monthInt = Integer.parseInt(month);
            int dayInt = Integer.parseInt(day);
            Date date = new GregorianCalendar(yearInt, monthInt - 1, dayInt).getTime();
            return date;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
