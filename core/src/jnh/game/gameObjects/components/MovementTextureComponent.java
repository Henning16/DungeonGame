package jnh.game.gameObjects.components;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import jnh.game.assets.Assets;
import jnh.game.gfx.animations.Animator;

public class MovementTextureComponent extends Component {

    private MovementComponent movementComponent;
    private BodyComponent bodyComponent;

    private Animator animator = new Animator();
    private Animation<TextureRegion>[][]  texture = Assets.textures.PLAYER;

    @Override
    public void set(String[] parameters) throws Exception {
        texture = (parameters[0] != null) ? (Animation<TextureRegion>[][]) Assets.textures.getClass().getField(parameters[0]).get(Assets.textures) : texture;
    }

    @Override
    public void tick(float delta) {
        if(movementComponent == null || bodyComponent == null) {
            movementComponent = (MovementComponent) gameObject.getComponent(MovementComponent.class);
            bodyComponent = (BodyComponent) gameObject.getComponent(BodyComponent.class);
            return;
        }
        animator.tick(delta);
        if(movementComponent.getLooking() == 1 || movementComponent.getLooking() == 3) {
            gameObject.setSize(0.5f, 1);
            gameObject.setOrigin(gameObject.getWidth() / 2, gameObject.getHeight() / 2);
        } else {
            gameObject.setSize(0.6875f, 1);
        }
        animator.play(texture[movementComponent.getLooking()][movementComponent.getState()], true);
    }

    @Override
    public void render() {
        gameObject.setTexture(animator.getTexture());
    }

    @Override
    public void remove() {

    }

    @Override
    public MovementTextureComponent copy() {
        MovementTextureComponent c = new MovementTextureComponent();
        c.texture = texture;
        return c;
    }
}
