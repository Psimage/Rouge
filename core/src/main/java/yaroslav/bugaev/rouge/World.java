package yaroslav.bugaev.rouge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static yaroslav.bugaev.rouge.Config.CAMERA_SPEED;
import static yaroslav.bugaev.rouge.Config.TILE_SIZE;

public class World {
    private SpriteBatch batch;
    private Texture img;
    private OrthographicCamera camera;

    public World() {
        int viewportWidth = Gdx.graphics.getWidth() / TILE_SIZE;
        int viewportHeight = Gdx.graphics.getHeight() / TILE_SIZE;
        camera = new OrthographicCamera(viewportWidth, viewportHeight);
        camera.translate(viewportWidth / 2f, viewportHeight / 2f);
        camera.update();

        batch = new SpriteBatch();
        img = new Texture("gritsko.jpg");
    }

    private void updateCamera(float delta) {
        float offset = CAMERA_SPEED * delta;

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.translate(-offset, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            camera.translate(offset, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.translate(0, offset);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            camera.translate(0, -offset);
        }

        camera.update();
    }

    public void update(float delta) {
        updateCamera(delta);
    }

    public void render() {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(img, 0, 0, 25, 19);
        batch.end();
    }
}
