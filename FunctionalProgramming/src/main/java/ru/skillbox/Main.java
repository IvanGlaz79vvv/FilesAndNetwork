package ru.skillbox;

import ru.skillbox.Processor.EmailNotification;
import ru.skillbox.Processor.PushNotification;
import ru.skillbox.Processor.SmsNotification;
import ru.skillbox.notification.Notification;

public class Main {
    public static void main(String[] args) {
        String subject = "Успешная регистрация!";
        String receiversEMAIL = "oleg@java.skillbox.ru, masha@java.skillbox.ru, yan@java.skillbox.ru";
        String receiversSMS = "+70001234567";
        String message = "Спасибо за регистрацию на сервисе!";
        String title = "Успешная регистрация!";
        String receiverPUSH = "o.yanovich";


        EmailNotification emailNotification = new EmailNotification();
        SmsNotification smsNotification = new SmsNotification();
        PushNotification pushNotification = new PushNotification();

        Notification emailNotification0 = new EmailNotification(subject, receiversEMAIL, message);
        Notification smsNotification0 = new SmsNotification(subject, receiversEMAIL, message);
        Notification pushNotification0 = new PushNotification(subject, receiversEMAIL, message);

        Notification emailNotification1 = new EmailNotification("Ты зарегистрирован!", "111@@java.skillbox.ru", "Отлично!");
        Notification smsNotification1 = new SmsNotification("Ты зарегистрирован!", "111@@java.skillbox.ru", "Отлично!");
        Notification pushNotification1 = new PushNotification("Ты зарегистрирован!", "111@@java.skillbox.ru", "Отлично!");

        Notification emailNotification2 = new EmailNotification("УРА!", "hello@@java.skillbox.ru", "Ты с нами!");
        Notification smsNotification2 = new SmsNotification("УРА!", "hello@@java.skillbox.ru", "Ты с нами!");
        Notification pushNotification2 = new PushNotification("УРА!", "hello@@java.skillbox.ru", "Ты с нами!");


        emailNotification.send(emailNotification0);
        smsNotification.send(smsNotification0);
        pushNotification.send(pushNotification0);
        System.out.println("<<<<<<<<<<<<<<<<  >>>>>>>>>>>>>>>");
        emailNotification.send(emailNotification1);
        smsNotification.send(smsNotification1);
        pushNotification.send(pushNotification1);
        System.out.println("<<<<<<<<<<<<<<<<  >>>>>>>>>>>>>>>");
        emailNotification.send(emailNotification2);
        smsNotification.send(smsNotification2);
        pushNotification.send(pushNotification2);

    }
}
