package com.geekwys.checkoutsdk.networkConfig;


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
        String response = params[2];
        String baseUrl = params[0];
        String request = params[1];

        try {
            HttpURLConnection connection;
            URL url = new URL(baseUrl);

            connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", CONTENT_TYPE);

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

            StringBuilder st = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                st.append(inputLine);
            }
            in.close();
            Log.i("POST-CHECKOUT:", "Response received:: " + st);
            response = st.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    public static class execute {

        public execute(String url, String payload, String response) {
            new Network().doInBackground(url, payload, response);
        }
    }
}
