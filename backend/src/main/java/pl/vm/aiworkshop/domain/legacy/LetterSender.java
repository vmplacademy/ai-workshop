package pl.vm.aiworkshop.domain.legacy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LetterSender implements NotificationSender {

    @Override
    public void send(String message) {
        log.info("Sending letter with message: {}", message);
    }
}