package jnh.game.gameObjects.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import jnh.game.assets.Assets;
import jnh.game.gameObjects.GameObject;
import jnh.game.gfx.Shake;

public class HealthComponent extends Component {

    private int health = -1;
    private int maxHealth = 100;

    private transient float colorFlashTimer = 0f;
    private transient float deathTimer = 0.4f;

    private transient ModifyDimensionComponent modifyDimensionComponent;

    @Override
    public void tick(float delta) {
        if(modifyDimensionComponent == null) {
            modifyDimensionComponent = gameObject.getComponent(ModifyDimensionComponent.class);
            return;
        }
        colorFlashTimer = Math.max(0, colorFlashTimer - (colorFlashTimer / 10) - 0.0001f);
        if(isDead()) {
            deathTimer = Math.max(0, deathTimer - delta);
        }
        if(deathTimer == 0f) {
            gameObject.getGameObjectManager().remove(gameObject.getID());
            gameObject.getGameObjectManager().destroyables.remove((Object) gameObject.getID());
        }
    }

    @Override
    public void render(Batch batch) {
        batch.setColor(new Color(1, 0, 0, colorFlashTimer));
        ((TransformDrawable)gameObject.getDrawable()).draw(
                batch, gameObject.getX(), gameObject.getY(),
                gameObject.getOriginX() - gameObject.getImageX(), gameObject.getOriginY() - gameObject.getImageY(),
                gameObject.getImageWidth(), gameObject.getImageHeight(), gameObject.getScaleX(), gameObject.getScaleY(), gameObject.getRotation());
        if(isDead()) {
            colorFlashTimer = 0f;
            gameObject.setRotation(- deathTimer * 100);
            gameObject.setColor(new Color(1, 0, 0, deathTimer * 2));
        }
    }

    @Override
    public void attachedTo(GameObject gameObject) {
        super.attachedTo(gameObject);
        gameObject.getGameObjectManager().destroyables.add(gameObject.getID());
        if(health == -1) {
            health = maxHealth;
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
        modifyDimensionComponent.deform(new Vector2(1.2f, 1.2f), 0.3f);
        if(gameObject.getType().equals("PLAYER")) {
            gameObject.getStage().getScreen().getGameCamera().shake(new Shake(0.1f, 1));
        } else {
            long soundID = Assets.sounds.ENEMY_HIT.play();
            Assets.sounds.ENEMY_HIT.setPitch(soundID, (float) (0.3f * Math.random() + 1f));
            Assets.sounds.ENEMY_HIT.setVolume(soundID, 0.1f);
        }
        colorFlashTimer = 1f;
    }

    public boolean isDead() {
        return health == 0;
    }

}
