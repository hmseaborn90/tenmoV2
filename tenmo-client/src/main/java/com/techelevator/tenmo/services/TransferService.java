package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.*;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;

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

    public String sendTeBucks(int userId, int amount) {
        TransferRequestDto transferRequestDto = new TransferRequestDto();
        transferRequestDto.setUserTo(userId);
        transferRequestDto.setAmount(BigDecimal.valueOf(amount));
        String responseMessage = null;
        try {
            ResponseEntity<String> response =
                    restTemplate.exchange(API_BASE_URL + "send", HttpMethod.POST, makeTransferRequestEntity(transferRequestDto), String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                responseMessage = response.getBody();
            } else {
                System.out.println("Error: " + response.getStatusCode() + " - " + response.getBody());
            }
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return responseMessage;
    }
    public String requestTeBucks(int userId, int amount) {
        TransferRequestDto transferRequestDto = new TransferRequestDto();
        transferRequestDto.setUserTo(userId);
        transferRequestDto.setAmount(BigDecimal.valueOf(amount));
        String responseMessage = null;
        try {
            ResponseEntity<String> response =
                    restTemplate.exchange(API_BASE_URL + "request", HttpMethod.POST, makeTransferRequestEntity(transferRequestDto), String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                responseMessage = response.getBody();
            } else {
                System.out.println("Error: " + response.getStatusCode() + " - " + response.getBody());
            }
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return responseMessage;
    }

    public TransferPendingDto[] getPending() {
        TransferPendingDto[] pendingTransfers = null;
        try {
            ResponseEntity<TransferPendingDto[]> response =
                    restTemplate.exchange(API_BASE_URL + "pending", HttpMethod.GET, makeAuthEntity(), TransferPendingDto[].class);
            if (response.getStatusCode().is2xxSuccessful()) {
                pendingTransfers = response.getBody();
            } else {
                System.out.println("Error: " + response.getStatusCode() + " - " + response.getBody());
            }
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return pendingTransfers;
    }
    public TransferDto[] getTransfers() {
        TransferDto[] transfers = null;
        try {
            ResponseEntity<TransferDto[]> response =
                    restTemplate.exchange(API_BASE_URL , HttpMethod.GET, makeAuthEntity(), TransferDto[].class);
            if (response.getStatusCode().is2xxSuccessful()) {
                transfers = response.getBody();
            } else {
                System.out.println("Error: " + response.getStatusCode() + " - " + response.getBody());
            }
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return transfers;
    }
    public TransferDetailsDto getTransfersById(int transferId) {
        TransferDetailsDto transfer = null;
        try {
            ResponseEntity<TransferDetailsDto> response =
                    restTemplate.exchange(API_BASE_URL + "/" + transferId , HttpMethod.GET, makeAuthEntity(), TransferDetailsDto.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                transfer = response.getBody();
            } else {
                System.out.println("Error: " + response.getStatusCode() + " - " + response.getBody());
            }
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return transfer;
    }

    public String approveTransfer(int trasferId){
        String responseMessage = null;
        try {
            ResponseEntity<String> response =
                    restTemplate.exchange(API_BASE_URL + "/" + trasferId + "/approve", HttpMethod.PUT, makeAuthEntity(), String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                responseMessage = response.getBody();

            } else {
                System.out.println("Error: " + response.getStatusCode() + " - " + response.getBody());
            }
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return responseMessage;
    }
    public String rejectTransfer(int trasferId){
        String responseMessage = null;
        try {
            ResponseEntity<String> response =
                    restTemplate.exchange(API_BASE_URL + "/" + trasferId + "/reject", HttpMethod.PUT, makeAuthEntity(), String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                responseMessage = response.getBody();
            } else {
                System.out.println("Error: " + response.getStatusCode() + " - " + response.getBody());
            }
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return responseMessage;
    }

    private HttpEntity<TransferRequestDto> makeTransferRequestEntity(TransferRequestDto transferRequestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(transferRequestDto, headers);
    }

    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        if (authToken != null) {
            headers.setBearerAuth(authToken);
        } else {
            throw new IllegalStateException("Auth token not set");
        }
        return new HttpEntity<>(headers);
    }

}
