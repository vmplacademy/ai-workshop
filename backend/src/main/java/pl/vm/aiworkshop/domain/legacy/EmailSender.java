package pl.vm.aiworkshop.domain.legacy;

import org.springframework.stereotype.Component;

public class EmailSender  {

    public void sendEmail(String message) {
        System.out.println("Sending email with message: " + message);
    }
}
