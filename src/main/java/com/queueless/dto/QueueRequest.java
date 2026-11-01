package com.queueless.dto;

import com.queueless.entity.QueueStatus;

public class QueueRequest {

    private String name;
    private String description;
    private QueueStatus status;
    private Long organizationId;

    public QueueRequest() {
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

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(QueueStatus status) {
        this.status = status;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }
}