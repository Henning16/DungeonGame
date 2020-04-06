package jnh.game.gfx;

public class Shake {

    public static final Shake EMPTY = new Shake (0f, 0f);
    public static final Shake SMALL = new Shake (0.15f, 1.2f);
    public static final Shake DEFAULT = new Shake (0.2f, 2f);
    public static final Shake MEDIUM = new Shake (0.3f, 4f);
    public static final Shake BIG = new Shake (0.5f, 6f);

    public float duration;
    public float intensity;

    public Shake (Shake shake) {
        this.duration = shake.duration;
        this.intensity = shake.intensity;
    }

    public Shake (float duration, float intensity) {
        this.duration = duration;
        this.intensity = intensity;
    }
}
