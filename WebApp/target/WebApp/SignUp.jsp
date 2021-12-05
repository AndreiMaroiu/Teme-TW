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

    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <link rel="StyleSheet" href="${pageContext.request.contextPath}/Css/site.css" type="text/css"/>
</head>
<body>
    <h1>Sign Up</h1>

    <form action="SignUp" method="post">
        <table>
            <tr>
                <td>Username*:</td>
                <td>
                    <label>
                        <input type="text" name="username" required placeholder="username" value="${user.username}"
                               oninvalid="this.setCustomValidity('Please enter a username!')"/>
                    </label>
                </td>
            </tr>
            <tr>
                <td>Password*:</td>
                <td>
                    <label>
                        <input type="password" name="password" required placeholder="password"
                                oninvalid="this.setCustomValidity('Please enter a password!')"/>
                    </label>
                </td>
            </tr>
            <tr>
                <td>Confirm Password*:</td>
                <td>
                    <label>
                    <input type="password" name="confirmPass" required placeholder="confirm password"
                                oninvalid="this.setCustomValidity('Please reenter password!')"/>
                    </label>
                </td>
            </tr>
            <tr>
                <td>Name:</td>
                <td>
                    <label>
                        <input type="text" name="name" placeholder="name" value="${user.name}"/>
                    </label>
                </td>
            </tr>
            <tr>
                <td>Address:</td>
                <td>
                    <label>
                        <input type="text" name="address" placeholder="address" value="${user.address}"/>
                    </label>
                </td>
            </tr>
            <tr>
                <td>Birthday:</td>
                <td>
                    <label>
                        <input type="date" name="date" value="${user.birthdayFormated}">
                    </label>
                </td>
            </tr>
        </table>
        <p>Every field marked with * is obligatory!</p>
        <br/>
        <input type="submit" value="Sign Up" class="button-center round-button"/>
    </form>
    <form action="Logout">
        <input type="submit" value="Cancel" class="button-center round-button"/>
    </form>
</body>
</html>
