import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ForgotPassword", value = "/ForgotPassword")
public class ForgotPassword extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
//        response.setContentType("text/html;charset=UTF-8");

        switch (request.getParameter("button"))
        {
        case "Cancel":
            cancel(request, response);
            break;
        case "Confirm":
            confirm(request, response);
            break;
        }
    }

    private void confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        String password = request.getParameter("password");
        String confirmPass = request.getParameter("confirmPass");

        if (password != null && password.equals(confirmPass))
        {
            User user = Validate.findUserByName((String)session.getAttribute("username"));
            user.setPassword(password);
            Validate.updateUser(user);
            session.setAttribute("user", user);
            session.removeAttribute("username");

            RequestDispatcher dispatcher = request.getRequestDispatcher("Details.jsp");
            dispatcher.forward(request, response);
        }
        else
        {
            reloadPage(request, response, "Password does not match!");
        }
    }

    private void reloadPage(HttpServletRequest request, HttpServletResponse response, String message) throws IOException, ServletException
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher("ForgotPassword.jsp");
        dispatcher.include(request, response);
        response.getWriter().println("<p class='warning'>" + message + "</p>");
    }

    private void cancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        session.invalidate();

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
