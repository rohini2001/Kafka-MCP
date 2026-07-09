package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.SendMessageRequest;
import org.example.service.ProducerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/producer")
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @PostMapping("/send")
    public String sendMessage(
            @RequestBody SendMessageRequest request) {

        producerService.sendMessage(
                request.getTopic(),
                request.getMessage());

        return "Message Sent";
    }

}