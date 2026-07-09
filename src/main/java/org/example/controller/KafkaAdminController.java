package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.CreateTopicRequest;
import org.example.service.KafkaAdminService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class KafkaAdminController {

    private final KafkaAdminService kafkaAdminService;

    @GetMapping("/topics")
    public Set<String> getTopics() {
        return kafkaAdminService.getTopics();
    }

    @PostMapping("/topics")
    public String createTopic(
            @RequestBody CreateTopicRequest request) {

        return kafkaAdminService.createTopic(
                request.getTopicName(),
                request.getPartitions(),
                request.getReplicationFactor());
    }

    @DeleteMapping("/topics/{topicName}")
    public String deleteTopic(
            @PathVariable String topicName) {

        return kafkaAdminService.deleteTopic(topicName);
    }

    @GetMapping("/topics/{topicName}")
    public Object describeTopic(
            @PathVariable String topicName) {

        return kafkaAdminService.describeTopic(topicName);
    }
}