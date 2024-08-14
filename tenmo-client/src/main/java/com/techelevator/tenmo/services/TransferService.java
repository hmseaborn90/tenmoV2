package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class TransferService {

    public static final String API_BASE_URL = "http://localhost:8080/transfer/";
    private RestTemplate restTemplate = new RestTemplate();

    private String authToken = null;

    public void setAuthToken(AuthenticatedUser currentUser) {
        if (currentUser != null && currentUser.getToken() != null) {

            this.authToken = currentUser.getToken();

        } else {
            throw new IllegalArgumentException("Invalid Authenticated User or token");
        }
    }

//    public BigDecimal getBalance() {
//        BigDecimal balance = null;
//        try {
//            ResponseEntity<BigDecimal> response =
//                    restTemplate.exchange(API_BASE_URL + "balance/", HttpMethod.GET, makeAuthEntity(), BigDecimal.class);
//            if (response.getStatusCode().is2xxSuccessful()) {
//                balance = response.getBody();
//            } else {
//                System.out.println("Error: " + response.getStatusCode() + " - " + response.getBody());
//            }
//        } catch (RestClientResponseException | ResourceAccessException e) {
//            System.out.println("Exception: " + e.getMessage());
//        }
//        return balance;
//    }

}
