package com.geekwys.checkoutsdk.networkConfig;

public interface Authentication {
    void onPostExecute(String baseUrl, String endpoint, String request);
}
