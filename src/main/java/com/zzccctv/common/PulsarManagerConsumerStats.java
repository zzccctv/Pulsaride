package com.zzccctv.common;

import java.util.HashMap;

public class PulsarManagerConsumerStats {
    private String address;

    private String connectedSince;

    private String clientVersion;

    /** Total rate of messages delivered to the consumer. msg/s */
    private double msgRateOut;

    /** Total throughput delivered to the consumer. bytes/s */
    private double msgThroughputOut;

    /** Total rate of messages redelivered by this consumer. msg/s */
    private double msgRateRedeliver;

    /** Name of the consumer */
    private String consumerName;

    /** Number of available message permits for the consumer */
    private int availablePermits;

    /** Metadata (key/value strings) associated with this consumer */
    private HashMap<String, String> metadata;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getConnectedSince() {
        return connectedSince;
    }

    public void setConnectedSince(String connectedSince) {
        this.connectedSince = connectedSince;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
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

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public int getAvailablePermits() {
        return availablePermits;
    }

    public void setAvailablePermits(int availablePermits) {
        this.availablePermits = availablePermits;
    }

    public HashMap<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(HashMap<String, String> metadata) {
        this.metadata = metadata;
    }
}
