package net.ertha.erthabungeecord;

import net.ertha.packet.Packet;
import net.ertha.packet.PlayerStorage;

public class PacketHandler {
    public static String read(Packet packet, ErthaBungeecord erthaBungeecord){
        if(packet.hasPlayerStorage())
            if(erthaBungeecord.playerStorages.contains(packet.getPlayerStorage()) ){
                for (PlayerStorage player: erthaBungeecord.playerStorages){
                    if(player.equals(packet.getPlayerStorage()))
                        if(packet.getPlayerStorage().getFriends() != null) {
                            player.setFriends(packet.getPlayerStorage().getFriends());
                            break;
                        }
                }

            }else {
                erthaBungeecord.playerStorages.add(packet.getPlayerStorage());
            }

        return packet.getServerName();
    }
}
