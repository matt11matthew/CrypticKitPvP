package com.cryptickits.kitpvp.rank;

import com.cryptickits.kitpvp.database.JdbcDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Matthew E on 6/14/2017.
 */
public class RankJdbcDao extends JdbcDao<Rank> {
    private static RankJdbcDao instance;

    public static RankJdbcDao getInstance() {
        if (instance == null) {
            instance = new RankJdbcDao();
        }
        return instance;
    }

    public RankJdbcDao() {
        super("cryptic_kitpvp_ranks");
        instance = this;
    }

    @Override
    public Rank create(Rank rank, boolean async) {
        executeUpdate("INSERT INTO cryptic_kitpvp_ranks (name, nextRank, cost, tag) VALUES(?,?,?,?)", async, rank.getName(), rank.getNextRank(), rank.getCost(), rank.getTag());
        return rank;
    }

    @Override
    public void delete(Rank rank, boolean async) {
        executeUpdate("DELETE FROM cryptic_kitpvp_ranks WHERE name=?", async, rank.getName());
    }

    @Override
    public Rank get(String key, boolean async) throws SQLException {
        ResultSet resultSet = executeQuery("SELECT * FROM cryptic_kitpvp_ranks WHERE name=?", async, key);
        if (resultSet.next()) {
            return new Rank(key)
                    .setCost(resultSet.getDouble("cost"))
                    .setNextRank(resultSet.getString("nextRank"))
                    .setTag(resultSet.getString("tag"));
        }
        return null;
    }

    @Override
    public void save(Rank rank, boolean async) {
        executeUpdate("UPDATE cryptic_kitpvp_ranks SET cost=? WHERE name=?", async, rank.getCost(), rank.getName());
        executeUpdate("UPDATE cryptic_kitpvp_ranks SET nextRank=? WHERE name=?", async, rank.getNextRank(), rank.getName());
        executeUpdate("UPDATE cryptic_kitpvp_ranks SET tag=? WHERE name=?", async, rank.getTag(), rank.getName());
    }
}
