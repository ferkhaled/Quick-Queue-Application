package com.requillionsolutions.quickqueue.queueentry;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(path = "/api/v1/queues/{qid}/queue-entries",  produces = {MediaType.APPLICATION_JSON_VALUE})
public class QueueEntryController {

    private final QueueEntryService queueEntryService;

    @Operation(summary = "List all queue entries")
    @GetMapping
    public ResponseEntity<List<QueueEntryGetDTO>> getQueueEntries(
            @Parameter(description = "Id of Queue", required = true) @PathVariable("qid") Long qid
    ) {
        log.info("Get all queues entries for queue {}", qid);

        List<QueueEntryGetDTO> response = queueEntryService.getAllQueueEntries(qid);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Fetch next queue entry")
    @GetMapping("/next")
    public ResponseEntity<QueueEntryGetDTO> getNextQueueEntry(
            @Parameter(description = "Id of queue", required = true) @PathVariable("qid") Long qid
    ) {
        log.info("Get next queue entry for queue {}", qid);

        QueueEntryGetDTO response = queueEntryService.getNextQueueEntry(qid);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Add a queue entry")
    @PostMapping
    public ResponseEntity<QueueEntryGetDTO> addQueueEntry(
            @Parameter(description = "Id of queue", required = true) @PathVariable("qid") Long qid,
            @Parameter(description = "Details of the queue to be created", required = true) @RequestBody QueueEntryPostDTO dto
    ) {
        log.info("Add queue entry for queue {}", qid);

        QueueEntryGetDTO response = queueEntryService.addQueueEntry(qid, dto);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Use queue entry")
    @DeleteMapping
    public ResponseEntity<Void> useQueueEntry(
            @Parameter(description = "Id of queue", required = true) @PathVariable("qid") Long qid,
            @Parameter(description = "Id of queue entry", required = true) @PathVariable("id") Long id
    ) {
        log.info("Use queue entry {} for queue {}", id, qid);

        queueEntryService.useQueueEntry(qid, id);

        return ResponseEntity.ok().build();
    }
}
