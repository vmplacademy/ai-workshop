package pl.vm.aiworkshop.domain.legacy;

import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LegacyNotificationService {

    private static final Logger logger = LoggerFactory.getLogger(LegacyNotificationService.class);

    private final Map<NotificationType, NotificationSender> notificationSenders;

    public LegacyNotificationService(Map<NotificationType, NotificationSender> notificationSenders) {
        this.notificationSenders = notificationSenders;
    }

    public void sendNotification(String message, NotificationType type) {
        Objects.requireNonNull(message, "Message cannot be null");
        Objects.requireNonNull(type, "Notification type cannot be null");

        NotificationSender sender = notificationSenders.get(type);
        if (sender != null) {
            sender.send(message);
        } else {
            logger.error("Unknown notification type: {}", type);
            throw new IllegalArgumentException("Unknown notification type: " + type);
        }
    }
}

