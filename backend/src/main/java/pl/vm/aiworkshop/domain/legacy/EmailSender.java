package pl.vm.aiworkshop.domain.legacy;

import org.springframework.stereotype.Component;

@Component
public class EmailSender implements NotificationSender {
    @Override
    public void send(String message) {
        System.out.println("Sending email with message: " + message);
    }
}
