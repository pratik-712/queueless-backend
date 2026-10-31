package com.queueless.controller;

import com.queueless.entity.Queue;
import com.queueless.service.QueueService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.queueless.dto.QueueRequest;
import com.queueless.dto.QueueResponse;
import com.queueless.dto.QueueUpdateRequest;

@RestController
@RequestMapping("/api/queues")
public class QueueController {

    private final QueueService queueService;

    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    @PostMapping
    public QueueResponse createQueue(@RequestBody QueueRequest request) {
        return queueService.createQueue(request);
    }
    
    @GetMapping
    public List<QueueResponse> getAllQueues(){
        return queueService.getAllQueues();
    }

    @GetMapping("/{id}")
    public QueueResponse getQueueById(@PathVariable Long id) {
        return queueService.getQueueById(id);
    }

    @PutMapping("/{id}")
    public QueueResponse updateQueue(
        @PathVariable Long id,
        @RequestBody QueueUpdateRequest request) {
        return queueService.updateQueue(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteQueue(@PathVariable Long id) {
        queueService.deleteQueue(id);
    }
}