package com.cryptickits.kitpvp.player;

import java.util.UUID;

/**
 * Created by Matthew E on 6/14/2017.
 */
public class CrypticPlayer {
    private UUID uniqueId;
    private String username;
    private String ipAddress;
    private String server;

    public CrypticPlayer(UUID uniqueId, String username) {
        this.uniqueId = uniqueId;
        this.username = username;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public String getUsername() {
        return username;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public CrypticPlayer setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public String getServer() {
        return server;
    }

    public CrypticPlayer setServer(String server) {
        this.server = server;
        return this;
    }
}
