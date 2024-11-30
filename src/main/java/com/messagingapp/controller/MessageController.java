package com.messagingapp.controller;

import com.messagingapp.entity.Message;
import com.messagingapp.repository.MessageRepository;
import com.messagingapp.service.MessageService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Message Controller", description = "Manage messages and responses")
@Controller
public class MessageController {
    private final MessageRepository repository;
    private final MessageService messageService;

    public MessageController(MessageRepository repository,
                             MessageService messageService) {
        this.repository = repository;
        this.messageService = messageService;
    }

    @Operation(summary = "Home Page", description = "Displays unread messages")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved unread messages"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Message>> homePage(Model model) {
        messageService.ensureMessagesImported();

        List<Message> allMessages = repository.findAll();
        return ResponseEntity.ok(allMessages);
    }

    @Operation(summary = "Respond to Message", description = "Respond to a specific message by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message responded successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Message not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/respond/{id}")
    public ResponseEntity<String> respondToMessage(@PathVariable Long id,
                                   @RequestParam String response,
                                   RedirectAttributes redirectAttributes) {
        try {
            messageService.respondToMessage(id, response);
            return ResponseEntity.ok("Message responded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
        }
    }
}
