package com.sohilladhani.weeklydrawapplication.util.db;

public interface DAO {
    void setup() throws Exception;
    void connect() throws Exception;
    void close() throws Exception;
    boolean isConnected() throws Exception;
}
