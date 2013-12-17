package yaroslav.bugaev.rouge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Timer;

public class SpriteController extends InputAdapter {
    private final Timer.Task moveTask;
    private Sprite sprite;
    private TiledMapTileLayer collisionLayer;

    public SpriteController(Sprite sprite, TiledMap map) {
        this.sprite = sprite;
        collisionLayer = (TiledMapTileLayer) map.getLayers().get("Collision");
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
        if (keycode == Input.Keys.LEFT) {
            move(-1, 0);
        } else if (keycode == Input.Keys.RIGHT) {
            move(1, 0);
        } else if (keycode == Input.Keys.UP) {
            move(0, 1);
        } else if (keycode == Input.Keys.DOWN) {
            move(0, -1);
        }

        if (moveTask.isScheduled()) {
            moveTask.cancel();
        }
        Timer.schedule(moveTask, 0.2f);

        return super.keyDown(keycode);
    }

    private void move(int dx, int dy) {
        int toCellX = (int) (sprite.getX() / Config.TILE_SIZE) + dx;
        int toCellY = (int) (sprite.getY() / Config.TILE_SIZE) + dy;

        if (collisionLayer.getCell(toCellX, toCellY) == null) {
            sprite.setPosition(toCellX * Config.TILE_SIZE, toCellY * Config.TILE_SIZE + 10);
        }
    }
}
