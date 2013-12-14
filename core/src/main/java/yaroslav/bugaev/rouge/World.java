package yaroslav.bugaev.rouge;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class World {
    SpriteBatch batch;
    Texture img;
    private OrthographicCamera camera;

    public World() {
        camera = new OrthographicCamera(8, 6);
        camera.position.set(4, 3, 0);
        camera.update();

        batch = new SpriteBatch();
        img = new Texture("gritsko.jpg");
    }

    public void update(float delta) {

    }

    public void render() {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(img, 0, 0, 8, 6);
        batch.end();
    }
}
