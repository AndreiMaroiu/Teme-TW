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

    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <link rel="StyleSheet" href="${pageContext.request.contextPath}/Css/site.css" type="text/css"/>
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
                <td>
                    <label>
                        <input type="password" name="password">
                    </label>
                </td>
            </tr>
            <tr>
                <td>Confirm password:</td>
                <td>
                    <label>
                        <input type="password" name="confirmPass">
                    </label>
                </td>
            </tr>
        </table>
        <table>
            <tr>
                <td>
                    <input type="submit" value="Confirm" name="button" class="round-button button-center"/>
                </td>
                <td>
                    <input type="submit" value="Cancel" name="button" class="round-button button-center"/>
                </td>
            </tr>
        </table>

    </form>
</body>
</html>
