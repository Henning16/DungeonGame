package jnh.game.components;

import jnh.game.gameObjects.GameObject;
import jnh.game.utils.TimeHandler;

public class ItemComponent extends Component {

    private transient ItemAction itemAction;
    private boolean inWorld = true;

    @Override
    public void tick(float delta) {
        if(inWorld) {
            gameObject.setRotation(315);
            gameObject.setPosition(gameObject.getX(), (float) (gameObject.getY() + 0.005f * Math.sin(0.1f * TimeHandler.getTicks())));
        }
    }

    @Override
    public void attachedTo(GameObject gameObject) {
        super.attachedTo(gameObject);
    }

    @Override
    public ItemComponent copy() {
        ItemComponent c = new ItemComponent();
        c.inWorld = inWorld;
        return c;
    }

    private boolean findItemActionComponent() {
        if(itemAction == null) {
            itemAction = gameObject.getComponentByInterface(ItemAction.class);
            return itemAction != null;
        } else {
            return true;
        }
    }
    public void use(GameObject user) {
        if(findItemActionComponent()) {
            itemAction.use(user);
        }
    }

    public void secondaryUse(GameObject user) {
        if(findItemActionComponent()) {
            itemAction.secondaryUse(user);
        }
    }

    public boolean isInWorld() {
        return inWorld;
    }

    public void setInWorld(boolean inWorld) {
        this.inWorld = inWorld;
    }
}
