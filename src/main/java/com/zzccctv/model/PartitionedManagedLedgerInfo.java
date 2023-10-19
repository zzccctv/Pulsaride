package com.zzccctv.model;

import com.google.common.collect.Maps;

import java.util.Map;

public class PartitionedManagedLedgerInfo extends ManagedLedgerInfo {

    public Map<String, ManagedLedgerInfo> partitions;

    public PartitionedManagedLedgerInfo() {
        partitions = Maps.newTreeMap();
    }
}
