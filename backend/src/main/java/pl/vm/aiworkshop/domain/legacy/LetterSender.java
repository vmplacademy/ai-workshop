package pl.vm.aiworkshop.domain.legacy;

import org.springframework.stereotype.Component;

@Component
public class LetterSender implements NotificationSender {
    @Override
    public void send(String message) {
        System.out.println("Sending letter with message: " + message);
    }
}
