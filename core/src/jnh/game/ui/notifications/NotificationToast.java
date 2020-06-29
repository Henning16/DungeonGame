package jnh.game.ui.notifications;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import jnh.game.assets.Assets;
import jnh.game.settings.Settings;
import jnh.game.ui.Styles;

public class NotificationToast extends Table {

    private Notification notification;
    private float timeRemaining = 4f;
    private boolean removeActionsInitiated = false;

    public NotificationToast(Notification notification) {
        this.notification = notification;
        left();
        NinePatch background = new NinePatch(Assets.uiTextures.BUTTON_UP, 5, 5, 6, 5);
        background.scale(2 * Settings.getUIScale(), 2 * Settings.getUIScale());
        setBackground(new NinePatchDrawable(background));
        add(new Label(notification.getTitle(), Styles.label)).padLeft(0).left().top();
        row();
        Label label = new Label(notification.getMessage(), Styles.text);
        label.setWrap(true);
        add(label).padTop(8 * Settings.getUIScale()).width(184 * Settings.getUIScale());
        addNotification();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        timeRemaining = timeRemaining - delta;
        if(timeRemaining <= -5) {
            getParent().removeActor(this);
        } else if(timeRemaining <= 0 && !removeActionsInitiated) {
            addAction(Actions.fadeOut(0.5f));
            addAction(Actions.moveBy(75 * Settings.getUIScale(), 0, 0.5f));
            removeActionsInitiated = true;
        }
    }

    public void addNotification() {
        getColor().a = 0;
        addAction(Actions.fadeIn(0.5f));
    }

    public void removeNotification() {
    }
}
