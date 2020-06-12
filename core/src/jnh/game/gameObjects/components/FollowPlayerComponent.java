package jnh.game.gameObjects.components;

import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.GameObject;

public class FollowPlayerComponent extends Component {

    private GameObject player;
    private MovementComponent movementComponent;

    private float followDistance = 3f;

    private boolean idleing = true;
    @Override
    public void set(String[] parameters) throws Exception {
        followDistance = (parameters[0] != null) ? Float.parseFloat(parameters[0]) : followDistance;
    }

    @Override
    public void tick(float delta) {
        if(movementComponent == null || player == null) {
            movementComponent = (MovementComponent) gameObject.getComponent(MovementComponent.class);
            player = gameObject.getGameObjectManager().getGameObject(gameObject.getGameObjectManager().playerID);
            return;
        }
        if(Vector2.dst(player.getX(), player.getY(), gameObject.getX(), gameObject.getY()) > followDistance) {
            if(Math.random() < 0.03f) {
                if(Math.random() < 0.4f) {
                    idleing = !true;
                } else {
                    movementComponent.move((int) (Math.random() * 4));
                }
            } else if(!idleing) {
                movementComponent.move(movementComponent.getLooking());
            }
        } else if(Vector2.dst(player.getX(), player.getY(), gameObject.getX(), gameObject.getY()) > 1.2f){
            float[] distances = new float[] {player.getY() - gameObject.getY(), player.getX() - gameObject.getX(), gameObject.getY() - player.getY(), gameObject.getX() - player.getX()};
            float maxDistance = 0;
            int direction = 0;
            for(int i = 0; i < 4; i++) {
                if(distances[i] > maxDistance) {
                    maxDistance = distances[i];
                    direction = i;
                }
            }
            movementComponent.move(direction);
        }
    }

    @Override
    public void render() {

    }

    @Override
    public void remove() {

    }

    @Override
    public Component copy() {
        FollowPlayerComponent c = new FollowPlayerComponent();
        c.followDistance = followDistance;
        return c;
    }
}
