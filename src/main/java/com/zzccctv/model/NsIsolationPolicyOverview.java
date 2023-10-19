package com.zzccctv.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class NsIsolationPolicyOverview extends AbstractOverview{
    private List<String> clusters;

    public NsIsolationPolicyOverview() {
        clusters = new ArrayList<>();
    }

    public List<String> getClusters() {
        return clusters;
    }

    public void setClusters(List<String> clusters) {
        this.clusters = clusters;
    }
}
