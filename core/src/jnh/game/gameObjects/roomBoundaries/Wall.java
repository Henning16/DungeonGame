package jnh.game.gameObjects.roomBoundaries;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import jnh.game.gameObjects.RigidObject;
import jnh.game.stages.GameStage;

public class Wall extends RigidObject {

    public Wall (GameStage stage, TextureRegion texture, Vector2 position) {
        super (stage, texture, position, new Vector2 (1, 1));
    }

    @Override
    public void createBody () {
        BodyDef def = new BodyDef ();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set (getX (), getY ());

        setBody (getStage ().getScreen ().getWorld ().createBody (def));

        PolygonShape shape = new PolygonShape ();
        shape.setAsBox (getWidth () / 2, getHeight () / 2);
        getBody ().createFixture (shape, 1f);
        shape.dispose ();
    }
}
