package pl.vm.aiworkshop.domain.notification;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SmsSender implements NotificationSender {

    @Override
    public void send(String message) {
        log.info("Sending SMS with message: {}", message);
    }
}
