<%--
  Created by IntelliJ IDEA.
  User: Achi
  Date: 01-Dec-21
  Time: 6:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page  %>--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        if (session.getAttribute("username") == null)
        {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    %>

    <h1>Forgot Password!</h1>

    <form method="post" action="ForgotPassword">
        <table>
            <tr>
                <td>Username:</td>
                <td>${username}</td>
            </tr>
            <tr>
                <td>New password:</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>Confirm password:</td>
                <td><input type="password" name="confirmPass"></td>
            </tr>
        </table>
        <input type="submit" value="Confirm" name="button"/>
        <input type="submit" value="Cancel" name="button"/>
    </form>
</body>
</html>
