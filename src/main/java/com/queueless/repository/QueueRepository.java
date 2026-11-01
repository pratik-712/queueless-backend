package com.queueless.repository;

import java.util.List;

import com.queueless.entity.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueRepository extends JpaRepository<Queue, Long> {

    List<Queue> findByOrganizationId(Long organizationId);

}