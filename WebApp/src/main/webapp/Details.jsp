<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.ParseException" %><%--
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

    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <link rel="StyleSheet" href="${pageContext.request.contextPath}/Css/site.css" type="text/css"/>
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
                <td>
                    <input type="date" name="date"
                           value="${user.birthdayFormated}"/>
                </td>

            </tr>
        </table>

        <table>
            <tr>
                <td>
                    <input type="submit" value="Save" class="button-center" name="button"/>
                </td>
                <td>
                    <input type="submit" value="Log out" class="button-center" name="button">
                </td>
            </tr>
        </table>

    </form>
</body>
</html>
