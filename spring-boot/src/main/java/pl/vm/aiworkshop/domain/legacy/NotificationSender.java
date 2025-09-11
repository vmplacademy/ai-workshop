package pl.vm.aiworkshop.domain.legacy;

/**
 * Interface for sending notifications.
 */
public interface NotificationSender {

    /**
     * Sends a notification with the given message.
     *
     * @param message the message to be sent
     */
    void send(String message);
}