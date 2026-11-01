package com.queueless.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.queueless.dto.QueueRequest;
import com.queueless.dto.QueueResponse;
import com.queueless.dto.QueueUpdateRequest;
import com.queueless.entity.Organization;
import com.queueless.entity.Queue;
import com.queueless.repository.OrganizationRepository;
import com.queueless.repository.QueueRepository;

@Service
public class QueueService {

    private final QueueRepository queueRepository;
    private final OrganizationRepository organizationRepository;

    public QueueService(
            QueueRepository queueRepository,
            OrganizationRepository organizationRepository) {
        this.queueRepository = queueRepository;
        this.organizationRepository = organizationRepository;
    }

    public QueueResponse createQueue(QueueRequest request) {

        Organization organization = organizationRepository
                .findById(request.getOrganizationId())
                .orElseThrow(() -> new RuntimeException("Organization not found"));

        Queue queue = new Queue(
                request.getName(),
                request.getDescription(),
                request.getStatus()
        );

        queue.setOrganization(organization);

        Queue savedQueue = queueRepository.save(queue);

        return new QueueResponse(
                savedQueue.getId(),
                savedQueue.getName(),
                savedQueue.getDescription(),
                savedQueue.getStatus(),
                savedQueue.getCreatedAt(),
                savedQueue.getOrganization().getId()
        );
    }

    // Get all queues
    public List<QueueResponse> getAllQueues() {

        List<Queue> queues = queueRepository.findAll();

        return queues.stream()
                .map(queue -> new QueueResponse(
                        queue.getId(),
                        queue.getName(),
                        queue.getDescription(),
                        queue.getStatus(),
                        queue.getCreatedAt(),
                        queue.getOrganization().getId()
                ))
                .toList();
    }

        // Get queues by organization ID
    public List<QueueResponse> getQueuesByOrganizationId(Long organizationId) {

        List<Queue> queues = queueRepository.findByOrganizationId(organizationId);

        return queues.stream()
                .map(queue -> new QueueResponse(
                        queue.getId(),
                        queue.getName(),
                        queue.getDescription(),
                        queue.getStatus(),
                        queue.getCreatedAt(),
                        queue.getOrganization().getId()
                ))
                .toList();
    }

    // Get a queue by ID
    public QueueResponse getQueueById(Long id) {

        Queue queue = queueRepository.findById(id).orElse(null);

        if (queue == null) {
            return null;
        }

        return new QueueResponse(
                queue.getId(),
                queue.getName(),
                queue.getDescription(),
                queue.getStatus(),
                queue.getCreatedAt(),
                queue.getOrganization().getId()
        );
    }

    // Update a queue
    public QueueResponse updateQueue(Long id, QueueUpdateRequest request) {

        Queue existingQueue = queueRepository.findById(id).orElse(null);

        if (existingQueue == null) {
            return null;
        }

        existingQueue.setName(request.getName());
        existingQueue.setDescription(request.getDescription());
        existingQueue.setStatus(request.getStatus());

        Queue updatedQueue = queueRepository.save(existingQueue);

        return new QueueResponse(
                updatedQueue.getId(),
                updatedQueue.getName(),
                updatedQueue.getDescription(),
                updatedQueue.getStatus(),
                updatedQueue.getCreatedAt(),
                updatedQueue.getOrganization().getId()
        );
    }

    // Delete a queue
    public void deleteQueue(Long id) {
        queueRepository.deleteById(id);
    }
}