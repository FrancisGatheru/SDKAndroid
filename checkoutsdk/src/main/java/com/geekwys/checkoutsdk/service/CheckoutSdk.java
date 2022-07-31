package com.geekwys.checkoutsdk.service;

import android.util.Log;

import com.geekwys.checkoutsdk.model.Authentication;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author evil twins
 */
public class CheckoutSdk {

    /**
     * @param clientId
     * @param clientSecret build auth payload for bearer token
     * @param grantType    remains as const - client_credentials
     */
    public String authenticateUser(String clientId, String clientSecret, String grantType) {
        Authentication.setClientId(clientId);
        Authentication.setClientSecret(clientSecret);
        Authentication.setGrantType(grantType);

        JSONObject userAuthDetails = new JSONObject();

        try {
            userAuthDetails.put("client_id", Authentication.getClientId());
            userAuthDetails.put("client_secret", Authentication.getClientSecret());
            userAuthDetails.put("grant_type", Authentication.getGrantType());
        } catch (NullPointerException | JSONException e) {
            e.printStackTrace();
        }

        Log.i("Authentication Payload", "Built Auth Payload: " + userAuthDetails);
        return userAuthDetails.toString();
    }

    /**
     * formulate post-checkout payload.
     */
    public String postCheckout(String merchantTransactionID, Double requestAmount, String currencyCode,
                               String accountNumber, String serviceCode, String dueDate, String requestDescription,
                               String countryCode, String customerFirstName, String customerLastName, String MSISDN,
                               String customerEmail, String paymentWebhookUrl, String successRedirectUrl, String failRedirectUrl
    ) {
        JSONObject postCheckout = new JSONObject();
        try {
            postCheckout.put("merchantTransactionID", merchantTransactionID);
            postCheckout.put("requestAmount", requestAmount);
            postCheckout.put("currencyCode", currencyCode);
            postCheckout.put("requestAmount", requestAmount);
            postCheckout.put("accountNumber", accountNumber);
            postCheckout.put("serviceCode", serviceCode);
            postCheckout.put("dueDate", dueDate);
            postCheckout.put("requestDescription", requestDescription);
            postCheckout.put("countryCode", countryCode);
            postCheckout.put("customerFirstName", customerFirstName);
            postCheckout.put("customerLastName", customerLastName);
            postCheckout.put("MSISDN", MSISDN);
            postCheckout.put("customerEmail", customerEmail);
            postCheckout.put("paymentWebhookUrl", paymentWebhookUrl);
            postCheckout.put("successRedirectUrl", successRedirectUrl);
            postCheckout.put("failRedirectUrl", failRedirectUrl);
        } catch (NullPointerException | JSONException e) {
            e.printStackTrace();
        }
        return postCheckout.toString();
    }

    /**
     * formulate post-charge payload
     */
    public String postCharge(String merchantTransactionID, String checkoutRequestID, String chargeMsisdn,
                             String chargeAmount, String currencyCode, String payerModeID, String languageCode
    ) {
        JSONObject postCharge = new JSONObject();
        try {
            postCharge.put("merchantTransactionID", merchantTransactionID);
            postCharge.put("checkoutRequestID", checkoutRequestID);
            postCharge.put("chargeMsisdn", chargeMsisdn);
            postCharge.put("chargeAmount", chargeAmount);
            postCharge.put("currencyCode", currencyCode);
            postCharge.put("payerModeID", payerModeID);
            postCharge.put("languageCode", languageCode);
        } catch (NullPointerException | JSONException e) {
            e.printStackTrace();
        }
        return postCharge.toString();
    }


    /**
     * formulate query-payment status
     */
    public String queryPaymentStatus(String merchantTransactionID, String serviceCode, String checkoutRequestID) {
        JSONObject queryStatus = new JSONObject();
        try {
            queryStatus.put("merchantTransactionID", merchantTransactionID);
            queryStatus.put("serviceCode", serviceCode);
            queryStatus.put("checkoutRequestID", checkoutRequestID);
        } catch (NullPointerException | JSONException e) {
            e.printStackTrace();
        }
        return queryStatus.toString();
    }

    /**
     * formulate capture-payment payload
     */
    public String acknowledgePayment(String checkoutRequestID, String merchantTransactionID, String statusCode,
                                     String statusDescription, String receiptNumber, String currencyCode, Double acknowledgeAmount
    ) {
        JSONObject capturePayment = new JSONObject();
        try {
            capturePayment.put("checkoutRequestID", checkoutRequestID);
            capturePayment.put("merchantTransactionID", merchantTransactionID);
            capturePayment.put("statusCode", statusCode);
            capturePayment.put("statusDescription", statusDescription);
            capturePayment.put("receiptNumber", receiptNumber);
            capturePayment.put("currencyCode", currencyCode);
            capturePayment.put("acknowledgeAmount", acknowledgeAmount);
        } catch (NullPointerException | JSONException e) {
            e.printStackTrace();
        }
        return capturePayment.toString();
    }

    /**
     * formulate initiate refund payload
     */
    public String initiateRefund(String merchantTransactionID, String checkoutRequestID, String refundType,
                                 Double refundAmount, String currencyCode, String narration, String extraDetails
    ) {
        JSONObject initiateRefund = new JSONObject();
        try {
            initiateRefund.put("merchantTransactionID", merchantTransactionID);
            initiateRefund.put("checkoutRequestID", checkoutRequestID);
            initiateRefund.put("refundType", refundType);
            initiateRefund.put("refundAmount", refundAmount);
            initiateRefund.put("currencyCode", currencyCode);
            initiateRefund.put("narration", narration);
            initiateRefund.put("extraDetails", extraDetails);
        } catch (NullPointerException | JSONException e) {
            e.printStackTrace();
        }
        return initiateRefund.toString();
    }

    /**
     * formulate cancel refund payload
     */
    public String cancelRefund(String merchantTransactionID, String serviceCode, Integer checkoutRequestID) {
        JSONObject cancelRefund = new JSONObject();
        try {
            cancelRefund.put("merchantTransactionID", merchantTransactionID);
            cancelRefund.put("serviceCode", serviceCode);
            cancelRefund.put("checkoutRequestID", checkoutRequestID);
        } catch (NullPointerException | JSONException e) {
            e.printStackTrace();
        }
        return cancelRefund.toString();
    }

}
