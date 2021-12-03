<%--
  Created by IntelliJ IDEA.
  User: Achi
  Date: 28-Nov-21
  Time: 3:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
    <h1>Sign Up</h1>

    <form action="SignUp" method="post">
        <table>
            <tr>
                <td>Username*:</td>
                <td><input type="text" name="username" required/></td>
            </tr>
            <tr>
                <td>Password*:</td>
                <td><input type="password" name="password" required/></td>
            </tr>
            <tr>
                <td>Confirm Password*:</td>
                <td><input type="password" name="confirmPass" required/></td>
            </tr>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name"/></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><input type="text" name="address"/></td>
            </tr>
            <tr>
                <td>Birthday:</td>
                <td><input type="date" name="date"></td>
            </tr>
        </table>

        <input type="submit" value="Sign Up"/>
    </form>

    <form action="Logout">
        <input type="submit" value="Cancel"/>
    </form>
</body>
</html>
