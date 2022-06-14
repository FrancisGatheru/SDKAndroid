package com.geekwys.checkoutsdk.networkConfig;

/**
 * @author evil twins
 */
public interface Network {
    String onPostExecute(String baseUrl, String endpoint, String request);
}
