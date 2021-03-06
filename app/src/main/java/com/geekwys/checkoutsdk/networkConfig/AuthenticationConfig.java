package com.geekwys.checkoutsdk.networkConfig;

import static com.geekwys.checkoutsdk.Constants.CONTENT_TYPE;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author evil twins
 */
public class AuthenticationConfig extends AsyncTask<String, String, String> implements Network {

    public AuthenticationConfig() {
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String accessToken = null;
        try {
            URL url = new URL(strings[0] + strings[1]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", CONTENT_TYPE);
            OutputStreamWriter streamWriter = new OutputStreamWriter(connection.getOutputStream());
            streamWriter.write(strings[2]);
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
            JSONObject bearToken = new JSONObject(String.valueOf(st));
            Log.i("AUTH-REQUEST::", "Response Received:: " + st);

            //extract access token from parent object
            accessToken = bearToken.get("access_token").toString();
        } catch (NullPointerException | IOException | JSONException exception) {
            exception.printStackTrace();
        }
        return accessToken;
    }

    @Override
    public String onPostExecute(String baseUrl, String endpoint, String request) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Log.i("CUSTOM-CHECKOUT::", "Posting to URL:: " + baseUrl + endpoint + "\n" + "Request Payload:: " + request);
        return doInBackground(baseUrl, endpoint, request);
    }
}
