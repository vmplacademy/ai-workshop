package pl.vm.aiworkshop.domain.legacy;

import org.springframework.stereotype.Service;

@Service
class LetterSender {

    void sendPost(String message) {
        System.out.println("Sending letter with message: " + message);
    }
}
