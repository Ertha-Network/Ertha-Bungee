package net.ertha.erthabungeecord;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Collection;

public class EBPluginMessages {

    private final ErthaBungeecord eb;
    private

    EBPluginMessages(ErthaBungeecord erthaBungeecord){
        eb = erthaBungeecord;
    }

    @Override
    public void onPluginMessageReceived(String channel, ProxiedPlayer player, byte[] message){
        if(!channel.equals("ertha:bungee"))return;
        ByteArrayDataInput input = ByteStreams.newDataInput(message);
        String subChannel = input.readUTF();
        String data = input.readUTF();
    }

    public void sendData(ProxiedPlayer player, String channel, String subChannel, String data)
    {
        Collection<ProxiedPlayer> networkPlayers = ProxyServer.getInstance().getPlayers();
        // perform a check to see if globally are no players
        if ( networkPlayers == null || networkPlayers.isEmpty() )
        {
            return;
        }
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF( subChannel ); // the channel could be whatever you want
        out.writeUTF( data ); // this data could be whatever you want

        // we send the data to the server
        // using ServerInfo the packet is being queued if there are no players in the server
        // using only the server to send data the packet will be lost if no players are in it
        player.getServer().getInfo().sendData( channel, out.toByteArray() );
    }



}
