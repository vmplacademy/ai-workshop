package pl.vm.aiworkshop.domain.legacy;

import org.springframework.stereotype.Component;

public class SmsSender {

    public void sendSms(String message) {
        System.out.println("Sending SMS with message: " + message);
    }
}
