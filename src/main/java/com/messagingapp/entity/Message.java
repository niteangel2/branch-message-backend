package com.messagingapp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "app")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "message_body", length = 1000)
    private String messageBody;

    @Column(name = "response", length = 1000)
    private String response;

    @Column(name = "status")
    private String status = "UNREAD"; // UNREAD,RESOLVED

    public Message() {}

    public Message(Long userId, LocalDateTime timestamp, String messageBody) {
        this.userId = userId;
        this.timestamp = timestamp;
        this.messageBody = messageBody;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getMessageBody() { return messageBody; }
    public void setMessageBody(String messageBody) { this.messageBody = messageBody; }

    public String getResponse() { return response; }
    public void setResponse(String response) { this.response = response; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}