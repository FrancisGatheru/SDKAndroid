package com.geekwys.checkoutsdk;

import static com.geekwys.checkoutsdk.Constants.BASEURL;
import static com.geekwys.checkoutsdk.Constants.CLIENT_ID;
import static com.geekwys.checkoutsdk.Constants.CLIENT_SECRET;
import static com.geekwys.checkoutsdk.Constants.GRANT_TYPE;
import static com.geekwys.checkoutsdk.Constants.POST_CHECKOUT;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.geekwys.checkoutsdk.networkConfig.NetworkConfig;
import com.geekwys.checkoutsdk.service.CheckoutSdk;

import java.util.UUID;

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
        CheckoutSdk checkoutSdk = new CheckoutSdk();
        checkoutSdk.authenticateUser(CLIENT_ID, CLIENT_SECRET, GRANT_TYPE);

        String postCheckout = checkoutSdk.postCheckout(
                UUID.randomUUID().toString(), 1000D, "KES", "SH3M",
                "MADDEV3581", "2022-10-16 0:0:0", "Post Checkout", "KE", "Shem",
                "Brooklyn", "25472100000", "itsme@crap.com", "https://shem.com/crap",
                "https://itsme.com/yourdad", "https://absent.io/getmilk"
        );
        new NetworkConfig().onPostExecute(BASEURL, POST_CHECKOUT, postCheckout);
    }
}