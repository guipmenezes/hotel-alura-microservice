package com.hotelalura;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "hotelalura", groupId = "foo")
    void listener(String data) {
        System.out.println("Listener received: " + data + "🫡");
    }
}
