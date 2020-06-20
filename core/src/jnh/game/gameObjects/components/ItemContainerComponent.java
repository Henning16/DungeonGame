package jnh.game.gameObjects.components;

import java.util.LinkedList;
import java.util.List;

public class ItemContainerComponent extends Component {

    private LinkedList<Integer> items = new LinkedList<>();
    private int size = 1;

    @Override
    public void tick(float delta) {
        for(Integer item: items) {
            gameObject.getGameObjectManager().getGameObject(item).tick(delta);
        }
    }

    @Override
    public ItemContainerComponent copy() {
        ItemContainerComponent c = new ItemContainerComponent();
        c.size = size;
        c.items = items;
        return c;
    }

    public boolean add(int id) {
        if(items.size() >= size) {
            return false;
        } else {
            items.add(id);
            return true;
        }
    }

    public int remove(int id) {
        return items.remove(id);
    }

    public int getItem(int id) {
        try {
            return items.get(id);
        } catch(IndexOutOfBoundsException e) {
            return -1;
        }
    }

    public List<Integer> getItems() {
        return items;
    }

    public boolean isFull() {
        return items.size() >= size;
    }
}
/**
 ## Texture-Components

 ### TextureComponent
 Diese Component fügt dem GameObject eine einfache oder animierte Textur hinzu.

 **Benötigte Components:** N/A

 **Parameter:**
 | Name      | Typ      | Beschreibung                                                 |
 | --------- | -------- | ------------------------------------------------------------ |
 | `texture` | `String` | Der Name der Variable unter `Assets.textures` der die gewünschte Textur enthält. Der Typ der Variable muss `Animation<TextureRegion>` sein. |
 | `paused`  |`boolean` | Ob die Texture eine statische Textur oder eine Animation ist. |

 ### RandomTextureComponent
 Diese Component fügt dem GameObject eine einfache oder animierte, zufällige Textur aus dem angegebenen Array hinzu.

 **Benötigte Components:** N/A

 **Parameter:**
 | Name      | Typ      | Beschreibung                                                 |
 | --------- | -------- | ------------------------------------------------------------ |
 | `textures` | `String` | Der Name der Variable unter `Assets.textures` der die gewünschten Texturen enthält. Der Typ der Variable muss `Animation<TextureRegion>[]` sein. |
 | `paused`  |`boolean` | Ob die Texturen statisch oder eine Animation sind. |

 ### IndexedTextureComponent
 Diese Component fügt dem GameObject eine einfache oder animierte, Textur aus dem angegebenen Array an der angegebenen Stelle hinzu.

 **Benötigte Components:** N/A

 **Parameter:**
 | Name      | Typ      | Beschreibung                                                 |
 | --------- | -------- | ------------------------------------------------------------ |
 | `textures` | `String` | Der Name der Variable unter `Assets.textures` der die gewünschten Texturen enthält. Der Typ der Variable muss `Animation<TextureRegion>[]` sein. |
 | `index` | `int` | Der Index, an dem dem Array die Textur entnommen werden soll. |
 | `paused`  |`boolean` | Ob die Texturen statisch oder eine Animation sind. |

 ### MovementTextureComponent
 Diese Component verwaltet die Textur eines GameObjects abhängig von seiner Geschwindigkeit und Richtung.

 **Benötigte Components:** MovementComponent

 **Parameter:**
 | Name      | Typ      | Beschreibung                                                 |
 | --------- | -------- | ------------------------------------------------------------ |
 | `textures` | `String` | Der Name der Variable unter `Assets.textures` der die gewünschten Texturen enthält. Der Typ der Variable muss `Animation<TextureRegion>[][]` sein. Die erste Dimension verwaltet die Richtung, in die das GameObject schaut (Direction.java), die zweite Dimension den Zustand (MovementState.java) |

 ## Item-Components

 ### ItemComponent
 Diese Component steuert die sinusförmige Bewegung der Items und Item-Aktionen.

 **Benötigte Components:** N/A

 **Parameter:** N/A

 ### ItemContainerComponent
 Mithilfe dieser Component wird eine Item-Sammlung verwaltet, wie z.B einer Truhe oder der eines Spielers.

 **Benötigte Components:** N/A

 **Parameter:**
 | Name      | Typ      | Beschreibung                                                 |
 | --------- | -------- | ------------------------------------------------------------ |
 | `size` | `int` | Die maximale Anzahl an Items, die in den Item-Container passen. |
 | `...` | `` | Künftig: Representation der Items, die sich im Container befinden (über IDs). |
 **/