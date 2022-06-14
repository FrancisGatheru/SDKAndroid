package com.geekwys.checkoutsdk.networkConfig;

public interface Network {
    void onPostExecute(String baseUrl, String endpoint, String request);
}
