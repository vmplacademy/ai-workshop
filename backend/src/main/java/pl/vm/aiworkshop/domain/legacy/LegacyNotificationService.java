package pl.vm.aiworkshop.domain.legacy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for sending notifications.
 * <p></p>
 * It serves as an example of an existing, legacy service that can be used in the application.
 */
@Service
public class LegacyNotificationService {

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private SmsSender smsSender;

    @Autowired
    private LetterSender letterSender;

    void sendNotification(String message, String type) {
        if (type.contains("email")) {
            emailSender.sendEmail(message);
        } else if (type.contains("sms")) {
            smsSender.sendSms(message);
        } else if (type.contains("letter")) {
            letterSender.sendPost(message);
        } else {
            System.out.println("Unknown notification type");
        }
    }
}

