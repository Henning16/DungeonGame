package jnh.game.gameObjects.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.GameObject;

public class WeaponComponent extends Component implements ItemAction {

    private int damage = 1;
    private float knockback = 0.2f;
    private float range = 3f;
    private float cooldown = 10f;
    private float cooldownCounter = 0f;

    @Override
    public void set(String[] parameters) throws Exception {
        damage = (parameters[0] != null) ? Integer.parseInt(parameters[0]) : damage;
        knockback = (parameters[1] != null) ? Float.parseFloat(parameters[1]) : knockback;
        range = (parameters[2] != null) ? Float.parseFloat(parameters[2]) : range;
        cooldown = (parameters[3] != null) ? Float.parseFloat(parameters[3]) : cooldown;
    }

    @Override
    public String[] get() {
        String[] parameters = new String[4];
        parameters[0] = String.valueOf(damage);
        parameters[1] = String.valueOf(knockback);
        parameters[2] = String.valueOf(range);
        parameters[3] = String.valueOf(cooldown);
        return parameters;
    }

    @Override
    public void tick(float delta) {
        cooldownCounter = Math.max(0f, cooldownCounter - delta);
    }

    @Override
    public void render(Batch batch) {

    }

    @Override
    public void remove() {

    }

    @Override
    public WeaponComponent copy() {
        WeaponComponent c = new WeaponComponent();
        c.damage = damage;
        c.knockback = knockback;
        c.range = range;
        c.cooldown = cooldown;
        return c;
    }

    @Override
    public void use(GameObject user) {
        if(cooldownCounter != 0) {
            return;
        }
        Vector2 pos = user.getStage().convertScreenPositionToWorldPosition(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
        for(Integer i: user.getGameObjectManager().destroyables) {
            GameObject other = user.getGameObjectManager().getGameObject(i);
            if(i != user.getID() && user.getPosition().dst2(other.getPosition()) < range * range) {
                ((HealthComponent) other.getComponent(HealthComponent.class)).dealDamage(damage, 1f);
            }
        }
    }

    @Override
    public void secondaryUse(GameObject user) {

    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public float getKnockback() {
        return knockback;
    }

    public void setKnockback(float knockback) {
        this.knockback = knockback;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public float getCooldown() {
        return cooldown;
    }

    public void setCooldown(float cooldown) {
        this.cooldown = cooldown;
    }
}
