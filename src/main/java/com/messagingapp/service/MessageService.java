package com.messagingapp.service;

import com.messagingapp.entity.Message;
import com.messagingapp.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository repository;
    private boolean messagesImported = false;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public void ensureMessagesImported() {
        if (!messagesImported) {
            importMessagesFromCsv("C:\\Users\\shagu\\IdeaProjects\\messaging-app\\src\\main\\resources\\data.csv");
            messagesImported = true;
        }
    }

    public void importMessagesFromCsv(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                String[] parts = line.split(",");
                parts[1] = parts[1].replace(' ','T');
                Message message = new Message(
                        Long.parseLong(parts[0]),
                        LocalDateTime.parse(parts[1]),
                        parts[2]
                );
                repository.save(message);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to import messages from CSV", e);
        }
    }

    public void respondToMessage(Long id, String response) {
        Message message = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found"));

        message.setResponse(response);
        message.setStatus("RESOLVED");
        repository.save(message);
    }
}
