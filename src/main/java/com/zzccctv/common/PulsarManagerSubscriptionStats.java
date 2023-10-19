package com.zzccctv.common;

import java.util.ArrayList;

public class PulsarManagerSubscriptionStats {
    private long numberOfEntriesSinceFirstNotAckedMessage;
    private long totalNonContiguousDeletedMessagesRange;

    /** Total rate of messages delivered on this subscription. msg/s */
    private double msgRateOut;

    /** Total throughput delivered on this subscription. bytes/s */
    private double msgThroughputOut;

    /** Total rate of messages redelivered on this subscription. msg/s */
    private double msgRateRedeliver;

    /** Number of messages in the subscription backlog */
    private long msgBacklog;

    /** Whether this subscription is Exclusive or Shared or Failover */
    private String type;

    /** Total rate of messages expired on this subscription. msg/s */
    private double msgRateExpired;

    /** List of connected consumers on this subscription w/ their stats */
    private ArrayList<PulsarManagerConsumerStats> consumers;

    /** Mark that the subscription state is kept in sync across different regions */
    private boolean isReplicated;

    public long getNumberOfEntriesSinceFirstNotAckedMessage() {
        return numberOfEntriesSinceFirstNotAckedMessage;
    }

    public void setNumberOfEntriesSinceFirstNotAckedMessage(long numberOfEntriesSinceFirstNotAckedMessage) {
        this.numberOfEntriesSinceFirstNotAckedMessage = numberOfEntriesSinceFirstNotAckedMessage;
    }

    public long getTotalNonContiguousDeletedMessagesRange() {
        return totalNonContiguousDeletedMessagesRange;
    }

    public void setTotalNonContiguousDeletedMessagesRange(long totalNonContiguousDeletedMessagesRange) {
        this.totalNonContiguousDeletedMessagesRange = totalNonContiguousDeletedMessagesRange;
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

    public double getMsgRateRedeliver() {
        return msgRateRedeliver;
    }

    public void setMsgRateRedeliver(double msgRateRedeliver) {
        this.msgRateRedeliver = msgRateRedeliver;
    }

    public long getMsgBacklog() {
        return msgBacklog;
    }

    public void setMsgBacklog(long msgBacklog) {
        this.msgBacklog = msgBacklog;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMsgRateExpired() {
        return msgRateExpired;
    }

    public void setMsgRateExpired(double msgRateExpired) {
        this.msgRateExpired = msgRateExpired;
    }

    public ArrayList<PulsarManagerConsumerStats> getConsumers() {
        return consumers;
    }

    public void setConsumers(ArrayList<PulsarManagerConsumerStats> consumers) {
        this.consumers = consumers;
    }

    public boolean isReplicated() {
        return isReplicated;
    }

    public void setReplicated(boolean replicated) {
        isReplicated = replicated;
    }
}
