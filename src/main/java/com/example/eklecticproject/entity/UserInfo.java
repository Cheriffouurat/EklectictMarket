package com.example.eklecticproject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {
    @JsonProperty("id")
    private String id;

    @JsonProperty("msisdn")
    private MSISDN msisdn;

    @JsonProperty("date_debfree")
    private String dateDebFree;

    @JsonProperty("date_finfree")
    private String dateFinFree;

    @JsonProperty("subscription_date")
    private String subscriptionDate;

    @JsonProperty("unsubscription_date")
    private String unsubscriptionDate;

    @JsonProperty("first_broadcastbilling")
    private String firstBroadcastBilling;

    @JsonProperty("last_broadcastbilling")
    private String lastBroadcastBilling;

    @JsonProperty("first_successbilling")
    private String firstSuccessBilling;

    @JsonProperty("last_successbilling")
    private String lastSuccessBilling;

    @JsonProperty("success_billing")
    private String successBilling;

    @JsonProperty("simchurn")
    private String simChurn;

    @JsonProperty("date_simchurn")
    private String dateSimChurn;

    @JsonProperty("date_revsimchurn")
    private String dateRevSimChurn;

    @JsonProperty("create_at")
    private String createAt;

    @JsonProperty("last_status_update")
    private String lastStatusUpdate;

    @JsonProperty("service_id")
    private String serviceId;

    @JsonProperty("offre_id")
    private String offreId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("acquisition")
    private String acquisition;

    @JsonProperty("type")
    private String type;

    @JsonProperty("partenaire_id")
    private String partenaireId;

    @JsonProperty("country")
    private String country;

    @JsonProperty("mncmcc")
    private String mncmcc;

    @JsonProperty("contry_code")
    private String countryCode;

    @JsonProperty("shortname")
    private String shortname;

    @JsonProperty("iss")
    private String iss;

    @JsonProperty("state")
    private String state;

    @JsonProperty("expire_date")
    private String expireDate;

    @Getter
    @Setter
    public static class MSISDN {
        @JsonProperty("value")
        private String value;
    }
}