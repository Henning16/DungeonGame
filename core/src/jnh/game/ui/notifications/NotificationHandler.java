package jnh.game.ui.notifications;

import java.util.ArrayDeque;
import java.util.Queue;

public class NotificationHandler {

    private static NotificationTable table;
    private static Queue<Notification> notifications = new ArrayDeque<>();

    public static void addNotification(Notification notification) {
        notifications.add(notification);
        table.addNotification(new NotificationToast(notification));
    }

    public static Notification popNotification() {
        return notifications.poll();
    }

    public static void setTable(NotificationTable table) {
        NotificationHandler.table = table;
    }
}
