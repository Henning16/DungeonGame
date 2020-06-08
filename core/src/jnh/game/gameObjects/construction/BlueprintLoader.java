package jnh.game.gameObjects.construction;

import com.badlogic.gdx.Gdx;
import jnh.game.gameObjects.GameObject;
import jnh.game.gameObjects.components.Component;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public class BlueprintLoader {

    public Blueprint loadBlueprint(String name) {
        Blueprint blueprint = new Blueprint();
        String blueprintRepresentation = Gdx.files.internal("blueprints/"+name+".bpt").readString();
        String[] lines = blueprintRepresentation.split("\n");
        blueprint.type = lines[0];
        for(int i = 1; i < lines.length; i++) {
            String[] tokens = lines[i].split(":");
            Component c;
            try {
                Class<?> cls = Class.forName("jnh.game.gameObjects.components."+tokens[0]);
                Constructor<?> constructor = cls.getConstructor(GameObject.class);
                c = (Component) constructor.newInstance(GameObject.EMPTY);
                blueprint.components.add(c);
            } catch(Exception e) {
                System.err.println("The component " + tokens[0] + " could not be found.");
                continue;
            }
            String[] values = tokens[1].split(" ");
            for(int j = 0; j < values.length; j++) {
                if(values[j] == "_") {
                    values[j] = null;
                }
            }
            try {
                c.set(Arrays.copyOfRange(values, 1, values.length));
            } catch(IllegalArgumentException e) {
                System.err.println("The parameters provided for the component " + c.getClass().getName() + " are invalid.");
            }
        }
        return blueprint;
    }
}