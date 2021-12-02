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
    private static final String MESSAGE = "signupmessage";

    private HttpSession session;
    private HttpServletResponse response;


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPass = request.getParameter("confirmPass");
        String name = request.getParameter("name");
        String address = request.getParameter("address");

        session = request.getSession();
        this.response = response;

        if (isNullOrEmpty(email))
        {
            reloadPage("Enter a valid email!");
            return;
        }

        if (isNullOrEmpty(password))
        {
            reloadPage("Enter a valid password!");
            return;
        }

        if (isNullOrEmpty(confirmPass) || !confirmPass.equals(password))
        {
            reloadPage("Password does not match!");
            return;
        }

        User user = new User();
        user.setPassword(password);

        Validate.addUser(user);

        response.sendRedirect("Details.jsp");
    }

    private void reloadPage(String message) throws IOException
    {
        session.setAttribute(MESSAGE, message);
        response.sendRedirect("SignUp.jsp");
    }

    private static boolean isNullOrEmpty(String str)
    {
        return str == null || str.isEmpty();
    }
}
