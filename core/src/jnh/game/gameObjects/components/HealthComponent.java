package jnh.game.gameObjects.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import jnh.game.gameObjects.GameObject;
import jnh.game.gfx.Shake;

public class HealthComponent extends Component {

    private int health = 100;
    private int maxHealth = 100;

    private float colorFlashTimer = 0f;
    private float deathTimer = 0.4f;

    @Override
    public void set(String[] parameters) throws Exception {
        maxHealth = (parameters[0] != null) ? Integer.parseInt(parameters[0]) : maxHealth;
        health = maxHealth;
    }

    @Override
    public String[] get() {
        String[] parameters = new String[2];
        parameters[0] = String.valueOf(maxHealth);
        parameters[1] = String.valueOf(health);
        return parameters;
    }

    @Override
    public void tick(float delta) {
        colorFlashTimer = Math.max(0, colorFlashTimer - (colorFlashTimer / 10) - 0.0001f);
        if(isDead()) {
            deathTimer = Math.max(0, deathTimer - delta);
        }
        if(deathTimer == 0f) {
            gameObject.getGameObjectManager().remove(gameObject.getID());
        }
    }

    @Override
    public void render(Batch batch) {
        batch.setColor(new Color(1, 0, 0, colorFlashTimer));
        batch.draw(gameObject.getTexture(), gameObject.getX(), gameObject.getY(), gameObject.getWidth(), gameObject.getHeight());

        if(isDead()) {
            colorFlashTimer = 0f;
            gameObject.setRotation(deathTimer * 100);
            gameObject.setColor(new Color(1, 0, 0, deathTimer));
        }
    }

    @Override
    public void remove() {

    }

    @Override
    public HealthComponent copy() {
        HealthComponent c = new HealthComponent();
        c.maxHealth = maxHealth;
        c.health = health;
        return c;
    }

    @Override
    public void attachedTo(GameObject gameObject) {
        super.attachedTo(gameObject);
        gameObject.getGameObjectManager().destroyables.add(gameObject.getID());
    }

    public void dealDamage(int damage, float damageModifier) {
        health = Math.max(0, health - (int) (damage * damageModifier));
        if(gameObject.getType().equals("PLAYER")) {
            gameObject.getStage().getScreen().getGameCamera().shake(new Shake(0.2f, damage * damageModifier * 0.03f));
        }
        colorFlashTimer = 1f;
    }

    public boolean isDead() {
        return health == 0;
    }
}
