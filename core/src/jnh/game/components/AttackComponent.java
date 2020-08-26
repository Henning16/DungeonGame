package jnh.game.components;

import jnh.game.assets.Tags;
import jnh.game.components.items.ItemContainerComponent;
import jnh.game.components.items.WeaponComponent;
import jnh.game.gameObjects.GameObject;
import jnh.game.gameObjects.GameObjectManager;
import jnh.game.gameObjects.ID;

import java.util.ArrayList;

/**
 * This component will let the game object try to attack a game object from a certain group of game objects.
 * However, it wont follow the target, combine it with the {@link FollowComponent}, if you want to achieve that.
 * These game objects are defined by the parameter "targetedTags", which is a list of tags.
 * If one game object has one or more of these specified tags he will be a possible target.
 * The game object will choose the nearest target or if it already has one, it will keep that one.
 * This component works similar to the {@link FollowComponent}.
 * If the game object should use a weapon, a {@link ItemContainerComponent} is required.
 * If it exists and there is a item with a {@link WeaponComponent} at the first slot in the ItemContainerComponent this
 * weapon will be utilized, otherwise the weapon which is specified using the parameter "hand" will be used.
 */
public class AttackComponent extends Component {

    private ArrayList<Tags> targetedTags = new ArrayList<>();

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
        for (Tags tag: targetedTags) {
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
        if(hand == null) {
            hand = new WeaponComponent();
        }
    }

    @Override
    public AttackComponent copy() {
        AttackComponent c = new AttackComponent();
        c.targetedTags = targetedTags;
        c.hand = hand;
        return c;
    }
}
