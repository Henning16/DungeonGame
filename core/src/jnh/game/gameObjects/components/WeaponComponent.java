package jnh.game.gameObjects.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.GameObject;

public class WeaponComponent extends Component implements ItemAction {

    private int damage = 1;
    private float knockback = 0.2f;
    private float range = 3f;
    private float cooldown = 0.5f;
    private transient float cooldownCounter = 0f;

    @Override
    public void tick(float delta) {
        cooldownCounter = Math.max(0, cooldownCounter - delta);
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
        if(cooldownCounter != 0.0f) {
            return;
        }
        Vector2 pos = user.getStage().convertScreenPositionToWorldPosition(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
        for(Integer i: user.getGameObjectManager().destroyables) {
            GameObject other = user.getGameObjectManager().getGameObject(i);
            if(i != user.getID() && user.getPosition().dst2(other.getPosition()) < range * range) {
                ((HealthComponent) other.getComponent(HealthComponent.class)).dealDamage(damage, 1f);
                other.getComponent(BodyComponent.class).getBody().applyForce(
                        new Vector2(other.getX() - gameObject.getX(), other.getY() - gameObject.getY()).scl(1000 * knockback),
                        new Vector2(other.getX(), other.getY()), true);
            }
        }
        cooldownCounter = cooldown;
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
