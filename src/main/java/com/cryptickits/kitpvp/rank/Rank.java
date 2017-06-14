package com.cryptickits.kitpvp.rank;

/**
 * Created by Matthew E on 6/14/2017.
 */
public class Rank {
    private String name;
    private String nextRank;
    private double cost;
    private String tag;

    public Rank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Rank setName(String name) {
        this.name = name;
        return this;
    }

    public String getNextRank() {
        return nextRank;
    }

    public Rank setNextRank(String nextRank) {
        this.nextRank = nextRank;
        return this;
    }

    public double getCost() {
        return cost;
    }

    public Rank setCost(double cost) {
        this.cost = cost;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public Rank setTag(String tag) {
        this.tag = tag;
        return this;
    }
}
