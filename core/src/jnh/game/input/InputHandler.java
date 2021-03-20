package jnh.game.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor {

    private short scroll = 0;
    private boolean leftClicked = false;
    private boolean rightClicked = false;

    public void update() {
        scroll = 0;
        leftClicked = false;
        rightClicked = false;
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
        if(button == Input.Buttons.LEFT) {
            leftClicked = true;
        } else if(button == Input.Buttons.RIGHT) {
            rightClicked = true;
        }
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

    public boolean isLeftClicked() {
        return leftClicked;
    }

    public boolean isRightClicked() {
        return rightClicked;
    }
}
