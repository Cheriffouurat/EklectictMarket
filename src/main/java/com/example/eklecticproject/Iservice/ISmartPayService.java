package com.example.eklecticproject.Iservice;

public interface ISmartPayService {
    void sendSMS(String subscriptionId, String message);

    String getAccessToken();
}
