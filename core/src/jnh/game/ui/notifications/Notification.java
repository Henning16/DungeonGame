package jnh.game.ui.notifications;

/**
 * Represents a notification, which gives the user debug / warning / error messages or other information.
 * A notification contains a title, a message and a type.
 * @see Notification.Type
 * @see NotificationTable
 */
public class Notification {

    private String title = "Title";
    private String message = "Message";
    private Type type = Type.DEFAULT;

    /**
     * The type of the notification changes how the notification is displayed.
     * Furthermore, different types of notifications can be filtered by the user.
     * <ul>
     * <li>{@code DEFAULT} should be used to represent plain messages, which don´t fall in any of the following categories.</li>
     * <li>{@code DEBUG} should be used if the notification gives information about an event, which has neither an negative impact
     * or any consequences as it was expected to take place.</li>
     * <li>{@code WARN} should be used to warn the user of an unexpected event, which could possibly have negative consequences.</li>
     * <li>{@code ERROR} may be used to notify the user that an unexpected event occured, which has harmful consquences / could
     * cause further problems or could result in a dysfunction of certain systems or even in a crash.</li>
     * </ul>
     */
    public enum Type {
        DEFAULT, DEBUG, WARN, ERROR;
    }

    /**
     * Creates a copy of the provided notification.
     * @param notification the notification, of which a copy will be created.
     * @see Notification#Notification(String, String)
     * @see Notification#Notification(String, String, Type)
     */
    public Notification(Notification notification) {
        this.title = notification.title;
        this.message = notification.message;
        this.type = notification.type;
    }

    /**
     * Instantiates a new notification using the given parameters.
     * The {@link Type type} will default to {@code DEFAULT}.
     * @param title the tile of the notification
     * @param message the message of the notification
     * @see Notification#Notification(Notification)
     * @see Notification#Notification(String, String, Type)
     */
    public Notification(String title, String message) {
        this.title = title;
        this.message = message;
    }

    /**
     * Constructs a new notification using the given parameters.
     * @param title the title of the notification
     * @param message the message of the notification
     * @param type the type of the notification
     * @see Notification#Notification(Notification)
     * @see Notification#Notification(String, String)
     */
    public Notification(String title, String message, Type type) {
        this.title = title;
        this.message = message;
        this.type = type;
    }

    /**
     * Returns the title of the notification.
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the notification.
     * @param title the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the message of the notification.
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message of the notification.
     * @param message the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the type of the notification.
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the type of the notification.
     * @param type the new type
     */
    public void setType(Type type) {
        this.type = type;
    }
}
