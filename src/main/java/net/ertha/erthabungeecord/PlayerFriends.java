package net.ertha.erthabungeecord;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class PlayerFriends {
    public final String player;
    private Map<UUID, String> friends = new HashMap<>();

    PlayerFriends(ProxiedPlayer proxiedPlayer) {
        this.player = proxiedPlayer.getName();
    }

    Boolean Contains(String friend) {
        return friends.containsValue(friend);
    }

    Boolean Contains(UUID friend) {
        return friends.containsKey(friend);
    }

    void add(ProxiedPlayer friend) {
        friends.put(friend.getUniqueId(), friend.getName());
    }

    Boolean del(String friend) {
        AtomicReference<Boolean> deleted = new AtomicReference<>(false);
        friends.forEach((k,v) -> {
            if(friend.equalsIgnoreCase(v)){
                friends.remove(k);
                deleted.set(true);
            }
        });
        return deleted.get();
    }

    Collection<String> get() {
        return friends.values();
    }


}
