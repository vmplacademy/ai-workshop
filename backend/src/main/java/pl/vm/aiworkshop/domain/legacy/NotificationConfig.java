package pl.vm.aiworkshop.domain.legacy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class NotificationConfig {

    @Bean
    public Map<NotificationType, NotificationSender> notificationSenders(EmailSender emailSender, SmsSender smsSender, LetterSender letterSender) {
        return Map.of(
                NotificationType.EMAIL, emailSender,
                NotificationType.SMS, smsSender,
                NotificationType.LETTER, letterSender
        );
    }
}