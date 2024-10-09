package com.requillionsolutions.quickqueue.queue;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QueueService {

    private final QueueRepo queueRepo;

    public QueueEntity getQueue(Long qid) {

        QueueEntity queue = queueRepo.findById(qid)
                .orElseThrow(() -> new RuntimeException("Queue not found"));
        return queue;
    }

    public List<QueueGetDTO> getAllQueues() {
        List<QueueEntity> queues = queueRepo.findAll();

        List<QueueGetDTO> dto = queues.stream()
                .map(queueEnty -> new QueueGetDTO(queueEnty))
                .toList();

        return dto;
    }

    public QueueGetDTO fetchQueue(Long id) {

        QueueEntity queue = getQueue(id);

        return new QueueGetDTO(queue);
    }

    public QueueGetDTO createQueue(QueuePostDTO dto) {

        QueueEntity queue = new QueueEntity();

        queue.setName(dto.getName());

        queue = queueRepo.save(queue);

        return new QueueGetDTO(queue);
    }

    public QueueGetDTO updateQueue(Long id, QueuePostDTO dto) {

        QueueEntity queue = getQueue(id);

        queue.setName(dto.getName());

        queue = queueRepo.save(queue);

        return new QueueGetDTO(queue);
    }

    public void deleteQueue(Long id) {

        queueRepo.deleteById(id);
    }
}
