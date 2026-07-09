package org.example.dto;

import lombok.Data;

@Data
public class CreateTopicRequest {

    private String topicName;
    private int partitions;
    private short replicationFactor;

}