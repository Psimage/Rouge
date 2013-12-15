package yaroslav.bugaev.rouge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import static yaroslav.bugaev.rouge.Config.CAMERA_SPEED;

public class CameraController {
    private OrthographicCamera camera;

    public CameraController(OrthographicCamera camera) {
        this.camera = camera;
    }

    public void update(float delta) {
        boolean updateCamera = false;
        float offset = CAMERA_SPEED * delta;
        Vector2 vector = new Vector2();

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            vector.set(-offset, 0);
            updateCamera = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            vector.set(offset, 0);
            updateCamera = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            vector.set(0, offset);
            updateCamera = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            vector.set(0, -offset);
            updateCamera = true;
        }

        if (updateCamera) {
            //noinspection SuspiciousNameCombination
            vector.set(MathUtils.round(vector.x), MathUtils.round(vector.y));
            camera.translate(vector);
            camera.update();
        }
    }
}
