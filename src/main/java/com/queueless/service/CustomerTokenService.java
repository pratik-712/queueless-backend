package com.queueless.service;

import java.util.List;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.queueless.exception.NoWaitingCustomersException;
import com.queueless.exception.QueueNotFoundException;
import com.queueless.entity.TokenStatus;
import com.queueless.entity.CustomerToken;
import com.queueless.entity.Queue;
import com.queueless.repository.CustomerTokenRepository;
import com.queueless.repository.QueueRepository;
import com.queueless.dto.TokenStatusSummary;
import com.queueless.exception.QueueNotFoundException;
import com.queueless.exception.TokenNotFoundException;

@Service
public class CustomerTokenService {

    private final CustomerTokenRepository tokenRepository;
    private final QueueRepository queueRepository;

    public CustomerTokenService(
            CustomerTokenRepository tokenRepository,
            QueueRepository queueRepository) {
        this.tokenRepository = tokenRepository;
        this.queueRepository = queueRepository;
    }

    // Generate a token for a customer
    public CustomerToken createToken(Long queueId, String customerName) {

        Queue queue = queueRepository.findById(queueId)
        .orElseThrow(() -> new QueueNotFoundException(
                "Queue not found with ID: " + queueId
        ));

        Optional<CustomerToken> lastToken =tokenRepository.findFirstByQueueIdOrderByIdDesc(queueId);

        int nextTokenNumber = lastToken
                .map(token -> Integer.parseInt(
                        token.getTokenNumber().substring(1)) + 1)
                .orElse(1);

        String tokenNumber = String.format("A%03d", nextTokenNumber);

        CustomerToken token = new CustomerToken(
                tokenNumber,
                customerName,
                TokenStatus.WAITING,
                queue
        );

        return tokenRepository.save(token);
    }

    // Get all tokens
    public List<CustomerToken> getAllTokens() {
        return tokenRepository.findAll();
    }

    // Get tokens for a specific queue
    public List<CustomerToken> getTokensByQueueId(Long queueId) {
        return tokenRepository.findByQueueId(queueId);
    }

        // Get a customer's position in the queue
        public int getCustomerQueuePosition(Long tokenId) {

        CustomerToken customerToken = tokenRepository.findById(tokenId)
                .orElseThrow(() -> new TokenNotFoundException(
                "Token not found with ID: " + tokenId
        ));

        if (customerToken.getStatus() != TokenStatus.WAITING) {
                return 0;
        }

        List<CustomerToken> waitingTokens =
                tokenRepository.findByQueueIdAndStatusOrderByCreatedAtAsc(
                        customerToken.getQueue().getId(),
                        TokenStatus.WAITING
                );

        for (int i = 0; i < waitingTokens.size(); i++) {
                if (waitingTokens.get(i).getId().equals(tokenId)) {
                return i + 1;
                }
        }

        return 0;
        }

        // Update token status
        public CustomerToken updateTokenStatus(Long tokenId, TokenStatus status) {

        CustomerToken token = tokenRepository.findById(tokenId)
                .orElseThrow(() -> new TokenNotFoundException(
                        "Token not found with ID: " + tokenId
                ));

        if (status == null) {
                throw new IllegalArgumentException(
                        "Token status cannot be empty"
                );
        }

        if (token.getStatus() == TokenStatus.COMPLETED) {
        throw new IllegalArgumentException(
                "Completed tokens cannot change status"
        );
        }

        token.setStatus(status);

        return tokenRepository.save(token);
        }

    // Call the next waiting customer in a queue
    public CustomerToken callNextCustomer(Long queueId) {

        CustomerToken token = tokenRepository
                .findFirstByQueueIdAndStatusOrderByCreatedAtAsc(
                        queueId,
                        TokenStatus.WAITING
                )
                .orElseThrow(() ->
                        new NoWaitingCustomersException(
                                "No waiting customers in this queue"
                        )
                );

        token.setStatus(TokenStatus.CALLED);

        return tokenRepository.save(token);
    }
    
    // Get token counts by status for a specific queue
    public TokenStatusSummary getTokenStatusSummary(Long queueId) {

        List<CustomerToken> tokens = tokenRepository.findByQueueId(queueId);

        long waiting = tokens.stream()
                .filter(token -> token.getStatus() == TokenStatus.WAITING)
                .count();

        long called = tokens.stream()
                .filter(token -> token.getStatus() == TokenStatus.CALLED)
                .count();

        long completed = tokens.stream()
                .filter(token -> token.getStatus() == TokenStatus.COMPLETED)
                .count();

        return new TokenStatusSummary(waiting, called, completed);
    }
}