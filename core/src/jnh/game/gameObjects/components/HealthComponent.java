package jnh.game.gameObjects.components;

import jnh.game.gfx.Shake;

public class HealthComponent extends Component {

    private int health = 100;
    private int maxHealth = 100;

    @Override
    public void set(String[] parameters) throws Exception {
        maxHealth = (parameters[0] != null) ? Integer.parseInt(parameters[0]) : maxHealth;
        health = maxHealth;
    }

    @Override
    public void tick(float delta) {

    }

    @Override
    public void render() {

    }

    @Override
    public void remove() {

    }

    @Override
    public HealthComponent copy() {
        HealthComponent c = new HealthComponent();
        c.maxHealth = maxHealth;
        return c;
    }

    public void dealDamage(int damage, float damageModifier) {
        health = Math.max(0, health - (int) (damage * damageModifier));
        if(gameObject.getType().equals("PLAYER")) {
            System.out.println("damage");
            gameObject.getStage().getScreen().getGameCamera().shake(new Shake(0.2f, damage * damageModifier * 0.03f));
        }
    }
}
