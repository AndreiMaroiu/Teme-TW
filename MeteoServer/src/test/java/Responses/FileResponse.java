package Responses;

public class FileResponse extends Response
{
    public FileResponse(String message)
    {
        super("readFile", message);
    }
}
