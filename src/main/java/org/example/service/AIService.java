package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class AIService {

    private final ChatClient chatClient;
    private final KafkaAdminService kafkaAdminService;
    private final KafkaAdminTools kafkaAdminTools;

    // Spring AI automatically configures a ChatClient.Builder for Gemini
    public AIService(ChatClient.Builder builder, KafkaAdminService kafkaAdminService, KafkaAdminTools kafkaAdminTools) {
        this.chatClient = builder.build();
        this.kafkaAdminService = kafkaAdminService;
        this.kafkaAdminTools = kafkaAdminTools;
    }

    public String askAI(String prompt) {
        log.info("========== AI REQUEST ==========");

        log.info("Prompt     : {}", prompt);

        long start = System.currentTimeMillis();
        String response = chatClient
                .prompt(prompt)
                .tools(kafkaAdminTools)
                .call()
                .content();

        long end = System.currentTimeMillis();

        log.info("Response Time : {} ms", (end - start));
        log.info("Response      : {}", response);
        log.info("===============================");

        return response;
    }

}
