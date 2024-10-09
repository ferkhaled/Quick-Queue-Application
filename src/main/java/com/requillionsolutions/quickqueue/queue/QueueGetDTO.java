package com.requillionsolutions.quickqueue.queue;

import lombok.Data;

@Data
public class QueueGetDTO {

    private Long id;
    private String name;

    public QueueGetDTO(QueueEntity app) {
        this.id = app.getId();
        this.name = app.getName();
    }
}
