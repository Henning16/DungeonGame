package jnh.game.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import jnh.game.assets.Assets;
import jnh.game.gameObjects.GameObject;
import jnh.game.gameObjects.ID;
import jnh.game.utils.Direction;

public class WeaponComponent extends Component implements ItemAction {

    private int damage = 1;
    private float knockback = 0.2f;
    private float range = 3f;
    private float cooldown = 0.5f;
    private transient float cooldownCounter = 0;

    private transient float attackTimer = 0;
    private transient int looking = 0;
    private transient long currentSwingSound = -1;
    private transient long panTimer = 0;

    @Override
    public void tick(float delta) {
        attackTimer = Math.max(0, attackTimer - delta);
        cooldownCounter = Math.max(0, cooldownCounter - delta);
        if(currentSwingSound != -1) {
            panTimer += delta;
            Assets.sounds.WEAPON_SWING.setPan(currentSwingSound, panTimer * 4 - 1, 1);
        }
        if(panTimer > 0.5f) {
            currentSwingSound = -1;
            panTimer = 0;
        }
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
    public void render(Batch batch) {
        super.render(batch);
        if(attackTimer != 0) {
            batch.setColor(1, 1, 1, attackTimer * 4);
            switch(looking) {
                case Direction.LEFT:
                    batch.draw((TextureRegion) Assets.textures.ATTACK.getKeyFrame(0), gameObject.getX() - 0.3f, gameObject.getY(),
                            0.09375f, 0.375f, 0.1875f, 0.75f, 1, 1, 180);
                    break;
                case Direction.RIGHT:
                    batch.draw((TextureRegion) Assets.textures.ATTACK.getKeyFrame(0), gameObject.getX() + 0.4f, gameObject.getY(),
                            0.09375f, 0.375f, 0.1875f, 0.75f, 1, 1, 0);
                    break;
                case Direction.UP:
                    batch.draw((TextureRegion) Assets.textures.ATTACK.getKeyFrame(0), gameObject.getX() - 0.21f, gameObject.getY() + 0.5f,
                            0.09375f, 0.375f, 0.1875f, 0.75f, 1, 1, 90);
                    break;
                case Direction.DOWN:
                    batch.draw((TextureRegion) Assets.textures.ATTACK.getKeyFrame(0), gameObject.getX() + 0.34f, gameObject.getY() - 0.5f,
                            0.09375f, 0.375f, 0.1875f, 0.75f, 1, 1, 270);
                    break;
            }
            batch.setColor(1, 1, 1, 1);
        }
    }

    @Override
    public void use(GameObject user) {
        if(cooldownCounter != 0) {
            return;
        }
        attackTimer = 0.4f;
        long soundID = Assets.sounds.WEAPON_SWING.play();
        Assets.sounds.WEAPON_SWING.setPitch(soundID, (float) (0.85f + 0.3f * Math.random()));
        Assets.sounds.WEAPON_SWING.setVolume(soundID, 0.3f);
        looking = user.getComponent(MovementComponent.class).getLooking();
        for(ID id: user.getGameObjectManager().getGameObjectsByTag("destroyable")) {
            GameObject other = user.getGameObjectManager().getGameObject(id);
            if(!id.equals(user.getID()) && user.getPosition().dst2(other.getPosition()) < range * range) {
                if(user.getComponent(MovementComponent.class) != null) {
                    boolean inVision;
                    switch(looking) {
                        case Direction.LEFT:
                            inVision = other.getX() < user.getX();
                            break;
                        case Direction.RIGHT:
                            inVision = other.getX() > user.getX();
                            break;
                        case Direction.UP:
                            inVision = other.getY() > user.getY();
                            break;
                        case Direction.DOWN:
                            inVision = other.getY() < user.getY();
                            break;
                        default:
                            inVision = true;
                    }
                    if(inVision) {
                        other.getComponent(HealthComponent.class).dealDamage(damage, 1);
                        other.getComponent(BodyComponent.class).getBody().applyForce(
                                new Vector2(other.getX() - gameObject.getX(), other.getY() - gameObject.getY()).scl(200 * knockback),
                                new Vector2(other.getX(), other.getY()), true);
                    }
                }
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

    public float getAttackTimer() {
        return attackTimer;
    }

    public void setAttackTimer(float attackTimer) {
        this.attackTimer = attackTimer;
    }
}
