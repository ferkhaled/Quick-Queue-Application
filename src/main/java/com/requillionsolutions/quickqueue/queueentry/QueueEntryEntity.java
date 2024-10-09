package com.requillionsolutions.quickqueue.queueentry;

import com.requillionsolutions.quickqueue.queue.QueueEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "queue_entries")
@Getter
@Setter
public class QueueEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "queue_entry_generator")
    @SequenceGenerator(name = "queue_entry_generator", sequenceName = "queue_entry_seq", initialValue = 1, allocationSize=10)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "POSITION")
    private int position;

    @ManyToOne
    @JoinColumn(name = "QUEUE_ID_FK")
    QueueEntity queue;
}
