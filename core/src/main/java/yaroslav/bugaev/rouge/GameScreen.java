package yaroslav.bugaev.rouge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL10;

public class GameScreen extends ScreenAdapter {
    World world;

    public GameScreen() {
        world = new World();
    }

    @Override
    public void render(float delta) {
        world.update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        world.render();
    }

    @Override
    public void dispose() {
        world.dispose();
    }
}
