package Responses;

public class WriteResponse extends Response
{
    public WriteResponse(String message)
    {
        super("write", message);
    }
}
