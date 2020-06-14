package jnh.game.gameObjects.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import jnh.game.gfx.Shake;

public class HealthComponent extends Component {

    private int health = 100;
    private int maxHealth = 100;

    private float colorFlashTimer = 0f;

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
        colorFlashTimer = Math.max(0, colorFlashTimer - 0.1f);
    }

    @Override
    public void render(Batch batch) {
        batch.setColor(new Color(1, 0, 0, colorFlashTimer));
        batch.draw(gameObject.getTexture(), gameObject.getX(), gameObject.getY(), gameObject.getWidth(), gameObject.getHeight());
    }

    @Override
    public void remove() {

    }

    @Override
    public HealthComponent copy() {
        HealthComponent c = new HealthComponent();
        c.maxHealth = maxHealth;
        return c;
    }

    public void dealDamage(int damage, float damageModifier) {
        health = Math.max(0, health - (int) (damage * damageModifier));
        if(gameObject.getType().equals("PLAYER")) {
            gameObject.getStage().getScreen().getGameCamera().shake(new Shake(0.2f, damage * damageModifier * 0.03f));
        }
        colorFlashTimer = 1f;
    }
}
