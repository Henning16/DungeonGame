package jnh.game.gameObjects.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import jnh.game.gameObjects.GameObject;

/**
 * Components können GameObjects hinzugefügt werden und übernehmen Teilaufgaben dieser GameObjects.
 * @see GameObject
 */
public abstract class Component {

    public Component() {

    }

    public Component(Component component) {

    }

    /**
     * Verweis auf das GameObject dem diese Component zugeordnet ist. Die Variable hat den Wert {@code null} bevor die Methode {@link #attachedTo(GameObject)} aufgerufen wurde.
     */
    protected GameObject gameObject;

    /**
     * Diese Methode wird aufgerufen, wenn durch {@link jnh.game.gameObjects.construction.BlueprintLoader} eine neue Component erstellt wird.
     * @param parameters Übergebene Parameter als String-Array. {@code null} wird ignoriert, bestehende Werte also nicht überschireben.
     * @throws IllegalArgumentException Diese Exception wird ausgelöst, wenn die Parameter ungültige Werte enthalten, bzw. nicht interpretiert werden können.
     */
    public abstract void set(String[] parameters) throws Exception;

    /**
     * Diese Methode verwandelt, relevante momentane Informationen des GameObjects in ein String-Array der später wieder von der {@link #set(String[])}-Methode interpretiert werden kann.
     * @returns die Parameter
     */
    public abstract String[] get();

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
    public abstract void tick(float delta);

    /**
     * Diese Methode wird bei jedem Rendern aufgerufen. Zu diesem Zeitpunkt wurden alle Tick-Methoden dieses GameObjects, aber noch nicht zwangsweise alle Tick-Methoden anderer GameObjects, ausgeführt. Die Methode wird zum Aktualisieren der Texturen, insbesondere mithilfe vom {@link jnh.game.gfx.animations.Animator} genutzt.
     * @see #tick(float)
     * @param batch
     */
    public abstract void render(Batch batch);

    /**
     * Diese Methode wird aufgerufen, nachdem das {@link GameObject} von der {@link jnh.game.stages.GameStage} gelöscht wurde.
     */
    public abstract void remove();

    public abstract Component copy();

}