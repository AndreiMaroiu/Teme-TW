<html>
    <head>
        <title>login form</title>

        <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
        <link rel="StyleSheet" href="${pageContext.request.contextPath}/Css/site.css" type="text/css"/>
    </head>
    <body>
            <h2 class="center-text">Login</h2>
            <form method="post" action="login">
                <table >
                    <tr>
                        <td>
                            Username:
                        </td>
                        <td>
                            <label>
                                <input type="text" name="email" required placeholder="username"
                                        oninvalid="this.setCustomValidity('Please enter a username!')"/>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Password:
                        </td>
                        <td>
                            <label>
                                <input type="password" name="pass" placeholder="password"/>
                            </label>
                        </td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <td>
                            <input class="button-center" type="submit" value="Login" name="button"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input class="button-center" type="submit" value="Sign Up" name="button"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input class="button-center" type="submit" value="Forgot Password" name="button"/>
                        </td>
                    </tr>
                </table>
            </form>

    </body>
</html>
