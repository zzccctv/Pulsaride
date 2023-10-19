package com.zzccctv.common;

import java.util.ArrayList;

public class PulsarManagerReplicatorStats {
    /** Total rate of messages received from the remote cluster. msg/s */
    private double msgRateIn;

    /** Total throughput received from the remote cluster. bytes/s */
    private double msgThroughputIn;

    /** Total rate of messages delivered to the replication-subscriber. msg/s */
    private double msgRateOut;

    /** Total throughput delivered to the replication-subscriber. bytes/s */
    private double msgThroughputOut;

    /** Total rate of messages expired. msg/s */
    private double msgRateExpired;

    /** Number of messages pending to be replicated to remote cluster */
    private long replicationBacklog;

    /** is the replication-subscriber up and running to replicate to remote cluster */
    private boolean connected;

    /** Time in seconds from the time a message was produced to the time when it is about to be replicated */
    private long replicationDelayInSeconds;

    /** Address of incoming replication connection */
    private String inboundConnection;

    /** Timestamp of incoming connection establishment time */
    private String inboundConnectedSince;

    /** Address of outbound replication connection */
    private String outboundConnection;

    /** Timestamp of outbound connection establishment time */
    private String outboundConnectedSince;

    private ArrayList<PulsarManagerConsumerStats> consumers;

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

    public double getMsgRateExpired() {
        return msgRateExpired;
    }

    public void setMsgRateExpired(double msgRateExpired) {
        this.msgRateExpired = msgRateExpired;
    }

    public long getReplicationBacklog() {
        return replicationBacklog;
    }

    public void setReplicationBacklog(long replicationBacklog) {
        this.replicationBacklog = replicationBacklog;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public long getReplicationDelayInSeconds() {
        return replicationDelayInSeconds;
    }

    public void setReplicationDelayInSeconds(long replicationDelayInSeconds) {
        this.replicationDelayInSeconds = replicationDelayInSeconds;
    }

    public String getInboundConnection() {
        return inboundConnection;
    }

    public void setInboundConnection(String inboundConnection) {
        this.inboundConnection = inboundConnection;
    }

    public String getInboundConnectedSince() {
        return inboundConnectedSince;
    }

    public void setInboundConnectedSince(String inboundConnectedSince) {
        this.inboundConnectedSince = inboundConnectedSince;
    }

    public String getOutboundConnection() {
        return outboundConnection;
    }

    public void setOutboundConnection(String outboundConnection) {
        this.outboundConnection = outboundConnection;
    }

    public String getOutboundConnectedSince() {
        return outboundConnectedSince;
    }

    public void setOutboundConnectedSince(String outboundConnectedSince) {
        this.outboundConnectedSince = outboundConnectedSince;
    }

    public ArrayList<PulsarManagerConsumerStats> getConsumers() {
        return consumers;
    }

    public void setConsumers(ArrayList<PulsarManagerConsumerStats> consumers) {
        this.consumers = consumers;
    }
}
