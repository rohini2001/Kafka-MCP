package org.example.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class KafkaService {

    private final AdminClient adminClient;

    public Set<String> getTopics() {

        try {
            return adminClient
                    .listTopics()
                    .names()
                    .get();

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to fetch Kafka topics",
                    e);
        }
    }
}