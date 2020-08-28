package jnh.game.components.items;

import jnh.game.assets.Assets;
import jnh.game.components.Component;
import jnh.game.components.HealthComponent;
import jnh.game.gameObjects.GameObject;

public class HealPotionComponent extends Component implements ItemAction {

    private int amount = 1;

    @Override
    public Component copy() {
        HealPotionComponent c = new HealPotionComponent();
        c.amount = amount;
        return c;
    }

    @Override
    public void use(GameObject user) {

    }

    @Override
    public void secondaryUse(GameObject user) {
        ItemContainerComponent itemContainerComponent = user.getComponent(ItemContainerComponent.class);
        HealthComponent healthComponent = user.getComponent(HealthComponent.class);
        if(itemContainerComponent != null && healthComponent != null) {
            long soundID = Assets.sounds.DRINK_POTION.play();
            Assets.sounds.WEAPON_SWING.setPitch(soundID, (float) (1 + 0.3f * Math.random()));
            Assets.sounds.WEAPON_SWING.setVolume(soundID, 0.3f);
            healthComponent.heal(amount, 1);
            itemContainerComponent.remove(gameObject.getID());
            gameObject.remove();
        }

    }
}
