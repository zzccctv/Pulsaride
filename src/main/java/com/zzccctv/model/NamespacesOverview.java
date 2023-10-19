package com.zzccctv.model;

import java.util.*;

public class NamespacesOverview extends AbstractOverview {
    private Set<String> namespaces;

    public NamespacesOverview() {
        namespaces = new HashSet<>();
    }


    public Set<String> getNamespaces() {
        return namespaces;
    }

    public void addNamespaces(Set<String> namespaces) {
        this.namespaces.addAll(namespaces);
    }
}
