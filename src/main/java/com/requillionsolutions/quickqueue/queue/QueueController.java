package com.requillionsolutions.quickqueue.queue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(path = "/api/v1/queues",  produces = {MediaType.APPLICATION_JSON_VALUE})
public class QueueController {

    private final QueueService queueService;

    @Operation(summary = "List all queues")
    @GetMapping
    public ResponseEntity<List<QueueGetDTO>> getQueues(
    ) {
        log.info("Get all queues");

        List<QueueGetDTO> response = queueService.getAllQueues();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Fetch Queue")
    @GetMapping("/{id}")
    public ResponseEntity<QueueGetDTO> getQueue(
            @Parameter(description = "Id of Queue", required = true) @PathVariable("id") Long id
    ) {
        log.info("Get Queue {}", id);

        QueueGetDTO response = queueService.fetchQueue(id);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Create a queue")
    @PostMapping
    public ResponseEntity<QueueGetDTO> createQueue(
            @Parameter(description = "Details of the queue to be created", required = true) @RequestBody QueuePostDTO dto
    ) {
        log.info("Create queue");

        QueueGetDTO response = queueService.createQueue(dto);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update queue")
    @PutMapping("/{id}")
    public ResponseEntity<QueueGetDTO> updateQueue(
            @Parameter(description = "Id of queue", required = true) @PathVariable("id") Long id,
            @Parameter(description = "Details of the queue to be updated", required = true) @RequestBody QueuePostDTO dto
    ) {
        log.info("Update queue {}", id);

        QueueGetDTO response = queueService.updateQueue(id, dto);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete queue")
    @DeleteMapping
    public ResponseEntity<Void> deleteQueue(
            @Parameter(description = "Id of queue", required = true) @PathVariable("id") Long id
    ) {
        log.info("Delete queue {}", id);

        queueService.deleteQueue(id);

        return ResponseEntity.ok().build();
    }
}
