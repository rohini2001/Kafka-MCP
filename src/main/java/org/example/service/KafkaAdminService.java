package org.example.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class KafkaAdminService {

    private final AdminClient adminClient;

    public Set<String> getTopics() {

        try {
            return adminClient.listTopics().names().get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String createTopic(String topic,
                              int partitions,
                              short replicationFactor) {

        try {

            NewTopic newTopic =
                    new NewTopic(
                            topic,
                            partitions,
                            replicationFactor);

            adminClient.createTopics(
                            Collections.singleton(newTopic))
                    .all()
                    .get();

            return "Topic Created";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String deleteTopic(String topic) {

        try {

            adminClient.deleteTopics(
                            Collections.singleton(topic))
                    .all()
                    .get();

            return "Topic Deleted";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TopicDescription describeTopic(
            String topic) {

        try {

            return adminClient
                    .describeTopics(
                            Collections.singleton(topic))
                    .allTopicNames()
                    .get()
                    .get(topic);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}