package com.geekwys.checkoutsdk.networkConfig;


import static com.geekwys.checkoutsdk.Constants.AUTH;
import static com.geekwys.checkoutsdk.Constants.BASEURL;
import static com.geekwys.checkoutsdk.Constants.CLIENT_ID;
import static com.geekwys.checkoutsdk.Constants.CLIENT_SECRET;
import static com.geekwys.checkoutsdk.Constants.CONTENT_TYPE;
import static com.geekwys.checkoutsdk.Constants.GRANT_TYPE;

import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.geekwys.checkoutsdk.service.CheckoutSdk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * @author evil twins
 */
public class NetworkConfig extends AsyncTask<String, String, String> implements Network {

    public NetworkConfig() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(params[0] + params[1]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //get access token to set as authorization header
            String accessToken = new AuthenticationConfig().onPostExecute(
                    BASEURL, AUTH, new CheckoutSdk().authenticateUser(
                            CLIENT_ID, CLIENT_SECRET, GRANT_TYPE
                    )
            );

            if (accessToken.isEmpty()) {
                Log.w("AUH-REQUEST", "Access token is null");
            }

            connection.setRequestProperty("Content-Type", CONTENT_TYPE);
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
            connection.setDoOutput(true);
            OutputStreamWriter streamWriter = new OutputStreamWriter(connection.getOutputStream());
            streamWriter.write(params[2]);

            streamWriter.close();

            int responseCode = connection.getResponseCode();
            InputStreamReader inputStreamReader;

            if (responseCode == 200)
                inputStreamReader = new InputStreamReader(connection.getInputStream());
            else
                inputStreamReader = new InputStreamReader(connection.getErrorStream());

            BufferedReader in = new BufferedReader(inputStreamReader);
            String inputLine;

            StringBuilder st = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                st.append(inputLine);
            }
            in.close();
            Log.i("CUSTOM-CHECKOUT::", "Response received:: " + st);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String onPostExecute(String baseUrl, String endpoint, String request) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Log.i("CUSTOM-CHECKOUT::", "Posting to URL:: " + baseUrl + endpoint + "\n" + "Request Payload:: " + request);
        doInBackground(baseUrl, endpoint, request);
        return "";
    }
}
