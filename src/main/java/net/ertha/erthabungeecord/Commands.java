package net.ertha.erthabungeecord;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class Commands extends Command {
    private ErthaBungeecord eb;

    public Commands(ErthaBungeecord erthaBungeecord)  {
        super("pingpong");
        eb = erthaBungeecord;
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        //eb.pm.sendToBukkit("ping", eb.getProxy().getServerInfo("TEST1"));
    }
}
