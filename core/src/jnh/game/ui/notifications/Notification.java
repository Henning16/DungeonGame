package jnh.game.ui.notifications;

public class Notification {

    private String title = "Title";
    private String message = "Message";
    private Type type = Type.DEFAULT;

    public enum Type {
        DEFAULT, DEBUG, WARN, ERROR;
    }

    public Notification(String title, String message, Type type) {
        this.title = title;
        this.message = message;
        this.type = type;
    }
}
