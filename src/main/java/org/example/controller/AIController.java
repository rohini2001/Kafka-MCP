package org.example.controller;

import  org.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    @Autowired
    private AIService aiService;

    @GetMapping("/generate")
    public String generateText(@RequestParam String prompt) {
        return aiService.askAI(prompt);
    }

}
//package org.example.controller;
//
// import lombok.RequiredArgsConstructor;
// import org.example.ai.KafkaAdminTools;
// import org.springframework.ai.chat.client.ChatClient;
// import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/ai")
//@RequiredArgsConstructor
//public class AIController {
//
//    private final ChatClient chatClient;
//    private final KafkaAdminTools kafkaAdminTools;
//
//    @PostMapping("/chat")
//    public String chat(@RequestBody String prompt) {
//
//        return chatClient.prompt(prompt)
//                .tools(kafkaAdminTools)
//                .call()
//                .content();
//
//    }
//
//}