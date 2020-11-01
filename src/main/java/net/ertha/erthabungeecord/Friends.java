package net.ertha.erthabungeecord;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Friends extends Command {

    private final ErthaBungeecord eb;
    private final Map<UUID, PlayerFriends> friends = new HashMap<>();

    Friends(ErthaBungeecord erthaBungeecord) {
        super("friend");
        eb = erthaBungeecord;
    }

    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            getFriendsList(player);
            if (strings.length > 2) {
                switch (strings[0].toLowerCase()) {
                    case "add":
                        add(player, strings);
                        break;
                    case "remove":
                    case "del":
                        del(player, strings);
                        break;
                    default:
                        gui(player);
                        break;
                }
            }
        }
    }

    void getFriendsList(ProxiedPlayer player) {
        // add check database
        if (!friends.containsKey(player.getUniqueId())) {
            friends.put(player.getUniqueId(), new PlayerFriends(player));
        }
    }


    private void add(ProxiedPlayer player, String[] strings) {
        ProxiedPlayer friend = BungeeCord.getInstance().getPlayer(strings[1]);
        if(friends.get(player.getUniqueId()).Contains(strings[1])){
            player.sendMessage(new TextComponent(ChatColor.RED+"You cannot add "+strings[1]+" again!"));
            return;
        }
        if(friend == null){
            player.sendMessage(new TextComponent(ChatColor.RED+"You cannot add "+strings[1]+" because this person isn't online!"));
        }else {
            friends.get(player.getUniqueId()).add(friend);
        }

    }

    private void del(ProxiedPlayer player, String[] strings) {
        if(friends.get(player.getUniqueId()).del(strings[1])){
            player.sendMessage(new TextComponent(ChatColor.RED+strings[1]+" has been deleted form your list!"));
        }
    }

    private void gui(ProxiedPlayer player) {
        //add call over socket to server to load gui.
    }
}
