package com.requillionsolutions.quickqueue.queueentry;

import lombok.Data;

@Data
public class QueueEntryGetDTO {

    private Long id;
    private String name;
    private int position;

    public QueueEntryGetDTO(QueueEntryEntity entry) {
        this.id = entry.getId();
        this.name = entry.getName();
        this.position = entry.getPosition();
    }
}
