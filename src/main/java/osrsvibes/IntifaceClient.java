package osrsvibes;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;

public class IntifaceClient
{
    private WebSocket socket;

    public void connect()
    {
        socket = HttpClient.newHttpClient()
                .newWebSocketBuilder()
                .buildAsync(
                        URI.create("ws://localhost:12345"),
                        new WebSocket.Listener() {}
                )
                .join();

        // Request server info (required)
        send("{"
                + "\"RequestServerInfo\":{"
                + "\"Id\":1,"
                + "\"ClientName\":\"OSRS Vibes\","
                + "\"MessageVersion\":3"
                + "}"
                + "}");

        // Start scanning for devices
        send("{"
                + "\"StartScanning\":{"
                + "\"Id\":2"
                + "}"
                + "}");

        System.out.println("Connected to Intiface Central");
    }

    public void vibrate(double strength)
    {
        if (socket == null)
            return;

        send("{"
                + "\"ScalarCmd\":{"
                + "\"Id\":3,"
                + "\"DeviceIndex\":0,"
                + "\"Scalars\":[{"
                + "\"ActuatorType\":\"Vibrate\","
                + "\"Scalar\":" + strength
                + "}]"
                + "}"
                + "}");
    }

    private void send(String json)
    {
        socket.sendText(json, true);
    }

    public void disconnect()
    {
        if (socket != null)
        {
            socket.abort();
            socket = null;
        }
    }
}
