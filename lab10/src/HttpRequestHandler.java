import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class HttpRequestHandler implements Runnable {
    private Socket socket;

    public HttpRequestHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            while (true) {
                InputStream is = socket.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(is));
                String entry = in.readLine();
                System.out.println("Receive: " + entry);
                Server.displayMessage(entry + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}