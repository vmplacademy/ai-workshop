package pl.vm.aiworkshop.domain.legacy;

import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    void sendEmail(String message) {
        System.out.println("Sending email with message: " + message);
    }
}
