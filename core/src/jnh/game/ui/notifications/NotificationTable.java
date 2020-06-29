package jnh.game.ui.notifications;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import jnh.game.settings.Settings;

public class NotificationTable extends Table {

    public NotificationTable() {
        right().bottom().pad(12 * Settings.getUIScale());
    }

    public void addNotification(NotificationToast notificationToast) {
        add(notificationToast).space(12 * Settings.getUIScale()).width(200 * Settings.getUIScale());
        row();
    }

}