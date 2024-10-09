package com.requillionsolutions.quickqueue.queueentry;

import com.requillionsolutions.quickqueue.queue.QueueEntity;
import com.requillionsolutions.quickqueue.queue.QueueRepo;
import com.requillionsolutions.quickqueue.queue.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QueueEntryService {

    private final QueueEntryRepo queueEntryRepo;
    private final QueueService queueService;

    public List<QueueEntryGetDTO> getAllQueueEntries(Long qid) {
        List<QueueEntryEntity> queueEntries = queueEntryRepo.findByQueue_Id(qid);

        List<QueueEntryGetDTO> dto = queueEntries.stream()
                .map(queueEntry -> new QueueEntryGetDTO(queueEntry))
                .toList();

        return dto;
    }

    public QueueEntryGetDTO getNextQueueEntry(Long qid) {

        Optional<Integer> nextPositionFound = queueEntryRepo.findNextPositionByQueueId(qid);

        if (nextPositionFound.isEmpty()) {
            return null;
        }

        QueueEntryEntity queueEntry = queueEntryRepo.findByQueue_IdAndPosition(qid, nextPositionFound.get())
                .orElseThrow(() -> new RuntimeException("Queue entry not found"));

        return new QueueEntryGetDTO(queueEntry);
    }

    public QueueEntryGetDTO addQueueEntry(Long qid, QueueEntryPostDTO dto) {

        int lastPosition = 0;

        Optional<Integer> lastPositionFound = queueEntryRepo.findLastPositionByQueueId(qid);

        if (lastPositionFound.isPresent()) {
            lastPosition = lastPositionFound.get();
        }

        QueueEntity queue = queueService.getQueue(qid);
        QueueEntryEntity queueEntry = new QueueEntryEntity();

        queueEntry.setName(dto.getName());
        queueEntry.setPosition(lastPosition + 1);
        queueEntry.setQueue(queue);

        queueEntry = queueEntryRepo.save(queueEntry);

        return new QueueEntryGetDTO(queueEntry);
    }

    public void useQueueEntry(Long qid, Long id) {
        Optional<QueueEntryEntity> entryFound = queueEntryRepo.findByQueue_IdAndId(qid, id);

        if (entryFound.isPresent()) {
            queueEntryRepo.delete(entryFound.get());
        }
    }

}
