package net.ertha.erthabungeecord;

import java.io.*;
import java.net.Socket;

public class SocketServerConnection implements Runnable {
    private final ErthaBungeecord eb;
    private final Socket server;
    private BufferedReader in;
    private PrintWriter out;

    public SocketServerConnection(ErthaBungeecord erthaBungeecord, Socket clientSocket) {
        eb = erthaBungeecord;
        this.server = clientSocket;
        createClient();
    }

    private void createClient() {
        try {
            InputStream in1;
            in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            out = new PrintWriter(server.getOutputStream(), true);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void run() {
        String request = null;
        while (true) {
            try {
                request = in.readLine();
                eb.getLogger().info(request);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } finally {
                close();
            }
        }

    }

    public void close() {
        out.close();
        try {
            in.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        eb.getLogger().info("Closing socket");
    }
}
