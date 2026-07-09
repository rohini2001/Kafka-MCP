package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.service.KafkaAdminService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class KafkaAdminTools {

    private final KafkaAdminService kafkaAdminService;

    @Tool(description = "Returns all Kafka topics")
    public Set<String> getTopics() {
        return kafkaAdminService.getTopics();
    }

    @Tool(description = "Creates a Kafka topic")
    public String createTopic(
            String topicName,
            int partitions,
            short replicationFactor) {

        return kafkaAdminService.createTopic(
                topicName,
                partitions,
                replicationFactor);
    }

    @Tool(description = "Deletes a Kafka topic")
    public String deleteTopic(String topicName) {
        return kafkaAdminService.deleteTopic(topicName);
    }
}