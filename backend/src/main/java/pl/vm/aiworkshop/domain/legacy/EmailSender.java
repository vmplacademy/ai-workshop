package pl.vm.aiworkshop.domain.legacy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    private static final Logger logger = LoggerFactory.getLogger(EmailSender.class);

    void sendEmail(String message) {
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Message must not be null or blank");
        }
        logger.info("Sending email with message: {}", message);
        System.out.println("Sending email with message: " + message);
    }
}
