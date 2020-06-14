package jnh.game.gameObjects.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import jnh.game.gameObjects.GameObject;

public class AttackPlayerComponent extends Component {

    private GameObject player;
    private float remainingCooldownTime = 0f;

    @Override
    public void set(String[] parameters) throws Exception {

    }

    @Override
    public String[] get() {
        return new String[0];
    }

    @Override
    public void tick(float delta) {
        if(player == null) {
            player = gameObject.getGameObjectManager().getGameObject(gameObject.getGameObjectManager().playerID);
        }
        //TODO use weapon stats
        remainingCooldownTime = Math.max(0, remainingCooldownTime - delta);
        if(Vector2.dst(player.getX(), player.getY(), gameObject.getX(), gameObject.getY()) < 1.5f && remainingCooldownTime == 0f) {
            ((HealthComponent) player.getComponent(HealthComponent.class)).dealDamage((int) (Math.random() * 80), 1);
            ((BodyComponent) player.getComponent(BodyComponent.class)).getBody().applyForce(new Vector2(player.getX() - gameObject.getX(), player.getY() - gameObject.getY()).scl(150), new Vector2(player.getX(), player.getY()), true);
            remainingCooldownTime = 1f;
        }
    }

    @Override
    public void render(Batch batch) {

    }

    @Override
    public void remove() {

    }

    @Override
    public AttackPlayerComponent copy() {
        AttackPlayerComponent c = new AttackPlayerComponent();
        return c;
    }
}
