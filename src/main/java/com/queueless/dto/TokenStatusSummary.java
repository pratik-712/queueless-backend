package com.queueless.dto;

public class TokenStatusSummary {

    private long waiting;
    private long called;
    private long completed;

    public TokenStatusSummary(long waiting, long called, long completed) {
        this.waiting = waiting;
        this.called = called;
        this.completed = completed;
    }

    public long getWaiting() {
        return waiting;
    }

    public long getCalled() {
        return called;
    }

    public long getCompleted() {
        return completed;
    }
}