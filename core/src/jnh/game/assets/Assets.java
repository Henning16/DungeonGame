package jnh.game.assets;

import jnh.game.ui.UIStyles;

public class Assets {

    public static Textures textures;
    public static Blueprints blueprints;
    public static Fonts fonts;
    public static UITextures uiTextures;
    public static UIStyles uiStyles;
    public static Sounds sounds;

    public Assets() {
        textures = new Textures();
        blueprints = new Blueprints();
        fonts = new Fonts();
        uiTextures = new UITextures();
        uiStyles = new UIStyles();
        sounds = new Sounds();
        //TODO add Sounds, Maps, Music and more like texts etc.
    }


}