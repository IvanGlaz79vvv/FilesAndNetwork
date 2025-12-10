package ru.skillbox.Processor;

import ru.skillbox.notification.Notification;
import ru.skillbox.notification_sender.NotificationSender;

import java.util.List;

public class SmsNotification implements Notification, NotificationSender {
    private String subject;
    private String receivers;
    private String message;

    public SmsNotification() {
    }

    public SmsNotification(String subject, String receivers, String message) {
        this.subject = subject;
        this.receivers = receivers;
        this.message = message;
    }

    public String getReceivers() {
        return receivers;
    }

    public void setReceivers(String receivers) {
        this.receivers = receivers;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return форматированные тело сообщений
     */
    @Override
    public String formattedMessage() {
        return "\nSMS" +
                "\nreceivers: " + receivers +
                "\nmessage: " + message;
    }

    /**
     * Отправить одно уведомление
     *
     * @param notification уведомление
     */
    @Override
    public void send(Notification notification) {
        System.out.println(notification.formattedMessage());
    }

    /**
     * Отправка множества уведомлений
     *
     * @param notifications список уведомлений
     */
    @Override
    public void send(List notifications) {

    }
}
