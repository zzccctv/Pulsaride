package com.zzccctv.common;

import java.util.HashMap;

public class PulsarManagerPublisherStats {
    /** Total rate of messages published by this publisher. msg/s */
    private double msgRateIn;

    /** Total throughput of messages published by this publisher. byte/s */
    private double msgThroughputIn;

    /** Average message size published by this publisher */
    private double averageMsgSize;

    private String address;

    /** Id of this publisher */
    private long producerId;

    private String producerName;

    private String connectedSince;

    private String clientVersion;

    private HashMap<String, String> metadata;

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

    public double getAverageMsgSize() {
        return averageMsgSize;
    }

    public void setAverageMsgSize(double averageMsgSize) {
        this.averageMsgSize = averageMsgSize;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getProducerId() {
        return producerId;
    }

    public void setProducerId(long producerId) {
        this.producerId = producerId;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
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

    public HashMap<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(HashMap<String, String> metadata) {
        this.metadata = metadata;
    }
}
