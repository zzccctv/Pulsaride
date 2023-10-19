package com.zzccctv.model;

import java.util.*;

public class BrokerStatsOverview extends AbstractOverview {
    private Set<String> brokers;

    public BrokerStatsOverview() {
        brokers = new HashSet<>();
    }

    public Set<String> getBrokers() {
        return brokers;
    }

    public void addBrokers(List<String> data) {
        brokers.addAll(data);
    }

}
