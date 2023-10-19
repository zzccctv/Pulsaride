package com.zzccctv.common;

import java.util.ArrayList;
import java.util.HashMap;

public class PulsarManagerTopicStats {
    /**
     * Total rate of messages published on the topic. msg/s
     */
    private double msgRateIn;

    /**
     * Total throughput of messages published on the topic. byte/s
     */
    private double msgThroughputIn;

    /**
     * Total rate of messages dispatched for the topic. msg/s
     */
    private double msgRateOut;

    /**
     * Total throughput of messages dispatched for the topic. byte/s
     */
    private double msgThroughputOut;

    /**
     * Average size of published messages. bytes
     */
    private double averageMsgSize;

    /**
     * Space used to store the messages for the topic. bytes
     */
    private long storageSize;

    private int pendingAddEntriesCount;

    /**
     * List of connected publishers on this topic w/ their stats
     */
    private ArrayList<PulsarManagerPublisherStats> publishers;

    /**
     * Map of subscriptions with their individual statistics
     */
    private HashMap<String, PulsarManagerSubscriptionStats> subscriptions;

    /**
     * Map of replication statistics by remote cluster context
     */
    private HashMap<String, PulsarManagerReplicatorStats> replication;

    private String deduplicationStatus;

    private long producerCount;

    private long msgInCount;
    private long bytesInCount;
    private long msgOutCount;
    private long bytesOutCount;

    private long backlogSize;

    public double getMsgRateIn() {
        return msgRateIn;
    }

    public void setMsgRateIn(double msgRateIn) {
        this.msgRateIn = msgRateIn;
    }

    public double getMsgThroughputIn() {
        return msgThroughputIn;
    }

    public void setMsgThroughputIn(double msgThroughputIn) {
        this.msgThroughputIn = msgThroughputIn;
    }

    public double getMsgRateOut() {
        return msgRateOut;
    }

    public void setMsgRateOut(double msgRateOut) {
        this.msgRateOut = msgRateOut;
    }

    public double getMsgThroughputOut() {
        return msgThroughputOut;
    }

    public void setMsgThroughputOut(double msgThroughputOut) {
        this.msgThroughputOut = msgThroughputOut;
    }

    public double getAverageMsgSize() {
        return averageMsgSize;
    }

    public void setAverageMsgSize(double averageMsgSize) {
        this.averageMsgSize = averageMsgSize;
    }

    public long getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(long storageSize) {
        this.storageSize = storageSize;
    }

    public int getPendingAddEntriesCount() {
        return pendingAddEntriesCount;
    }

    public void setPendingAddEntriesCount(int pendingAddEntriesCount) {
        this.pendingAddEntriesCount = pendingAddEntriesCount;
    }

    public ArrayList<PulsarManagerPublisherStats> getPublishers() {
        return publishers;
    }

    public void setPublishers(ArrayList<PulsarManagerPublisherStats> publishers) {
        this.publishers = publishers;
    }

    public HashMap<String, PulsarManagerSubscriptionStats> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(HashMap<String, PulsarManagerSubscriptionStats> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public HashMap<String, PulsarManagerReplicatorStats> getReplication() {
        return replication;
    }

    public void setReplication(HashMap<String, PulsarManagerReplicatorStats> replication) {
        this.replication = replication;
    }

    public String getDeduplicationStatus() {
        return deduplicationStatus;
    }

    public void setDeduplicationStatus(String deduplicationStatus) {
        this.deduplicationStatus = deduplicationStatus;
    }

    public long getProducerCount() {
        return producerCount;
    }

    public void setProducerCount(long producerCount) {
        this.producerCount = producerCount;
    }

    public long getMsgInCount() {
        return msgInCount;
    }

    public void setMsgInCount(long msgInCount) {
        this.msgInCount = msgInCount;
    }

    public long getBytesInCount() {
        return bytesInCount;
    }

    public void setBytesInCount(long bytesInCount) {
        this.bytesInCount = bytesInCount;
    }

    public long getMsgOutCount() {
        return msgOutCount;
    }

    public void setMsgOutCount(long msgOutCount) {
        this.msgOutCount = msgOutCount;
    }

    public long getBytesOutCount() {
        return bytesOutCount;
    }

    public void setBytesOutCount(long bytesOutCount) {
        this.bytesOutCount = bytesOutCount;
    }

    public long getBacklogSize() {
        return backlogSize;
    }

    public void setBacklogSize(long backlogSize) {
        this.backlogSize = backlogSize;
    }
}
