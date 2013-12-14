package yaroslav.bugaev.rouge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

import static yaroslav.bugaev.rouge.Config.TILE_SIZE;

public class World implements Disposable {
    private SpriteBatch batch;
    private Sprite img;
    private OrthographicCamera camera;
    private CameraController cameraController;
    private AssetManager assetManager;

    public World() {
        int viewportWidth = Gdx.graphics.getWidth() / TILE_SIZE;
        int viewportHeight = Gdx.graphics.getHeight() / TILE_SIZE;
        camera = new OrthographicCamera(viewportWidth, viewportHeight);
        camera.translate(viewportWidth / 2f, viewportHeight / 2f);
        camera.update();

        cameraController = new CameraController(camera);

        assetManager = new AssetManager();
        assetManager.load("gritsko.jpg", Texture.class);
        assetManager.finishLoading();

        batch = new SpriteBatch();
        img = new Sprite(assetManager.get("gritsko.jpg", Texture.class));
        img.setBounds(0, 0, 25, 19);
    }

    public void update(float delta) {
        cameraController.update(delta);
    }

    public void render() {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        img.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        batch.dispose();
    }
}
