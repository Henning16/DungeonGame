package jnh.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import jnh.game.assets.Assets;
import jnh.game.settings.Settings;

public class UIStyles {

    public CheckBox.CheckBoxStyle checkBox;
    public TextButton.TextButtonStyle dangerousButton;
    public TextButton.TextButtonStyle defaultButton;
    public SelectBox.SelectBoxStyle dropdownMenu;
    public Label.LabelStyle header;
    public ProgressBar.ProgressBarStyle healthBar;
    public Label.LabelStyle label;
    public List.ListStyle list;
    public ScrollPane.ScrollPaneStyle scrollBar;
    public Slider.SliderStyle slider;
    public Label.LabelStyle text;
    public TextField.TextFieldStyle textField;
    public Label.LabelStyle title;
    public Window.WindowStyle window;

    public UIStyles() {
        loadScrollBar();
        loadCheckBox();
        loadDefaultButton();
        //Dangerous button uses default button
        loadDangerousButton();
        loadDropdownMenu();
        loadHeader();
        loadHealthBar();
        loadLabel();
        loadList();
        loadScrollBar();
        loadSlider();
        loadText();
        loadTextField();
        loadTitle();
        loadWindow();
    }

    private void loadCheckBox() {
        checkBox = new CheckBox.CheckBoxStyle();
        checkBox.checkboxOff = new TextureRegionDrawable(Assets.uiTextures.CHECKBOX_OFF);
        checkBox.checkboxOff.setMinWidth(26 * Settings.getUIScale());
        checkBox.checkboxOff.setMinHeight(26 * Settings.getUIScale());
        checkBox.checkboxOffDisabled = new TextureRegionDrawable(Assets.uiTextures.CHECKBOX_OFF_DISABLED);
        checkBox.checkboxOffDisabled.setMinWidth(26 * Settings.getUIScale());
        checkBox.checkboxOffDisabled.setMinHeight(26 * Settings.getUIScale());
        checkBox.checkboxOn = new TextureRegionDrawable(Assets.uiTextures.CHECKBOX_ON);
        checkBox.checkboxOn.setMinWidth(26 * Settings.getUIScale());
        checkBox.checkboxOn.setMinHeight(26 * Settings.getUIScale());
        checkBox.checkboxOnDisabled = new TextureRegionDrawable(Assets.uiTextures.CHECKBOX_ON_DISABLED);
        checkBox.checkboxOnDisabled.setMinWidth(26 * Settings.getUIScale());
        checkBox.checkboxOnDisabled.setMinHeight(26 * Settings.getUIScale());
        checkBox.checkboxOnOver = new TextureRegionDrawable(Assets.uiTextures.CHECKBOX_ON_OVER);
        checkBox.checkboxOnOver.setMinWidth(26 * Settings.getUIScale());
        checkBox.checkboxOnOver.setMinHeight(26 * Settings.getUIScale());
        checkBox.checkboxOver = new TextureRegionDrawable(Assets.uiTextures.CHECKBOX_OVER);
        checkBox.checkboxOver.setMinWidth(26 * Settings.getUIScale());
        checkBox.checkboxOver.setMinHeight(26 * Settings.getUIScale());

        checkBox.disabledFontColor = new Color(1, 1, 1, 0.5f);
        checkBox.font = Assets.fonts.EXEPP;
    }

    private void loadDangerousButton() {
        dangerousButton = new TextButton.TextButtonStyle(defaultButton);
        dangerousButton.fontColor = new Color(1, 0.4f, 0.4f, 1);
    }

    private void loadDefaultButton() {
        defaultButton = new TextButton.TextButtonStyle();

        NinePatch p1 = new NinePatch(Assets.uiTextures.BUTTON_UP, 5, 5, 6, 5);
        p1.scale(2 * Settings.getUIScale(), 2 * Settings.getUIScale());
        defaultButton.up =  new NinePatchDrawable(p1);

        NinePatch p2 = new NinePatch(Assets.uiTextures.BUTTON_OVER, 5, 5, 6, 5);
        p2.scale(2 * Settings.getUIScale(), 2 * Settings.getUIScale());
        defaultButton.over =  new NinePatchDrawable(p2);

        NinePatch p3 = new NinePatch(Assets.uiTextures.BUTTON_DOWN, 5, 5, 6, 5);
        p3.scale(2 * Settings.getUIScale(), 2 * Settings.getUIScale());
        defaultButton.down =  new NinePatchDrawable(p3);

        NinePatch p4 = new NinePatch(Assets.uiTextures.BUTTON_DISABLED, 5, 5, 6, 5);
        p4.scale(2 * Settings.getUIScale(), 2 * Settings.getUIScale());
        defaultButton.disabled =  new NinePatchDrawable(p4);

        defaultButton.fontColor = new Color(1, 1, 1, 1);
        defaultButton.disabledFontColor = new Color(1, 1, 1, 0.5f);
        defaultButton.font = Assets.fonts.EXEPP;
    }

