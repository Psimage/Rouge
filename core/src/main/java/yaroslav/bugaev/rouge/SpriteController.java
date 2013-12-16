package yaroslav.bugaev.rouge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;

public class SpriteController extends InputAdapter {
    private Sprite sprite;
    private MapObjects collisionMap;
    private final float offset = 24;
    private final Timer.Task moveTask;

    public SpriteController(Sprite sprite, MapObjects collisionMap) {
        this.sprite = sprite;
        this.collisionMap = collisionMap;
        moveTask = new Timer.Task() {
            @Override
            public void run() {
                if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    keyDown(Input.Keys.LEFT);
                } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    keyDown(Input.Keys.RIGHT);
                } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                    keyDown(Input.Keys.UP);
                } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                    keyDown(Input.Keys.DOWN);
                }
            }
        };
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

        move(vector);
        if (moveTask.isScheduled()) {
            moveTask.cancel();
        }
        Timer.schedule(moveTask, 0.2f);

        return super.keyDown(keycode);
    }

    private void move(Vector2 vector) {
        Rectangle nextPosition = sprite.getBoundingRectangle();
        nextPosition.setHeight(1);
        Vector2 nextPositionVector = new Vector2();
        nextPosition.setPosition(nextPosition.getPosition(nextPositionVector).add(vector));

        for (MapObject mapObject : collisionMap) {
            if (mapObject.getProperties().get("type", String.class).equals("Collision")) {
                if (nextPosition.overlaps(((RectangleMapObject) mapObject).getRectangle())) {
                    return;
                }
            }
        }

        sprite.setPosition(nextPosition.getX(), nextPosition.getY());
    }


}
