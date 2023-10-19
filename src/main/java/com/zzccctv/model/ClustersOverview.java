package com.zzccctv.model;

import java.util.*;

public class ClustersOverview extends AbstractOverview {

    private Set<String> clusters;

    public ClustersOverview() {
        clusters = new HashSet<>();
    }

    public Set<String> getClusters() {
        return clusters;
    }

    public void setClusters(Set<String> clusters) {
        this.clusters = clusters;
    }
}
