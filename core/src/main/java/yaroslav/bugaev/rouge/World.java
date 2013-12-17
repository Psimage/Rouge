package yaroslav.bugaev.rouge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Disposable;

public class World implements Disposable {
    private final OrthogonalTiledMapRenderer mapRenderer;
    private final TiledMap map;
    private SpriteBatch batch;
    private Sprite player;
    private OrthographicCamera camera;
    private CameraController cameraController;
    private SpriteController playerController;
    private AssetManager assetManager;


    public World() {
        batch = new SpriteBatch();

        int viewportWidth = Gdx.graphics.getWidth();
        int viewportHeight = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(viewportWidth, viewportHeight);
        camera.translate(viewportWidth / 2f, viewportHeight / 2f);
        camera.update();

        cameraController = new CameraController(camera);

        assetManager = new AssetManager();
        assetManager.load("hryts_32x32.png", Texture.class);
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        assetManager.load("map.tmx", TiledMap.class);
        assetManager.finishLoading();

        map = assetManager.get("map.tmx");

        mapRenderer = new OrthogonalTiledMapRenderer(map, 1, batch);

        player = new Sprite(assetManager.get("hryts_32x32.png", Texture.class));
        RectangleMapObject playerStart = (RectangleMapObject) map.getLayers().get("Objects").getObjects().get("Player start");
        float x = playerStart.getRectangle().getX();
        float y = playerStart.getRectangle().getY();
        player.setBounds(x, y + 10, 24, 24);

        playerController = new SpriteController(player, map);
        Gdx.input.setInputProcessor(playerController);
    }

    public void update(float delta) {
        cameraController.update(delta);
    }

    public void render() {
        batch.setProjectionMatrix(camera.combined);
        mapRenderer.setView(camera);
        batch.begin();
        mapRenderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get("Bottom"));
        player.draw(batch);
        mapRenderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get("Top"));
        batch.end();
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        batch.dispose();
    }
}
