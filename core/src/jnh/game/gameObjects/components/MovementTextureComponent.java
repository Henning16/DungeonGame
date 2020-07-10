package jnh.game.gameObjects.components;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import jnh.game.assets.Assets;
import jnh.game.gameObjects.GameObject;
import jnh.game.gfx.animations.Animator;

public class MovementTextureComponent extends Component {

    private transient MovementComponent movementComponent;
    private transient BodyComponent bodyComponent;

    private String textureSetName = "PLAYER";
    private transient Animation<TextureRegion>[][]  textureSet = Assets.textures.PLAYER;
    private transient Animator animator = new Animator();

    @Override
    public void tick(float delta) {
        if(movementComponent == null || bodyComponent == null) {
            movementComponent = (MovementComponent) gameObject.getComponent(MovementComponent.class);
            bodyComponent = (BodyComponent) gameObject.getComponent(BodyComponent.class);
            return;
        }
        animator.tick(delta);
        Animation animation = textureSet[movementComponent.getLooking()][movementComponent.getState()];
        animation.setFrameDuration(1 / (6 + 0.02f * movementComponent.getSpeed()));
        animator.play(animation, true);
    }

    @Override
    public void render(Batch batch) {
        gameObject.setTexture(animator.getTexture());
    }

    @Override
    public void attachedTo(GameObject gameObject) {
        super.attachedTo(gameObject);
        textureSet = Assets.textures.getTextureSet2(textureSetName);
    }

    @Override
    public MovementTextureComponent copy() {
        MovementTextureComponent c = new MovementTextureComponent();
        c.textureSetName = textureSetName;
        return c;
    }
}
