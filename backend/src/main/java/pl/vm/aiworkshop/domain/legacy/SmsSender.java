package pl.vm.aiworkshop.domain.legacy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SmsSender {

    private static final Logger logger = LoggerFactory.getLogger(SmsSender.class);

    void sendSms(String message) {
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Message must not be null or blank");
        }
        logger.info("Sending SMS with message: {}", message);
    }
}
