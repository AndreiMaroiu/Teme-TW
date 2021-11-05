package Responses;

import java.io.Serializable;

public class Response implements Serializable
{
    public static final Response Empty = new Response("", "");

    private String action;
    private String message;

    public Response() {}

    public Response(String action, String message)
    {
        this.action = action;
        this.message = message;
    }

    public String getAction()
    {
        return action;
    }

    public String getMessage()
    {
        return message;
    }
}
