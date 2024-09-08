package pl.vm.aiworkshop.domain.legacy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationService {

    private final Map<NotificationType, NotificationSender> notificationSenders;

    @Transactional
    public void sendNotification(String message, NotificationType type) {
        NotificationSender sender = notificationSenders.get(type);
        if (sender != null) {
            sender.send(message);
        } else {
            log.warn("Unknown notification type: {}", type);
        }
    }
}