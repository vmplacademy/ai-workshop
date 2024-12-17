package pl.vm.aiworkshop.domain.legacy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationService {

    private final Map<NotificationType, NotificationSender> notificationSenders;

    @Async
    @Transactional
    public void sendNotification(String message, NotificationType type) {
        NotificationSender sender = notificationSenders.get(type);
        if (sender != null) {
            try {
                sender.send(message);
            } catch (Exception e) {
                log.error("Failed to send notification. Type: {}, Message: {}, Exception: {}", type, message, e.getMessage());
            }
        } else {
            log.warn("Unknown notification type: {}", type);
        }
    }
}
