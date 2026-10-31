package com.queueless.dto;

import com.queueless.entity.QueueStatus;

import java.time.LocalDateTime;

public class QueueResponse {

    private Long id;
    private String name;
    private String description;
    private QueueStatus status;
    private LocalDateTime createdAt;

    public QueueResponse(
            Long id,
            String name,
            String description,
            QueueStatus status,
            LocalDateTime createdAt) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
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
}