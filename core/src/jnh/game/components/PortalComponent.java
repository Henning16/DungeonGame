package jnh.game.components;

import jnh.game.assets.Tags;
import jnh.game.gameObjects.GameObject;
import jnh.game.gameObjects.ID;
import jnh.game.utils.Direction;
import jnh.game.world.Dungeon;
import jnh.game.world.Room;

public class PortalComponent extends Component {

    private float range = 1;
    private int direction = Direction.DOWN;

    @Override
    public void tick(float delta) {
        super.tick(delta);
        for(ID playerID: gameObject.getGameObjectManager().getGameObjectsByTag(Tags.player)) {
            GameObject player = gameObject.getGameObjectManager().getGameObject(playerID);
            if(isFacingRightWay(player) && player.getPosition().dst2(gameObject.getPosition()) <= range * range) {
                setToCorrespondingPosition(player);
                setToCorrespondingRoom();
            }
        }
    }

    private boolean isFacingRightWay(GameObject player) {
        MovementComponent movementComponent = player.getComponent(MovementComponent.class);
        if(movementComponent == null) {
            return false;
        }
        return movementComponent.getLooking() == direction;
    }

    private void setToCorrespondingRoom() {
        Dungeon dungeon = gameObject.getStage().getDungeon();
        switch(direction) {
            case Direction.UP:
                dungeon.setRoom(dungeon.getCurrentRoomY() - 1, dungeon.getCurrentRoomX());
                break;
            case Direction.DOWN:
                dungeon.setRoom(dungeon.getCurrentRoomY() + 1, dungeon.getCurrentRoomX());
                break;
        }
    }

    private void setToCorrespondingPosition(GameObject player) {
        switch(direction) {
            case Direction.UP:
                player.getComponent(BodyComponent.class).getBody().setTransform(player.getX(), 1, 0);
                break;
            case Direction.DOWN:
                player.getComponent(BodyComponent.class).getBody().setTransform(player.getX(), Room.ROOM_HEIGHT - 2, 0);
                break;
        }
    }

    @Override
    public Component copy() {
        PortalComponent c = new PortalComponent();
        c.range = range;
        c.direction = direction;
        return c;
    }
}
