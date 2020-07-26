package jnh.game.components;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ComponentHandler {

    private Map<Class<? extends Component>, Component> map = new HashMap<>();
    private ArrayList<Component> list = new ArrayList<>();

    public ComponentHandler() {

    }

    public void tick(float delta) {
        for(Component component: list) {
            component.tick(delta);
        }
    }

    public void render(Batch batch) {
        for(Component component: list) {
            component.render(batch);
        }
    }

    public void remove() {
        for(Component component: list) {
            component.remove();
        }
    }

    public boolean add(Component component) {
        if(map.get(component.getClass()) == null) {
            map.put(component.getClass(), component);
            list.add(component);
            return true;
        }
        return false;
    }

    public <T> T getComponent(Class<T> componentType) {
        return (T) map.get(componentType);
    }

    public <T> T getComponentByInterface(Class<T> componentType) {
        for(Component component: list) {
            for(Class componentInterfaceClass: component.getClass().getInterfaces()) {
                if(componentInterfaceClass == componentType) {
                    return (T) component;
                }
            }
        }
        return null;
    }

    public ArrayList<Component> getList() {
        return list;
    }
}
