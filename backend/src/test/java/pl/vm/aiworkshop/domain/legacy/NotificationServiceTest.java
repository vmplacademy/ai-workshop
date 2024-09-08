package pl.vm.aiworkshop.domain.legacy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private EmailSender emailSender;

    @Mock
    private SmsSender smsSender;

    @Mock
    private LetterSender letterSender;

    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        notificationService = new NotificationService(
                Map.of(
                        NotificationType.EMAIL, emailSender,
                        NotificationType.SMS, smsSender,
                        NotificationType.LETTER, letterSender
                )
        );
    }

    @Test
    void sendsEmailWhenTypeIsEmail() {
        // given
        String message = "Test email message";

        // when
        notificationService.sendNotification(message, NotificationType.EMAIL);

        // then
        verify(emailSender).send(message);
    }

    @Test
    void sendsSmsWhenTypeIsSms() {
        // given
        String message = "Test SMS message";

        // when
        notificationService.sendNotification(message, NotificationType.SMS);

        // then
        verify(smsSender).send(message);
    }

    @Test
    void sendsLetterWhenTypeIsLetter() {
        // given
        String message = "Test letter message";

        // when
        notificationService.sendNotification(message, NotificationType.LETTER);

        // then
        verify(letterSender).send(message);
    }

    @Test
    void throwNullPointerExceptionWhenTypeIsUnknown() {
        // given
        String message = "Test unknown message";

        // when
        Throwable throwable = catchThrowable(
                () -> notificationService.sendNotification(message, null)
        );

        // then
        assertThat(throwable).isInstanceOf(NullPointerException.class);

        verifyNoInteractions(emailSender, smsSender, letterSender);
    }
}