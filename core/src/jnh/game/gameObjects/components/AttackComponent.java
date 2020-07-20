package jnh.game.gameObjects.components;

import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.GameObject;
import jnh.game.gameObjects.GameObjectManager;
import jnh.game.gameObjects.ID;

import java.util.ArrayList;

public class AttackComponent extends Component {

    private ArrayList<String> targetedTags = new ArrayList<>();

    private transient GameObject target;
    private transient float remainingCooldownTime = 0f;

    @Override
    public void tick(float delta) {
        if(target == null ) {
            target = getNearestTarget();
            return;
        }
        if(target.isRemoved()) {
            target = null;
            return;
        }
        remainingCooldownTime = Math.max(0, remainingCooldownTime - delta);
        if(Vector2.dst(target.getX(), target.getY(), gameObject.getX(), gameObject.getY()) < 1.5f && remainingCooldownTime == 0f) {
            target.getComponent(HealthComponent.class).dealDamage(1, 1);
            target.getComponent(BodyComponent.class).getBody().applyForce(new Vector2(target.getX() - gameObject.getX(), target.getY() - gameObject.getY()).scl(150), new Vector2(target.getX(), target.getY()), true);
            remainingCooldownTime = 1f;
        }
    }

    public GameObject getNearestTarget() {
        GameObjectManager gameObjectManager = gameObject.getGameObjectManager();
        float smallestDistance2 = Float.MAX_VALUE;
        GameObject nearestTarget = null;
        for (String tag: targetedTags) {
            for (ID targetID: gameObjectManager.getGameObjectsByTag(tag)) {
                GameObject target = gameObjectManager.getGameObject(targetID);
                if(target != null) {
                    float dst2 = gameObject.getPosition().dst2(target.getPosition());
                    if (dst2 < smallestDistance2) {
                        smallestDistance2 = dst2;
                        nearestTarget = target;
                    }
                }
            }
        }

        return nearestTarget;
    }

    @Override
    public AttackComponent copy() {
        AttackComponent c = new AttackComponent();
        c.targetedTags = targetedTags;
        return c;
    }
}
