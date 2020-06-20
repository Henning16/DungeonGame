package jnh.game.gameObjects.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import jnh.game.gameObjects.GameObject;

/**
 * Components können GameObjects hinzugefügt werden und übernehmen Teilaufgaben dieser GameObjects.
 * @see GameObject
 */
public abstract class Component implements Cloneable {

    /**
     * Verweis auf das GameObject dem diese Component zugeordnet ist. Die Variable hat den Wert {@code null} bevor die Methode {@link #attachedTo(GameObject)} aufgerufen wurde.
     */
    protected transient GameObject gameObject;

    public Component() {

    }

    /**
     * Diese Methode aufgerufen, nachdem diese Component einem {@link GameObject} hinzugefügt wurde.
     * @param gameObject das GameObject, dem diese Component hinzugefügt wurde.
     */
    public void attachedTo(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    /**
     * Diese Methode wird bei jedem Tick-Aufgerufen und wird zum Aktualisieren von Werten etc. genutzt.
     * @param delta die vergangene Zeit in Sekunden seit dem letzten Tick
     * @see #render(Batch)
     */
    public void tick(float delta) {

    }

    /**
     * Diese Methode wird bei jedem Rendern aufgerufen. Zu diesem Zeitpunkt wurden alle Tick-Methoden dieses GameObjects, aber noch nicht zwangsweise alle Tick-Methoden anderer GameObjects, ausgeführt. Die Methode wird zum Aktualisieren der Texturen, insbesondere mithilfe vom {@link jnh.game.gfx.animations.Animator} genutzt.
     * @see #tick(float)
     * @param batch
     */
    public void render(Batch batch) {

    }

    /**
     * Diese Methode wird aufgerufen, nachdem das {@link GameObject} von der {@link jnh.game.stages.GameStage} gelöscht wurde.
     */
    public void remove() {

    }

    public abstract Component copy();

}