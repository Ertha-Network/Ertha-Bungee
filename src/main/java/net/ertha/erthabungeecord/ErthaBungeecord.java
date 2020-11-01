package net.ertha.erthabungeecord;

import com.zaxxer.hikari.HikariDataSource;
import net.ertha.packet.PlayerStorage;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ErthaBungeecord extends Plugin {
    public Database db = new Database(this);
    public Friends friends = new Friends(this);
    public HikariDataSource hikari = db.connect();
    public SocketServer socketServer = new SocketServer(this);
    List<PlayerStorage> playerStorages = new ArrayList<>();

    @Override
    public void onEnable(){
        ProxyServer.getInstance().getPluginManager().registerCommand(this, friends);
        new Thread(socketServer).start();

    }

    @Override
    public void onDisable(){
        socketServer.closeAllConnections();
    }


}
