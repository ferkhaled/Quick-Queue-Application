package com.requillionsolutions.quickqueue.queueentry;

import com.requillionsolutions.quickqueue.queue.QueueEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface QueueEntryRepo extends CrudRepository<QueueEntryEntity, Long> {

    public List<QueueEntryEntity> findByQueue_Id(Long qid);

    @Query("SELECT MIN(qn.position) FROM QueueEntryEntity qn WHERE qn.queue.id = ?1")
    public Optional<Integer> findNextPositionByQueueId(Long qid);

    @Query("SELECT MAX(qn.position) FROM QueueEntryEntity qn WHERE qn.queue.id = ?1")
    public Optional<Integer> findLastPositionByQueueId(Long qid);

    public Optional<QueueEntryEntity> findByQueue_IdAndPosition(Long qid, int position);

    public Optional<QueueEntryEntity> findByQueue_IdAndId(Long qid, Long id);

    public void deleteByQueue_IdAndId(Long qid, Long id);
}
