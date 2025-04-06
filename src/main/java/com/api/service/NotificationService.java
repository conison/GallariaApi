package com.api.service;

import com.api.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    // Store only latest 5 notifications in memory
    private final LinkedList<Notification> notificationBuffer = new LinkedList<>();
    private static final int MAX_NOTIFICATIONS = 5;

    @Autowired
    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @PostConstruct
    public void initHardcodedNotifications() {
        notificationBuffer.add(new Notification("wf001", "Promotion Started for Host A", LocalDateTime.now()));
        notificationBuffer.add(new Notification("wf002", "Demise Completed for Host B", LocalDateTime.now()));
        notificationBuffer.add(new Notification("wf003", "Ping Check Running for Host C", LocalDateTime.now()));
        notificationBuffer.add(new Notification("wf004", "System Update Initiated for Host D", LocalDateTime.now()));
        notificationBuffer.add(new Notification("wf005", "Rollback Executed for Host E", LocalDateTime.now()));
    }

    public List<Notification> getLatest() {
        return new LinkedList<>(notificationBuffer);
    }

    public void sendNotification(Notification notif) {
        add(notif);
        messagingTemplate.convertAndSend("/topic/notifications", notif);
    }

    public void add(Notification notif) {
        if (notificationBuffer.size() >= MAX_NOTIFICATIONS) {
            notificationBuffer.removeLast();
        }
        notificationBuffer.addFirst(notif);
    }
}
