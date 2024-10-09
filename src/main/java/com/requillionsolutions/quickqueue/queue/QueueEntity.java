package com.requillionsolutions.quickqueue.queue;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "queues")
@Getter
@Setter
public class QueueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "queue_generator")
    @SequenceGenerator(name = "queue_generator", sequenceName = "queue_seq", initialValue = 1, allocationSize=10)
    private Long id;

    @Column(name = "NAME")
    private String name;
}
