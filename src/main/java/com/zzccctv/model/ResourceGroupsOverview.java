package com.zzccctv.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class ResourceGroupsOverview extends AbstractOverview {
    public List<String> groups;

    public ResourceGroupsOverview() {
        groups = new ArrayList<>();

    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }
}
