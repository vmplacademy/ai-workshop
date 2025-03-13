package pl.vm.aiworkshop.domain.legacy;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.vm.aiworkshop.domain.notification.EmailSender;
import pl.vm.aiworkshop.domain.notification.LetterSender;
import pl.vm.aiworkshop.domain.notification.NotificationSender;
import pl.vm.aiworkshop.domain.notification.SmsSender;

@Service
@RequiredArgsConstructor
public class LegacyNotificationService {

    private final SmsSender smsSender;
    private final EmailSender emailSender;
    private final LetterSender letterSender;

    public void send(String message, String type) {
        NotificationSender sender = switch (type) {
            case "SMS" -> smsSender;
            case "Email" -> emailSender;
            case "Letter" -> letterSender;
            default -> throw new IllegalArgumentException("Unknown notification type: " + type + ". Please provide a valid notification type.");
        };
        sender.send(message);
    }
}

