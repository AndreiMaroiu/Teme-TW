public class Main
{
    public static void main(String[] args)
    {
        String hostname = "localhost";
        int port = 8080;

        Client client = new Client(hostname, port);
        client.execute();
    }
}
