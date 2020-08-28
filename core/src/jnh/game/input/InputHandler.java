package jnh.game.input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class InputHandler implements InputProcessor {

    private short scroll = 0;

    public void update() {
        scroll = 0;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        scroll = (short) amount;
        return false;
    }

    public short getScroll() {
        return scroll;
    }
}
