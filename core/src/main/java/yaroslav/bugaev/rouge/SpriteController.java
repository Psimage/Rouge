package yaroslav.bugaev.rouge;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class SpriteController extends InputAdapter {
    private Sprite sprite;
    private MapObjects collisionMap;
    private final float offset = 24;

    public SpriteController(Sprite sprite, MapObjects collisionMap) {
        this.sprite = sprite;
        this.collisionMap = collisionMap;
    }

    @Override
    public boolean keyDown(int keycode) {
        Vector2 vector = new Vector2();
        if (keycode == Input.Keys.LEFT) {
            vector.set(-offset, 0);
        } else if (keycode == Input.Keys.RIGHT) {
            vector.set(offset, 0);
        } else if (keycode == Input.Keys.UP) {
            vector.set(0, offset);
        } else if (keycode == Input.Keys.DOWN) {
            vector.set(0, -offset);
        }

        Rectangle nextPosition = sprite.getBoundingRectangle();
        nextPosition.setHeight(1);
        Vector2 nextPositionVector = new Vector2();
        nextPosition.setPosition(nextPosition.getPosition(nextPositionVector).add(vector));

        for (MapObject mapObject : collisionMap) {
            Rectangle collision = ((RectangleMapObject) mapObject).getRectangle();
            if (nextPosition.overlaps(collision)) {
                return false;
            }
        }

        sprite.setPosition(nextPosition.getX(), nextPosition.getY());

        return super.keyDown(keycode);
    }
}
