package jnh.game.gameObjects.components;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import jnh.game.assets.Assets;
import jnh.game.gfx.animations.Animator;

public class MovementRenderComponent extends Component {

    private MovementComponent movementComponent;

    private Animator animator = new Animator();
    private Animation<TextureRegion>[][]  texture = Assets.textures.PLAYER;

    @Override
    public void set(String[] parameters) throws Exception {
        texture = (parameters[0] != null) ? (Animation<TextureRegion>[][]) Assets.textures.getClass().getField(parameters[0]).get(Assets.textures) : texture;
    }

    @Override
    public void tick(float delta) {
        if(movementComponent == null) {
            movementComponent = (MovementComponent) gameObject.getComponent(MovementComponent.class);
            return;
        }
        animator.tick(delta);
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
    public MovementRenderComponent copy() {
        MovementRenderComponent c = new MovementRenderComponent();
        c.texture = texture;
        return c;
    }
}
