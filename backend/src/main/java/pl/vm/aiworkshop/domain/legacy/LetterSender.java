package pl.vm.aiworkshop.domain.legacy;

import org.springframework.stereotype.Component;

public class LetterSender {

    public void sendPost(String message) {
        System.out.println("Sending letter with message: " + message);
    }
}
