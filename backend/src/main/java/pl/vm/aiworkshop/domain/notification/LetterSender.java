package pl.vm.aiworkshop.domain.notification;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LetterSender implements NotificationSender {

    @Override
    public void send(String message) {
        log.info("Sending Letter with message: {}", message);
    }
}
