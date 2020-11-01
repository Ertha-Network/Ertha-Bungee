package net.ertha.erthabungeecord;

import java.io.*;
import java.net.Socket;

import net.ertha.packet.*;

public class SocketServerConnection implements Runnable {
    private final ErthaBungeecord eb;
    private final Socket server;
    private String ServerName = new String("");
    private BufferedReader in;
    private ObjectInputStream objectIn;
    private PrintWriter out;
    private ObjectOutputStream objectOut;

    public void setServerName(String server){
        this.ServerName = server;
    }

    public String getServerName(){
        return this.ServerName;
    }

    public SocketServerConnection(ErthaBungeecord erthaBungeecord, Socket clientSocket) {
        eb = erthaBungeecord;
        this.server = clientSocket;
        createClient();
    }

    private void createClient() {
        try {
            InputStream in1;
            in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            objectIn = new ObjectInputStream(server.getInputStream());
            out = new PrintWriter(server.getOutputStream(), true);
            objectOut = new ObjectOutputStream(server.getOutputStream());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void run() {
        String request = null;
        while (true) {
            readResponsePacket();

            if(!server.isConnected())close();
        }

    }

    public void readResponsePacket(){
        try {
            setServerName(
                    PacketHandler.read(
                            (Packet) objectIn.readObject(),
                            eb
                    )
            );
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
            readResponseLine();
        }
    }

    private void readResponseLine() {
        try {
            String request = in.readLine();
            eb.getLogger().info(request);
            if(request.equals("ping")){
                out.write("pong");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
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
