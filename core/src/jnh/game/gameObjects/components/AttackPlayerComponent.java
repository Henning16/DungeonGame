package jnh.game.gameObjects.components;

import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.GameObject;

public class AttackPlayerComponent extends Component {

    private transient GameObject player;
    private transient float remainingCooldownTime = 0f;

    @Override
    public void tick(float delta) {
        if(player == null ) {
            player = gameObject.getGameObjectManager().getGameObject(gameObject.getGameObjectManager().playerID);
            return;
        }
        if(player.isRemoved()) {
            return;
        }
        remainingCooldownTime = Math.max(0, remainingCooldownTime - delta);
        if(Vector2.dst(player.getX(), player.getY(), gameObject.getX(), gameObject.getY()) < 1.5f && remainingCooldownTime == 0f) {
            player.getComponent(HealthComponent.class).dealDamage(1, 1);
            player.getComponent(BodyComponent.class).getBody().applyForce(new Vector2(player.getX() - gameObject.getX(), player.getY() - gameObject.getY()).scl(150), new Vector2(player.getX(), player.getY()), true);
            remainingCooldownTime = 1f;
        }
    }

    @Override
    public AttackPlayerComponent copy() {
        AttackPlayerComponent c = new AttackPlayerComponent();
        return c;
    }
}
