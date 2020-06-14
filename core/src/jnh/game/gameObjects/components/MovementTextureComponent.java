package jnh.game.gameObjects.components;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import jnh.game.assets.Assets;
import jnh.game.gfx.animations.Animator;

public class MovementTextureComponent extends Component {

    private MovementComponent movementComponent;
    private BodyComponent bodyComponent;

    private Animator animator = new Animator();
    private String  textureString = "PLAYER";
    private Animation<TextureRegion>[][]  texture = Assets.textures.PLAYER;

    @Override
    public void set(String[] parameters) throws Exception {
        textureString = (parameters[0] != null) ? parameters[0] : textureString;
        texture = (parameters[0] != null) ? (Animation<TextureRegion>[][]) Assets.textures.getClass().getField(parameters[0]).get(Assets.textures) : texture;
    }

    @Override
    public String[] get() {
        String[] parameters = new String[1];
        parameters[0] = textureString;
        return parameters;
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
        Animation animation = texture[movementComponent.getLooking()][movementComponent.getState()];
        animation.setFrameDuration(1 / (4f + 0.002f * movementComponent.getSpeed()));
        animator.play(animation, true);
    }

    @Override
    public void render(Batch batch) {
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
