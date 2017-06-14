package com.cryptickits.kitpvp.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Matthew E on 6/14/2017.
 */
public class CrypticPlayerManager implements Listener {
    private static CrypticPlayerManager instance;
    private Map<UUID, CrypticPlayer> crypticPlayerMap;

    public static CrypticPlayerManager getInstance() {
        if (instance == null) {
            instance = new CrypticPlayerManager();
        }
        return instance;
    }

    public CrypticPlayerManager() {
        instance = this;
        this.crypticPlayerMap = new ConcurrentHashMap<>();
    }

    public CrypticPlayer getCrypticPlayer(UUID uuid) {
        return crypticPlayerMap.get(uuid);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Player player = event.getPlayer();
        try {
            PlayerJdbcDao instance = PlayerJdbcDao.getInstance();
            CrypticPlayer crypticPlayer = instance.get(player.getUniqueId().toString(), true);
            if (crypticPlayer == null) {
                System.out.println("Player null creating player...");
                CrypticPlayer player1 = instance.create(new CrypticPlayer(player.getUniqueId(), player.getName()).setIpAddress(player.getAddress().getHostName()).setServer(Bukkit.getServer().getServerName()), true);
                this.crypticPlayerMap.put(player.getUniqueId(), player1);
                System.out.println("Created player " + player1.getUniqueId().toString());
            } else {
                System.out.println("Found player " + crypticPlayer.getUniqueId().toString());
                this.crypticPlayerMap.put(crypticPlayer.getUniqueId(), crypticPlayer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
           player.kickPlayer(ChatColor.RED + ChatColor.BOLD.toString() + "PLAYER DATA ERROR\n" + ChatColor.GRAY + "Please report this to an admin");
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(null);
        if (this.crypticPlayerMap.containsKey(player.getUniqueId())) {
            System.out.println("Saving player " + player.getUniqueId().toString() + "...");
            PlayerJdbcDao.getInstance().save(this.crypticPlayerMap.get(player.getUniqueId()), true);
            this.crypticPlayerMap.remove(player.getUniqueId());
        }
    }
}
