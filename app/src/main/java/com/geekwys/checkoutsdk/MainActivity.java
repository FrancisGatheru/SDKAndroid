package com.geekwys.checkoutsdk;

import static com.geekwys.checkoutsdk.Constants.AUTH;
import static com.geekwys.checkoutsdk.Constants.BASEURL;
import static com.geekwys.checkoutsdk.Constants.CLIENT_ID;
import static com.geekwys.checkoutsdk.Constants.CLIENT_SECRET;
import static com.geekwys.checkoutsdk.Constants.GRANT_TYPE;
import static com.geekwys.checkoutsdk.Constants.POST_CHECKOUT;

import android.os.Bundle;
import android.os.StrictMode;

import androidx.appcompat.app.AppCompatActivity;

import com.geekwys.checkoutsdk.networkConfig.Network;
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
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //make auth call
        CheckoutSdk checkoutSdk = new CheckoutSdk();
        String authRequest = checkoutSdk.authenticateUser(CLIENT_ID, CLIENT_SECRET, GRANT_TYPE);

        //make post-checkout request
        String postCheckout = checkoutSdk.postCheckout("", 0.0D, "", "",
                "", "", "", "", "", "",
                "", "", "", "", "");

        //make post-charge request
        String postCharge = checkoutSdk.postCharge("", "", "",
                "", "", "", "");

        //make query-payment status request
        String queryStatus = checkoutSdk.queryPaymentStatus("", "", "");

        System.out.println("What the fuck is request: " + queryStatus);

        new Network.execute(BASEURL, POST_CHECKOUT, queryStatus);
    }
}