package net.ertha.erthabungeecord;

import com.zaxxer.hikari.HikariDataSource;
import net.md_5.bungee.api.plugin.Plugin;

public class ErthaBungeecord extends Plugin{
    public EBDatabase db = new EBDatabase(this);
    public HikariDataSource hikari = db.connect();

    @Override
    public void onEnable(){


    }

    @Override
    public void onDisable(){

    }


}
