package com.zzccctv.model;

import java.util.*;

public class BrokersOverview extends AbstractOverview {
    private Set<String> brokers;

    private String leaderBroker = "";

    public BrokersOverview() {
        brokers = new HashSet<>();
    }

    public Set<String> getBrokers() {
        return brokers;
    }

    public void addBrokers(List<String> brokers) {
        this.brokers.addAll(brokers);
    }

    public String getLeaderBroker() {
        return leaderBroker;
    }

    public void setLeaderBroker(String leaderBroker) {
        this.leaderBroker = leaderBroker;
    }
}
