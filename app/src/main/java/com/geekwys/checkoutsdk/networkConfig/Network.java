package com.geekwys.checkoutsdk.networkConfig;

public interface Network {
    String onPostExecute(String baseUrl, String endpoint, String request);
}
