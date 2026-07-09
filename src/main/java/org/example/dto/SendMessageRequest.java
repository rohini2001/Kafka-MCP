package org.example.dto;

import lombok.Data;

@Data
public class SendMessageRequest {

    private String topic;
    private String message;

}