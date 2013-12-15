package yaroslav.bugaev.rouge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;

import static yaroslav.bugaev.rouge.Config.PLAYER_SPEED;

public class SpriteController {
    private Sprite sprite;

    public SpriteController(Sprite sprite) {
        this.sprite = sprite;
    }

    public void update(float delta) {
        float offset = PLAYER_SPEED * delta;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            sprite.translate(-offset, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            sprite.translate(offset, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            sprite.translate(0, offset);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            sprite.translate(0, -offset);
        }
    }
}
