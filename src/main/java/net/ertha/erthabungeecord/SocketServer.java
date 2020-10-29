package net.ertha.erthabungeecord;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.scheduler.TaskScheduler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer implements Runnable {

    ErthaBungeecord eb;

    private final ArrayList<SocketServerConnection> clients = new ArrayList<>();
    private final ExecutorService pool = Executors.newFixedThreadPool(100);

    public SocketServer(ErthaBungeecord erthaBungeecord) {
        eb = erthaBungeecord;
    }


    @Override
    public void run() {
        try {
            ServerSocket listener = new ServerSocket(25530);
            while (true) {
                eb.getLogger().info("Waiting for Socket connection");
                Socket client = listener.accept();
                eb.getLogger().info("Creating new Socket connection");
                SocketServerConnection clientThread = new SocketServerConnection(eb, client);
                eb.getLogger().info("Created new Socket connection");
                clients.add(clientThread);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
