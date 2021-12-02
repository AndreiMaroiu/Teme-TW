<html>
    <head>
        <title>login form</title>
    </head>
    <body>
        <h2>Login</h2>
        <form method="post" action="login">
            <table>
                <tr>
                    <td>
                        Username:
                    </td>
                    <td>
                        <input type="text" name="email"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Password:
                    </td>
                    <td>
                        <input type="password" name="pass"/>
                    </td>
                </tr>
            </table>
            <input type="submit" value="Login" name="button"/>
            <input type="submit" value="Forgot Password" name="button"/>
        </form>

        <form action="RedirectSignUp">
            <input type="submit" value="Sign Up"/>
        </form>
    </body>
</html>
