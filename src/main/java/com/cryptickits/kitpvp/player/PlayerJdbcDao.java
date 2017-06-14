package com.cryptickits.kitpvp.player;

import com.cryptickits.kitpvp.database.JdbcDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by Matthew E on 6/14/2017.
 */
public class PlayerJdbcDao extends JdbcDao<CrypticPlayer> {

    private static PlayerJdbcDao instance;

    public static PlayerJdbcDao getInstance() {
        if (instance == null) {
            instance = new PlayerJdbcDao();
        }
        return instance;
    }

    public PlayerJdbcDao() {
        super("cryptic_players");
        instance = this;
    }

    @Override
    public CrypticPlayer create(CrypticPlayer crypticPlayer, boolean async) {
        executeUpdate("INSERT cryptic_players (uuid, username, server, ip_address) VALUES(?,?,?,?)", async, crypticPlayer.getUniqueId().toString(), crypticPlayer.getUsername(), crypticPlayer.getServer(), crypticPlayer.getIpAddress());
        return crypticPlayer;
    }

    @Override
    public void delete(CrypticPlayer crypticPlayer, boolean async) {
        executeUpdate("DELETE FROM cryptic_players WHERE uuid=?", async, crypticPlayer.getUniqueId().toString());
    }

    @Override
    public CrypticPlayer get(String key, boolean async ) throws SQLException {
        ResultSet resultSet = executeQuery( "SELECT * FROM cryptic_players WHERE uuid=?", async,key);
        if (resultSet.next()) {
            return new CrypticPlayer(UUID.fromString(key), resultSet.getString("name"))
                    .setIpAddress(resultSet.getString("ip_address"))
                    .setServer(resultSet.getString("server"));
        }
        return null;
    }


    @Override
    public void save(CrypticPlayer crypticPlayer, boolean async) {
        executeUpdate("UPDATE cryptic_players SET ip_address=? WHERE uuid=?", async, crypticPlayer.getIpAddress(), crypticPlayer.getUniqueId().toString());
        executeUpdate("UPDATE cryptic_players SET server=? WHERE uuid=?", async, crypticPlayer.getServer(), crypticPlayer.getUniqueId().toString());
    }
}
