package com.zzccctv.model;

import java.util.*;

public class TopicsOverview extends AbstractOverview {
    private Set<String> topics;

    public TopicsOverview() {
        topics = new HashSet<>();
    }

    public Set<String> getTopics() {
        return topics;
    }

    public void addTopics(Set<String> topics) {
        this.topics.addAll(topics);
    }

}
