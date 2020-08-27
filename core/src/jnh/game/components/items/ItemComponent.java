package jnh.game.components.items;

import jnh.game.components.Component;
import jnh.game.gameObjects.GameObject;
import jnh.game.utils.TimeHandler;

public class ItemComponent extends Component {

    private transient ItemAction itemAction;
    private boolean inHand = false;

    @Override
    public void tick(float delta) {
        super.tick(delta);
        if(!inHand) {
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
        c.inHand = inHand;
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

    public boolean isInHand() {
        return inHand;
    }

    public void setInHand(boolean inHand) {
        this.inHand = inHand;
    }
}
