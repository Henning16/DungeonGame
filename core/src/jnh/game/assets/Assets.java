package jnh.game.assets;

import jnh.game.settings.Settings;
import jnh.game.ui.UIStyles;

/**
 * Utility class referencing other classes which mangage different types of assets.
 */
public class Assets {

    /**
     * All the textures, animations except those for ui.
     * @see #uiTextures
     */
    public static Textures textures;

    /**
     * All the blueprints, which are basically templates for game objects.
     * @see jnh.game.gameObjects.construction.Blueprint
     */
    public static Blueprints blueprints;

    /**
     * All the fonts which are used in the game.
     * @see #uiTextures
     * @see #uiStyles
     */
    public static Fonts fonts;

    /**
     * All the textures etc. for ui.
     * @see #textures
     * @see #uiStyles
     * @see #fonts
     */
    public static UITextures uiTextures;

    /**
     * All the styles used for ui components.
     * @see #uiTextures
     * @see #fonts
     */
    public static UIStyles uiStyles;

    /**
     * All the sounds used in the game.
     */
    public static Sounds sounds;

    private Assets() {

    }

    /**
     * Initializes and loads all the given assets. Some assets can be reloaded if necessary using {@link #reloadFonts()} and {@link #reloadUIStyles()}.
     */
    public static void init() {
        textures = new Textures();
        blueprints = new Blueprints();
        fonts = new Fonts();
        uiTextures = new UITextures();
        uiStyles = new UIStyles();
        sounds = new Sounds();
        //TODO add Maps, Music and more like texts etc.
    }

    /**
     * Reloads the fonts to adapt to {@link Settings#getUIScale()}.
     * @see #reloadUIStyles()
     */
    public static void reloadFonts() {
        fonts = new Fonts();
    }

    /**
     * Reloads the ui styles to adapt to {@link Settings#getUIScale()}.
     * @see #reloadFonts()
     */
    public static void reloadUIStyles() {
        uiStyles = new UIStyles();
    }

    /**
     * Releases all ressources.
     */
    public static void dispose() {
        Assets.textures.dispose();
    }
}