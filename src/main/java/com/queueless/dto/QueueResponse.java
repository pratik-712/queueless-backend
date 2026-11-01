package com.queueless.dto;

import com.queueless.entity.QueueStatus;

import java.time.LocalDateTime;

public class QueueResponse {

    private Long id;
    private String name;
    private String description;
    private QueueStatus status;
    private LocalDateTime createdAt;
    private Long organizationId;

    public QueueResponse(
            Long id,
            String name,
            String description,
            QueueStatus status,
            LocalDateTime createdAt,
            Long organizationId) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.organizationId = organizationId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public QueueStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getOrganizationId() {
    return organizationId;
    }
}