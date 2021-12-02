import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

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
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        User user = Validate.getUser(email, pass);

        if (user != null)
        {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/Details.jsp");
            dispatcher.forward(request, response);
        }
        else
        {
            out.println("Username or Password incorrect");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.include(request, response);
        }
    }

    private void redirectPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String username = request.getParameter("email");

        if (username == null || username.isEmpty())
        {
            response.getWriter().println("enter a username!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.include(request, response);
        }
        else if (Validate.findUserByName(username) == null)
        {
            response.getWriter().println("enter a valid username!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.include(request, response);
        }
        else
        {
            session.setAttribute("username", username);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ForgotPassword.jsp");
            dispatcher.forward(request, response);
        }
    }
}