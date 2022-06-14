package com.geekwys.checkoutsdk;

import static com.geekwys.checkoutsdk.Constants.AUTH;
import static com.geekwys.checkoutsdk.Constants.BASEURL;
import static com.geekwys.checkoutsdk.Constants.CLIENT_ID;
import static com.geekwys.checkoutsdk.Constants.CLIENT_SECRET;
import static com.geekwys.checkoutsdk.Constants.GRANT_TYPE;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.geekwys.checkoutsdk.networkConfig.AuthenticationConfig;
import com.geekwys.checkoutsdk.service.CheckoutSdk;

/**
 * @author evil twins
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeCall();
    }

    void makeCall() {
        //make auth call
        CheckoutSdk checkoutSdk = new CheckoutSdk();
        String authRequest = checkoutSdk.authenticateUser(CLIENT_ID, CLIENT_SECRET, GRANT_TYPE);

        new AuthenticationConfig().onPostExecute(BASEURL, AUTH, authRequest);
    }
}