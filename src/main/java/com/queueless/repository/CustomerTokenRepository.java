package com.queueless.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.queueless.entity.CustomerToken;
import com.queueless.entity.TokenStatus;

public interface CustomerTokenRepository extends JpaRepository<CustomerToken, Long> {

    List<CustomerToken> findByQueueId(Long queueId);

    List<CustomerToken> findByQueueIdAndStatusOrderByCreatedAtAsc(
            Long queueId,
            TokenStatus status
    );

    Optional<CustomerToken> findFirstByQueueIdAndStatusOrderByCreatedAtAsc(
            Long queueId,
            TokenStatus status
    );

    Optional<CustomerToken> findFirstByQueueIdOrderByIdDesc(Long queueId);
}