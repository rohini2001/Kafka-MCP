package org.example.controller;
import  org.example.service.*;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaService kafkaService;

    @GetMapping("/kafka/topics")
    public Set<String> getTopics() {

        return kafkaService.getTopics();
    }
}