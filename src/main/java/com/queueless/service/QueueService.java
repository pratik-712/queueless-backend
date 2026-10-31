package com.queueless.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.queueless.entity.Queue;
import com.queueless.repository.QueueRepository;
import com.queueless.dto.QueueRequest;
import com.queueless.dto.QueueResponse;
import com.queueless.dto.QueueUpdateRequest;

@Service 
public class QueueService {
    private final QueueRepository queueRepository;

    public QueueService(QueueRepository queueRepository){
        this.queueRepository=queueRepository;
    }

    public QueueResponse createQueue(QueueRequest request) {

    Queue queue = new Queue(
            request.getName(),
            request.getDescription(),
            request.getStatus()
    );

    Queue savedQueue = queueRepository.save(queue);

    return new QueueResponse(
            savedQueue.getId(),
            savedQueue.getName(),
            savedQueue.getDescription(),
            savedQueue.getStatus(),
            savedQueue.getCreatedAt()
    );
}

    public List<QueueResponse> getAllQueues() {

    List<Queue> queues = queueRepository.findAll();

    return queues.stream()
            .map(queue -> new QueueResponse(
                    queue.getId(),
                    queue.getName(),
                    queue.getDescription(),
                    queue.getStatus(),
                    queue.getCreatedAt()
            ))
            .toList();
    }

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
            queue.getCreatedAt()
    );
}

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
            updatedQueue.getCreatedAt()
    );
}
    public void deleteQueue(Long id) {
        queueRepository.deleteById(id);
    }
}


