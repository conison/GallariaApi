package com.api.controller;

import com.api.model.Notification;
import com.api.service.NotificationService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/latest")
    public List<Notification> getLatestNotifications() {
        return notificationService.getLatest();
    }

    @PostMapping("/send")
    public void sendNotification(@RequestBody Notification notif) {
        notificationService.sendNotification(notif);
    }
}
