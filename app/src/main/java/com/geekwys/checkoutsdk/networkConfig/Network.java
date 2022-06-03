package com.geekwys.checkoutsdk.networkConfig;


import static com.geekwys.checkoutsdk.Constants.AUTH;
import static com.geekwys.checkoutsdk.Constants.CHARSET;
import static com.geekwys.checkoutsdk.Constants.CONTENT_TYPE;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;


/**
 * @author evil twins
 */
public class Network extends AsyncTask<String, String, String> {

    public Network() {
    }

    @Override
    protected String doInBackground(String... params) {
        String baseUrl = params[0];
        String endpoint = params[1];
        String request = params[2];

        String response = null;

        try {
            HttpURLConnection connection;
            URL url = new URL(baseUrl + endpoint);

            connection = (HttpURLConnection) url.openConnection();

            StringBuilder st = new StringBuilder();
            if (endpoint.contains(AUTH)) {
                connection.setRequestProperty("Content-Type", CONTENT_TYPE);
                connection.setDoOutput(true);
                OutputStreamWriter streamWriter = new OutputStreamWriter(connection.getOutputStream());
                streamWriter.write(Arrays.toString(request.getBytes(CHARSET)));

                streamWriter.flush();
                streamWriter.close();

                int responseCode = connection.getResponseCode();
                InputStreamReader inputStreamReader;

                if (responseCode == 200)
                    inputStreamReader = new InputStreamReader(connection.getInputStream());
                else
                    inputStreamReader = new InputStreamReader(connection.getErrorStream());

                BufferedReader in = new BufferedReader(inputStreamReader);
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    st.append(inputLine);
                }
                in.close();
                Log.i("CUSTOM-CHECKOUT::", "Response Received:: " + st);
            } else if (!endpoint.contains(AUTH)) {
                String bearerToken = st.toString();
                connection.setRequestProperty("Content-Type", CONTENT_TYPE);
                connection.setRequestProperty("Authorization", "Bearer " + bearerToken);
                connection.setDoOutput(true);
                OutputStreamWriter streamWriter = new OutputStreamWriter(connection.getOutputStream());
                streamWriter.write(Arrays.toString(request.getBytes(CHARSET)));

                streamWriter.flush();
                streamWriter.close();

                int responseCode = connection.getResponseCode();
                InputStreamReader inputStreamReader;

                if (responseCode == 200)
                    inputStreamReader = new InputStreamReader(connection.getInputStream());
                else
                    inputStreamReader = new InputStreamReader(connection.getErrorStream());

                BufferedReader in = new BufferedReader(inputStreamReader);
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    st.append(inputLine);
                }
                in.close();
                Log.i("CUSTOM-CHECKOUT::", "Response received:: " + st);
            }

            response = st.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    public static class execute {

        public execute(String url, String endpoint, String payload) {
            Log.i("CUSTOM-CHECKOUT::", "Posting to URL:: " + url + endpoint + "\n" + "Request Payload:: " + payload);
            new Network().doInBackground(url, endpoint, payload);
        }
    }
}
