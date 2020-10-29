package net.ertha.erthabungeecord;

import com.zaxxer.hikari.HikariDataSource;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class ErthaBungeecord extends Plugin {
    public Database db = new Database(this);
    //public HikariDataSource hikari = db.connect();
    public SocketServer socketServer = new SocketServer(this);

    @Override
    public void onEnable(){
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Commands(this));
        new Thread(socketServer).start();
    }

    @Override
    public void onDisable(){

    }


}
