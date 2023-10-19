package com.zzccctv.model;

import java.util.*;

public class TenantsOverview extends AbstractOverview{
    private Set<String> tenants;
    public TenantsOverview() {
        tenants = new HashSet<>();
    }

    public Set<String> getTenants() {
        return tenants;
    }

    public void setTenants(Set<String> tenants) {
        this.tenants = tenants;
    }
}
