package yaroslav.bugaev.rouge;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteController extends InputAdapter {
    private Sprite sprite;
    private final float offset = 1;

    public SpriteController(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            sprite.translate(-offset, 0);
        } else if (keycode == Input.Keys.RIGHT) {
            sprite.translate(offset, 0);
        } else if (keycode == Input.Keys.UP) {
            sprite.translate(0, offset);
        } else if (keycode == Input.Keys.DOWN) {
            sprite.translate(0, -offset);
        }

        return super.keyDown(keycode);
    }
}
