import java.io.Serializable;

public class Response implements Serializable
{
    private String action = "";
    private String message = "";

    public Response() {}

    public String getAction()
    {
        return action;
    }

    public String getMessage()
    {
        return message;
    }
}