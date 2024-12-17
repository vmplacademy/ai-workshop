package pl.vm.aiworkshop.domain.legacy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service for sending notifications.
 * <p></p>
 * It serves as an example of an existing, legacy service that can be used in the application.
 */
@Service
public class LegacyNotificationService {

    private static final Logger logger = LoggerFactory.getLogger(LegacyNotificationService.class);

    private final EmailSender emailSender;
    private final SmsSender smsSender;
    private final LetterSender letterSender;

    public LegacyNotificationService(EmailSender emailSender, SmsSender smsSender, LetterSender letterSender) {
        this.emailSender = emailSender;
        this.smsSender = smsSender;
        this.letterSender = letterSender;
    }

    void sendNotification(String message, String type) {
        if (message == null || message.isBlank() || type == null || type.isBlank()) {
            throw new IllegalArgumentException("Message and type must not be null or blank");
        }
        switch (type.toLowerCase()) {
            case "email" -> emailSender.sendEmail(message);
            case "sms" -> smsSender.sendSms(message);
            case "letter" -> letterSender.sendPost(message);
            default -> {
                logger.warn("Unknown notification type: {}", type);
                throw new IllegalArgumentException("Unknown notification type: " + type);
            }
        }
    }
}