    private void loadDropdownMenu() {
        dropdownMenu = new SelectBox.SelectBoxStyle();
        dropdownMenu.listStyle = new List.ListStyle();
        dropdownMenu.scrollStyle = scrollBar;
        dropdownMenu.background = defaultButton.up;
        dropdownMenu.backgroundOver = defaultButton.over;
        dropdownMenu.backgroundDisabled = defaultButton.disabled;
        dropdownMenu.backgroundOpen = defaultButton.disabled;

        NinePatch p1 = new NinePatch(Assets.uiTextures.LIST_BACKGROUND, 5, 5, 6, 5);
        p1.scale(2 * Settings.getUIScale(), 2 * Settings.getUIScale());
        p1.setPadding(2 * Settings.getUIScale(), 2 * Settings.getUIScale(), 2 * Settings.getUIScale(), 2 * Settings.getUIScale());
        dropdownMenu.listStyle.background = new NinePatchDrawable(p1);

        NinePatch p2 = new NinePatch(Assets.uiTextures.LIST_SELECTION, 2, 2, 1, 1);
        p2.scale(2 * Settings.getUIScale(), 2 * Settings.getUIScale());
        p2.setPadding(8 * Settings.getUIScale(), 4 * Settings.getUIScale(), 4 * Settings.getUIScale(), 4 * Settings.getUIScale());
        dropdownMenu.listStyle.selection = new NinePatchDrawable(p2);

        dropdownMenu.fontColor = Color.WHITE;
        dropdownMenu.font = Assets.fonts.EXEPP;
        dropdownMenu.listStyle.font = Assets.fonts.EXEPP;
        dropdownMenu.font = Assets.fonts.EXEPP;

    }

    private void loadHeader() {
        header = new Label.LabelStyle();
        header.font = Assets.fonts.EXEPPL;
        header.fontColor = Color.WHITE;
    }

    private void loadHealthBar() {
        healthBar = new ProgressBar.ProgressBarStyle();
        NinePatch p1 = new NinePatch(Assets.uiTextures.HEALTH_BAR_BACKGROUND, 3, 3, 2, 2);
        p1.scale(2 * Settings.getUIScale(), 2 * Settings.getUIScale());
        p1.setPadding(0, 0, 0, 0);
        healthBar.background = new NinePatchDrawable(p1);
        healthBar.background.setMinHeight(20 * Settings.getUIScale());

        NinePatch p2 = new NinePatch(Assets.uiTextures.HEALTH_BAR_KNOB, 3, 3, 2, 2);
        p2.scale(2 * Settings.getUIScale(), 2 * Settings.getUIScale());
        healthBar.knobBefore = new NinePatchDrawable(p2);
        healthBar.knobBefore.setMinHeight(20 * Settings.getUIScale());
        healthBar.knobBefore.setMinWidth(32 * Settings.getUIScale());
    }

    private void loadLabel() {
        label = new Label.LabelStyle();
        label.font = Assets.fonts.EXEPP;
        label.fontColor = Color.WHITE;
    }

    private void loadList() {
        list = new List.ListStyle();

        NinePatch p1 = new NinePatch(Assets.uiTextures.LIST_BACKGROUND, 5, 5, 6, 5);
        p1.scale(2 * Settings.getUIScale(), 2 * Settings.getUIScale());
        p1.setPadding(2 * Settings.getUIScale(), 2 * Settings.getUIScale(), 2 * Settings.getUIScale(), 2 * Settings.getUIScale());
        list.background = new NinePatchDrawable(p1);

        NinePatch p2 = new NinePatch(Assets.uiTextures.LIST_SELECTION, 2, 2, 1, 1);
        p2.scale(2 * Settings.getUIScale(), 2 * Settings.getUIScale());
        p2.setPadding(8 * Settings.getUIScale(), 8 * Settings.getUIScale(), 8 * Settings.getUIScale(), 8 * Settings.getUIScale());
        list.selection = new NinePatchDrawable(p2);

        list.font = Assets.fonts.EXEPP;
        list.fontColorSelected = Color.WHITE;
        list.fontColorUnselected = Color.WHITE;
    }

    private void loadText() {
        text = new Label.LabelStyle();
        text.font = Assets.fonts.EXEPPS;
        text.fontColor = Color.WHITE;
    }

