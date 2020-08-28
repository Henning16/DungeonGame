package jnh.game.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import jnh.game.assets.Assets;
import jnh.game.assets.Tags;
import jnh.game.components.items.ItemContainerComponent;
import jnh.game.gameObjects.GameObject;
import jnh.game.gfx.ColorGrading;
import jnh.game.gfx.Shake;

public class HealthComponent extends Component {

    private int health = -1;
    private int maxHealth = 100;

    @Override
    public void attachedTo(GameObject gameObject) {
        super.attachedTo(gameObject);
        if(health == -1) {
            health = maxHealth;
        }
        if(gameObject.getType().equals("PLAYER")) {
            updateHealthBar();
        }
    }

    @Override
    public HealthComponent copy() {
        HealthComponent c = new HealthComponent();
        c.health = health;
        c.maxHealth = maxHealth;
        return c;
    }

    public void dealDamage(int damage, float damageModifier) {
        health = Math.max(0, health - (int) (damage * damageModifier));
        gameObject.addAction(new SequenceAction(Actions.scaleTo(1.2f, 1.2f, 0.05f), Actions.scaleTo(1, 1, 0.2f)));
        gameObject.addAction(new SequenceAction(Actions.color(new Color(1, 0, 0, 1), 0.05f), Actions.color(Color.WHITE, 0.3f)));
        if(gameObject.getType().equals("PLAYER")) {
            gameObject.getStage().getScreen().getGameCamera().shake(new Shake(0.1f, 1));
            updateHealthBar();
            if(health < 0.3f * maxHealth) {
                gameObject.getStage().getScreen().getColorGrader().transitionTo(ColorGrading.DANGER, 5000);
            } else {
                gameObject.getStage().getScreen().getColorGrader().transitionTo(ColorGrading.NORMAL, 5000);
            }
        } else {
            long soundID = Assets.sounds.ENEMY_HIT.play();
            Assets.sounds.ENEMY_HIT.setPitch(soundID, (float) (0.3f * Math.random() + 0.7f));
            Assets.sounds.ENEMY_HIT.setVolume(soundID, 0.1f);
        }
        if(isDead()) {
            if(gameObject.getType().equals("PLAYER")) {
                gameObject.getStage().getScreen().pause();
                gameObject.getStage().getScreen().getUI().showDeathScreen();
            } else {
                gameObject.addAction(Actions.rotateBy(-30, 0.2f, Interpolation.pow3Out));
                gameObject.addAction(new SequenceAction(Actions.alpha(0, 0.2f), new Action() {
                    @Override
                    public boolean act(float delta) {
                        gameObject.removeTag(Tags.destroyable);
                        gameObject.getGameObjectManager().remove(gameObject.getID());
                        return true;
                    }
                }));
            }
            if(gameObject.getComponent(ItemContainerComponent.class) != null) {
                gameObject.getComponent(ItemContainerComponent.class).ejectAll();
            }
        }
    }

    public void heal(int amount, float healModifier) {
        health = Math.max(maxHealth, health + (int) (amount * healModifier));
        updateHealthBar();
    }

    //TODO implement general approach so that every HealthComponent can point to its own progress bar
    private void updateHealthBar() {
        gameObject.getStage().getUI().updateHealthBar(0, maxHealth, health);
    }

    public boolean isDead() {
        return health == 0;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        updateHealthBar();
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        updateHealthBar();
    }
}
