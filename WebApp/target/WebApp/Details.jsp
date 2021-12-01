<%--
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

    <p>Username: ${user.username}</p>

    <form action="Details">
        <p>Name: <input type="text" name="name" value="${user.name}"/></p>
        <p>Address: <input type="text" name="address" value="${user.address}"/></p>
        <p>Birthday: <input type="text" name="birthday" value="${user.birthday}"/></p>

        <input type="submit" value="Save"/>
    </form>

    <form action="Logout">
        <input type="submit" value="logout">
    </form>
</body>
</html>
