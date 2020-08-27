package jnh.game.gfx;

import com.badlogic.gdx.graphics.Color;

public class ColorGrading {

    private Color red, green, blue;
    private float redLuminosity, greenLuminosity, blueLuminosity;
    private float redIntensity, greenIntensity, blueIntensity;

    public static final ColorGrading UNMODIFIED = new ColorGrading(Color.RED, Color.GREEN, Color.BLUE);
    public static final ColorGrading NORMAL = new ColorGrading(
            new Color(0.95f, 0.025f, 0.025f, 1),
            new Color(0.025f, 0.95f, 0.025f, 1),
            new Color(0.025f, 0.025f, 0.95f, 1), 0.1f, 0, 0);
    public static final ColorGrading DANGER = new ColorGrading(
            new Color(0.95f, 0.025f, 0.025f, 1),
            new Color(0.025f, 0.95f, 0.025f, 1),
            new Color(0.025f, 0.025f, 0.95f, 1),
            0.2f, 0, 0, 1.1f, 0.9f, 0.9f);
    public static final ColorGrading BLACKWHITE = new ColorGrading(new Color(1 / 3f, 1 / 3f, 1 / 3f, 1), new Color(1 / 3f, 1 / 3f, 1 / 3f, 1), new Color(1 / 3f, 1 / 3f, 1 / 3f, 1));
    public static final ColorGrading HORROR = new ColorGrading(
            new Color(0.9f, 0.05f, 0.05f, 1),
            new Color(0.05f, 0.9f, 0.05f, 1),
            new Color(0.05f, 0.05f, 0.9f, 1),
            0.2f, 0.03f, 0);
    public static final ColorGrading PERGAMENT = new ColorGrading(
            new Color(0.8f, 0.13f, 0.07f, 1),
            new Color(0.15f, 0.78f, 0.05f, 1),
            new Color(0.1f, 0.1f, 0.75f, 1),
            0.4f, 0.15f, 0, 1.2f, 1, 1
    );
    public ColorGrading(Color red, Color green, Color blue) {
        this(red, green, blue, 0, 0, 0);
    }

    public ColorGrading(Color red, Color green, Color blue, float redLuminosity, float greenLuminosity, float blueLuminosity) {
        this(red, green, blue, redLuminosity, greenLuminosity, blueLuminosity, 1, 1, 1);
    }

    public ColorGrading(Color red, Color green, Color blue, float redIntensity, float greenIntensity, float blueIntensity, boolean useIntensity) {
        this(red, green, blue, 0, 0, 0, redIntensity, greenIntensity, blueIntensity);
    }

    public ColorGrading(Color red, Color green, Color blue, float redLuminosity, float greenLuminosity, float blueLuminosity, float redIntensity, float greenIntensity, float blueIntensity) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.redLuminosity = redLuminosity;
        this.greenLuminosity = greenLuminosity;
        this.blueLuminosity = blueLuminosity;
        this.blueIntensity = blueIntensity;
        this.redIntensity = redIntensity;
        this.greenIntensity = greenIntensity;
        this.blueIntensity = blueIntensity;
    }

    public Color getRed() {
        return red;
    }

    public void setRed(Color red) {
        this.red = red;
    }

    public Color getGreen() {
        return green;
    }

    public void setGreen(Color green) {
        this.green = green;
    }

    public Color getBlue() {
        return blue;
    }

    public void setBlue(Color blue) {
        this.blue = blue;
    }

    public float getRedLuminosity() {
        return redLuminosity;
    }

    public void setRedLuminosity(float redLuminosity) {
        this.redLuminosity = redLuminosity;
    }

    public float getGreenLuminosity() {
        return greenLuminosity;
    }

    public void setGreenLuminosity(float greenLuminosity) {
        this.greenLuminosity = greenLuminosity;
    }

    public float getBlueLuminosity() {
        return blueLuminosity;
    }

    public void setBlueLuminosity(float blueLuminosity) {
        this.blueLuminosity = blueLuminosity;
    }

    public float getRedIntensity() {
        return redIntensity;
    }

    public void setRedIntensity(float redIntensity) {
        this.redIntensity = redIntensity;
    }

    public float getGreenIntensity() {
        return greenIntensity;
    }

    public void setGreenIntensity(float greenIntensity) {
        this.greenIntensity = greenIntensity;
    }

    public float getBlueIntensity() {
        return blueIntensity;
    }

    public void setBlueIntensity(float blueIntensity) {
        this.blueIntensity = blueIntensity;
    }
}
