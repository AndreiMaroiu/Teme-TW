<%@ page import="java.util.Calendar" %><%--
  Created by IntelliJ IDEA.
  User: Achi
  Date: 28-Nov-21
  Time: 11:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Details</title>
</head>
<body>
    <%
        if (session.getAttribute("user") == null)
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
            return;
        }
    %>
    <h2>My Details</h2>

    <form method="post" action="Details">
        <table>
            <tr>
                <td>Username:</td>
                <Td>${user.username}</Td>
            </tr>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" value="${user.name}"/></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><input type="text" name="address" value="${user.address}"/></td>
            </tr>
            <tr>
                <td>Birthday:</td>
                <td><input type="text" name="day" value="${user.birthday.date}"/></td>
                <td>
                    <input type="text" name="month" value="${(user.birthday != null) ? user.birthday.month + 1 : null}"/>
                </td>
                <td>
                    <input type="text" name="year" value="${(user.birthday != null) ? user.birthday.year + 1900 : null}"/>
                </td>
            </tr>
        </table>

        <input type="submit" value="Save"/>
    </form>

    <form action="Logout">
        <input type="submit" value="logout">
    </form>
</body>
</html>
