package jnh.game.components;

import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.GameObject;
import jnh.game.gameObjects.GameObjectManager;
import jnh.game.gameObjects.ID;

import java.util.ArrayList;

public class FollowComponent extends Component {

    private transient MovementComponent movementComponent;
    private transient GameObject target;

    private float followDistance = 3f;
    private ArrayList<String> targetedTags = new ArrayList<>();


    private transient float directionChangeCooldown = 0f;
    private transient Vector2 wanderDestiny;
    private transient float wanderDestinyTimer = 9f;

    @Override
    public void tick(float delta) {
        if(movementComponent == null) {
            movementComponent = gameObject.getComponent(MovementComponent.class);
            return;
        }
        if(target == null || Vector2.dst(target.getX(), target.getY(), gameObject.getX(), gameObject.getY()) > followDistance || target.isRemoved()) {
            target = getNearestTarget();
            if (target == null) {
                wanderAround(delta);
            }
        } else if(Vector2.dst(target.getX(), target.getY(), gameObject.getX(), gameObject.getY()) > 1.2f){
            moveTowards(target.getX(), target.getY());
        }
        directionChangeCooldown = Math.max(0, directionChangeCooldown - delta);
    }

    @Override
    public Component copy() {
        FollowComponent c = new FollowComponent();
        c.followDistance = followDistance;
        c.targetedTags = targetedTags;
        return c;
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

    private void wanderAround(float delta) {
        if((wanderDestiny == null || new Vector2(gameObject.getX(), gameObject.getY()).dst2(wanderDestiny) < 0.4f || wanderDestinyTimer < 0f)) {
            wanderDestiny = new Vector2((float) (gameObject.getX() - Math.random() * 10 - 5), (float) (gameObject.getY() - Math.random() * 10 - 5));
            wanderDestinyTimer = 9f;
        }
        if(wanderDestiny != null) {
            moveTowards(wanderDestiny, 0.5f);
            wanderDestinyTimer -= delta;
        }
    }

    private void moveTowards(Vector2 position) {
        moveTowards(position.x, position.y, 1f);
    }
    private void moveTowards(Vector2 position, float speedMultiplier) {
        moveTowards(position.x, position.y, speedMultiplier);
    }

    private void moveTowards(float x, float y) {
        moveTowards(x, y, 1f);
    }

    //TODO move into separate class when needed
    private void moveTowards(float x, float y, float speedMultiplier) {
        int direction;
        if(directionChangeCooldown == 0f) {
            float[] distances = new float[]{y - gameObject.getY(), x - gameObject.getX(), gameObject.getY() - y, gameObject.getX() - x};
            float maxDistance = 0;
            direction = 0;
            for(int i = 0; i < 4; i++) {
                if(distances[i] > maxDistance) {
                    maxDistance = distances[i];
                    direction = i;
                }
            }
            directionChangeCooldown = 0.7f / speedMultiplier;
        } else direction = movementComponent.getLooking();
        if(direction % 2 == 1) {
            if(Math.abs(y - gameObject.getY()) > 0.02f) {
                movementComponent.move((y - gameObject.getY() > 0) ? 0 : 2, 0.2f * speedMultiplier);
            }
        } else {
            if(Math.abs(x - gameObject.getX()) > 0.02f) {
                movementComponent.move((x - gameObject.getX() > 0) ? 1 : 3, 0.2f * speedMultiplier);
            }
        }
        movementComponent.move(direction, speedMultiplier);
    }
}
