package com.queueless.dto;

import com.queueless.entity.QueueStatus;

public class QueueUpdateRequest {

    private String name;
    private String description;
    private QueueStatus status;

    public QueueUpdateRequest() {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(QueueStatus status) {
        this.status = status;
    }
}