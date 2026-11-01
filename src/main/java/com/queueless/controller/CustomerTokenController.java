package com.queueless.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.queueless.dto.TokenStatusSummary;

import com.queueless.entity.TokenStatus;
import com.queueless.entity.CustomerToken;
import com.queueless.service.CustomerTokenService;

@RestController
@RequestMapping("/api/tokens")
public class CustomerTokenController {

    private final CustomerTokenService tokenService;

    public CustomerTokenController(CustomerTokenService tokenService) {
        this.tokenService = tokenService;
    }

    // Generate a token for a customer

    // Generate a token for a customer
    @PostMapping("/join/{queueId}")
    public CustomerToken createToken(
            @PathVariable Long queueId,
            @RequestParam(required = false) String customerName) {

        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException(
                    "Customer name cannot be empty"
            );
        }

        customerName = customerName.trim();

        if (!customerName.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException(
                    "Customer name must contain only letters and spaces"
            );
        }

        return tokenService.createToken(queueId, customerName);
    }
    // Get all tokens
    @GetMapping
    public List<CustomerToken> getAllTokens() {
        return tokenService.getAllTokens();
    }

    // Get tokens for a specific queue
    @GetMapping("/queue/{queueId}")
    public List<CustomerToken> getTokensByQueueId(
            @PathVariable Long queueId) {
        return tokenService.getTokensByQueueId(queueId);
    }

    // Get a customer's position in the queue
    @GetMapping("/{tokenId}/position")
    public int getCustomerQueuePosition(@PathVariable Long tokenId) {
        return tokenService.getCustomerQueuePosition(tokenId);
    }

    // Update token status
    @PutMapping("/{tokenId}/status")
    public CustomerToken updateTokenStatus(
            @PathVariable Long tokenId,
            @RequestBody TokenStatus status) {
        return tokenService.updateTokenStatus(tokenId, status);
    }

        // Call the next waiting customer
    @PutMapping("/queue/{queueId}/call-next")
    public CustomerToken callNextCustomer(@PathVariable Long queueId) {
        return tokenService.callNextCustomer(queueId);
    }

        // Get token status summary for a specific queue
    @GetMapping("/queue/{queueId}/summary")
    public TokenStatusSummary getTokenStatusSummary(
            @PathVariable Long queueId) {
        return tokenService.getTokenStatusSummary(queueId);
    }
}