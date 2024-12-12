package pl.vm.aiworkshop.domain.legacy;

import org.springframework.stereotype.Component;

@Component
public class SmsSender implements NotificationSender {
    @Override
    public void send(String message) {
        System.out.println("Sending SMS with message: " + message);
    }
}
