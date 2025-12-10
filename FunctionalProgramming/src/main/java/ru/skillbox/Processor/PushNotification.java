package ru.skillbox.Processor;

import ru.skillbox.notification.Notification;
import ru.skillbox.notification_sender.NotificationSender;

import java.util.List;

public class PushNotification implements Notification, NotificationSender {
    private String subject;
    private String receivers;
    private String message;

    public PushNotification() {
    }

    public PushNotification(String subject, String receiver, String message) {
        this.subject = subject;
        this.receivers = receiver;
        this.message = message;
    }

    public String getTitle() {
        return subject;
    }

    public void setTitle(String title) {
        this.subject = title;
    }

    public String getReceiver() {
        return receivers;
    }

    public void setReceiver(String receiver) {
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
        return "\nPUSH" +
                "\ntitle: " + subject +
                "\nreceiver: " + receivers +
                "\nmessage: " + "👋 " + message; // it doesn't work for me: "\ud83d\udc4b"
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
