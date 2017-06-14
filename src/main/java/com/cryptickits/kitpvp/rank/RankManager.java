package com.cryptickits.kitpvp.rank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Matthew E on 6/14/2017.
 */
public class RankManager {
    private static RankManager instance;
    private Map<String, Rank> rankMap;

    public static RankManager getInstance() {
        if (instance == null) {
            instance = new RankManager();
        }
        return instance;
    }

    public RankManager() {
        instance = this;
        this.rankMap = new ConcurrentHashMap<>();
        List<String> rankNameStringList = new ArrayList<>();
        RankJdbcDao instance = RankJdbcDao.getInstance();
        ResultSet resultSet = instance.executeQuery("SELECT * FROM cryptic_kitpvp_ranks", true);
        try {
            while (resultSet.next()) {
                rankNameStringList.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (String rankName : rankNameStringList) {
            try {
                Rank rank = instance.get(rankName, true);
                if (rank != null) {
                    this.rankMap.put(rank.getName(), rank);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
