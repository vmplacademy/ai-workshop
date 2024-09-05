package pl.vm.aiworkshop.domain.legacy;

import org.springframework.stereotype.Service;

@Service
public class SmsSender {

    void sendSms(String message) {
        System.out.println("Sending SMS with message: " + message);
    }
}
