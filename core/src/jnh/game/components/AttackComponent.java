package jnh.game.components;

import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.GameObject;
import jnh.game.gameObjects.GameObjectManager;
import jnh.game.gameObjects.ID;

import java.util.ArrayList;

public class AttackComponent extends Component {

    private ArrayList<String> targetedTags = new ArrayList<>();

    private transient GameObject target;
    private transient ItemContainerComponent itemContainerComponent;
    private WeaponComponent hand;

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
        if(itemContainerComponent == null) {
            itemContainerComponent = gameObject.getComponent(ItemContainerComponent.class);
        }
        hand.tick(delta);
        WeaponComponent weaponComponent = null;
        if(itemContainerComponent != null) {
            gameObject.getGameObjectManager().getGameObject(itemContainerComponent.getItem(0)).getComponent(WeaponComponent.class);
        }
        if(weaponComponent == null) {
            weaponComponent = hand;
            //TODO Way to define stats for attack with hand
        }
        weaponComponent.useOn(gameObject, target);
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
    public void attachedTo(GameObject gameObject) {
        super.attachedTo(gameObject);
        hand = new WeaponComponent();
    }

    @Override
    public AttackComponent copy() {
        AttackComponent c = new AttackComponent();
        c.targetedTags = targetedTags;
        return c;
    }
}