    private void loadScrollBar() {
        scrollBar = new ScrollPane.ScrollPaneStyle();
        NinePatch p1 = new NinePatch(Assets.uiTextures.SCROLL_KNOB, 3, 3, 6, 5);
        p1.scale(2 * Settings.getUIScale(), 2 * Settings.getUIScale());
        scrollBar.vScrollKnob = new NinePatchDrawable(p1);
        scrollBar.vScrollKnob.setMinWidth(18 * Settings.getUIScale());
        scrollBar.hScrollKnob = new NinePatchDrawable(p1);
        scrollBar.hScrollKnob.setMinHeight(18 * Settings.getUIScale());

        NinePatch p2 = new NinePatch(Assets.uiTextures.SCROLL_BACKGROUND, 3, 3, 6, 3);
        p2.scale(2 * Settings.getUIScale(), 2 * Settings.getUIScale());
        p2.setPadding(4 * Settings.getUIScale(), 4 * Settings.getUIScale(), 4 * Settings.getUIScale(), 4 * Settings.getUIScale());
        scrollBar.vScroll = new NinePatchDrawable(p2);
        scrollBar.vScroll.setMinWidth(18 * Settings.getUIScale());
    }

    private void loadSlider() {
        slider = new Slider.SliderStyle();
        NinePatch p1 = new NinePatch(Assets.uiTextures.SLIDER_BACKGROUND, 3, 3, 3, 3);
        p1.scale(2 * Settings.getUIScale(), 2 * Settings.getUIScale());
        p1.setPadding(0, 0, 0,0);
        slider.background = new NinePatchDrawable(p1);
        slider.background.setMinHeight(24 * Settings.getUIScale());

        NinePatch p2 = new NinePatch(Assets.uiTextures.SLIDER_KNOB, 3, 3, 3, 3);
        p2.scale(2 * Settings.getUIScale(), 2 * Settings.getUIScale());
        p2.setPadding(0, 0, 0,0);
        slider.knob = new NinePatchDrawable(p2);
        slider.knob.setMinHeight(24 * Settings.getUIScale());
        slider.knob.setMinWidth(24 * Settings.getUIScale());

        NinePatch p3 = new NinePatch(Assets.uiTextures.SLIDER_KNOB_OVER, 3, 3, 3, 3);
        p3.scale(2 * Settings.getUIScale(), 2 * Settings.getUIScale());
        p3.setPadding(0, 0, 0,0);
        slider.knobOver = new NinePatchDrawable(p3);
        slider.knobOver.setMinHeight(24 * Settings.getUIScale());
        slider.knobOver.setMinWidth(24 * Settings.getUIScale());
    }

    private void loadTextField() {
        textField = new TextField.TextFieldStyle();
        NinePatch p1 = new NinePatch(Assets.uiTextures.TEXT_FIELD_BACKGROUND, 5, 5, 5, 5);
        p1.scale(2 * Settings.getUIScale(), 2 * Settings.getUIScale());
        textField.background =  new NinePatchDrawable(p1);

        NinePatch p2 = new NinePatch(Assets.uiTextures.TEXT_FIELD_BACKGROUND_DISABLED, 5, 5, 5, 5);
        p2.scale(2 * Settings.getUIScale(), 2 * Settings.getUIScale());
        textField.disabledBackground =  new NinePatchDrawable(p2);

        NinePatch p3 = new NinePatch(Assets.uiTextures.TEXT_FIELD_BACKGROUND_FOCUSED, 5, 5, 5, 5);
        p3.scale(2 * Settings.getUIScale(), 2 * Settings.getUIScale());
        textField.focusedBackground =  new NinePatchDrawable(p3);

        textField.cursor = new TextureRegionDrawable(Assets.uiTextures.TEXT_FIELD_CURSOR);
        textField.cursor.setMinWidth(2 * Settings.getUIScale());

        textField.selection = new TextureRegionDrawable(Assets.uiTextures.TEXT_FIELD_SELECTION);

        textField.font =  Assets.fonts.EXEPP;
        textField.fontColor = Color.WHITE;
        textField.messageFont =  Assets.fonts.EXEPP;
        textField.messageFontColor = Color.WHITE;
    }

    private void loadTitle() {
        title = new Label.LabelStyle();
        title.font = Assets.fonts.EXEPPXL;
        title.fontColor = Color.WHITE;
    }

    private void loadWindow() {
        NinePatch p1 = new NinePatch(Assets.uiTextures.WINDOW_BACKGROUND, 5, 5, 19, 5);
        p1.scale(2 * Settings.getUIScale(), 2 * Settings.getUIScale());
        p1.setPadding(16 * Settings.getUIScale(), 16 * Settings.getUIScale(), 32 * Settings.getUIScale(), 8 * Settings.getUIScale());
        Drawable background = new NinePatchDrawable(p1);
        window = new Window.WindowStyle(Assets.fonts.EXEPP, Color.WHITE, background);
        window.stageBackground = new TextureRegionDrawable(Assets.uiTextures.LIST_SELECTION);

    }
}