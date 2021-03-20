package jnh.game.gfx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class ColorGrader {

    private ColorGrading current = ColorGrading.UNMODIFIED;
    private ColorGrading target = null;
    private int inertia;

    public ColorGrader() {

    }

    public void update(ShaderProgram shader) {
        if(target != null) {
            current.setRed(getAverage(current.getRed(), target.getRed(), inertia));
            current.setGreen(getAverage(current.getGreen(), target.getGreen(), inertia));
            current.setBlue(getAverage(current.getBlue(), target.getBlue(), inertia));
            current.setRedLuminosity(getAverage(current.getRedLuminosity(), target.getRedLuminosity(), inertia));
            current.setGreenLuminosity(getAverage(current.getGreenLuminosity(), target.getGreenLuminosity(), inertia));
            current.setBlueLuminosity(getAverage(current.getBlueLuminosity(), target.getBlueLuminosity(), inertia));
            current.setRedIntensity(getAverage(current.getRedIntensity(), target.getRedIntensity(), inertia));
            current.setGreenIntensity(getAverage(current.getGreenIntensity(), target.getGreenIntensity(), inertia));
            current.setBlueIntensity(getAverage(current.getBlueIntensity(), target.getBlueIntensity(), inertia));
        }
        shader.setUniformf("red", current.getRed());
        shader.setUniformf("green", current.getGreen());
        shader.setUniformf("blue", current.getBlue());
        shader.setUniformf("redLuminosity", current.getRedLuminosity());
        shader.setUniformf("greenLuminosity", current.getGreenLuminosity());
        shader.setUniformf("blueLuminosity", current.getBlueLuminosity());
        shader.setUniformf("redIntensity", current.getRedIntensity());
        shader.setUniformf("greenIntensity", current.getGreenIntensity());
        shader.setUniformf("blueIntensity", current.getBlueIntensity());
    }

    public void transitionTo(ColorGrading colorGrading, int inertia) {
        target = colorGrading;
        this.inertia = inertia;
    }

    public ColorGrading getColorGrading() {
        return current;
    }

    public void setColorGrading(ColorGrading colorGrading) {
        if(colorGrading != null) {
            current = colorGrading;
        }
    }

    private float getAverage(float float1, float float2, int weighting) {
        return (float1 * weighting + float2) / (weighting + 1.0f);
    }

    private Color getAverage(Color color1, Color color2, int weighting) {
        return new Color(
                (color1.r * weighting + color2.r) / (weighting + 1.0f),
                (color1.g * weighting + color2.g) / (weighting + 1.0f),
                (color1.b * weighting + color2.b) / (weighting + 1.0f),
                1);
    }
}
