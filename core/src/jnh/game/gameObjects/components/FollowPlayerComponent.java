package jnh.game.gameObjects.components;

import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.GameObject;

public class FollowPlayerComponent extends Component {

    private transient GameObject player;
    private transient MovementComponent movementComponent;

    private float followDistance = 3f;


    private transient float directionChangeCooldown = 0f;
    private transient Vector2 wanderDestiny;
    private transient float wanderDestinyTimer = 9f;

    @Override
    public void tick(float delta) {
        if(movementComponent == null || player == null) {
            movementComponent = (MovementComponent) gameObject.getComponent(MovementComponent.class);
            player = gameObject.getGameObjectManager().getGameObject(gameObject.getGameObjectManager().playerID);
            return;
        }
        if(Vector2.dst(player.getX(), player.getY(), gameObject.getX(), gameObject.getY()) > followDistance || player.isRemoved()) {
            wanderArround(delta);
        } else if(Vector2.dst(player.getX(), player.getY(), gameObject.getX(), gameObject.getY()) > 1.2f){
            moveTowards(player.getX(), player.getY());
        }
        directionChangeCooldown = Math.max(0, directionChangeCooldown - delta);
    }

    @Override
    public Component copy() {
        FollowPlayerComponent c = new FollowPlayerComponent();
        c.followDistance = followDistance;
        return c;
    }


    private void wanderArround(float delta) {
        if((wanderDestiny == null || new Vector2(gameObject.getX(), gameObject.getY()).dst2(wanderDestiny) < 0.4f || wanderDestinyTimer < 0f)) {
            wanderDestiny = new Vector2((float) (gameObject.getX() - Math.random() * 10 + 5), (float) (gameObject.getY() - Math.random() * 10 + 5));
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

    //TODO move into seperate class when needed
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
            directionChangeCooldown = 0.7f * (1 / speedMultiplier);
        } else {
            direction = movementComponent.getLooking();
        }
        if((direction + 1) % 2 == 0) {
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
