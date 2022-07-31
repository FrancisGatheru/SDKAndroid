package com.geekwys.checkoutsdk.model;

public class Authentication {
    public static String clientId;
    public static String clientSecret;
    public static String grantType;

    public Authentication() {
    }

    public static String getClientId() {
        return clientId;
    }

    public static void setClientId(String clientId) {
        Authentication.clientId = clientId;
    }

    public static String getClientSecret() {
        return clientSecret;
    }

    public static void setClientSecret(String clientSecret) {
        Authentication.clientSecret = clientSecret;
    }

    public static String getGrantType() {
        return grantType;
    }

    public static void setGrantType(String grantType) {
        Authentication.grantType = grantType;
    }


}
