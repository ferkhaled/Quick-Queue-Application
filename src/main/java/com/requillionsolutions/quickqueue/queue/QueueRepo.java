package com.requillionsolutions.quickqueue.queue;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QueueRepo extends CrudRepository<QueueEntity, Long> {

    public List<QueueEntity> findAll();
}
