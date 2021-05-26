package com.neocyberfashion.retrofitConfig;

public enum HostAddress {

    HOST_ADDRESS("http://192.168.0.2:8080/");

    private final String hostAddress;

    HostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public String getHostAddress() {
        return hostAddress;
    }
}
