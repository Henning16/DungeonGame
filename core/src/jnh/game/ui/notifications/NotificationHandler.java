package jnh.game.ui.notifications;

import java.util.ArrayDeque;
import java.util.Queue;

public class NotificationHandler {

    private Queue<Notification> notifications = new ArrayDeque<>();

    public NotificationHandler() {

    }

    public void addNotification(Notification notification) {
        notifications.add(notification);
    }

    public Notification popNotification() {
        return notifications.poll();
    }
}
