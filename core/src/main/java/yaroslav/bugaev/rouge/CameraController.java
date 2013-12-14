package yaroslav.bugaev.rouge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;

import static yaroslav.bugaev.rouge.Config.CAMERA_SPEED;

public class CameraController {
    private OrthographicCamera camera;

    public CameraController(OrthographicCamera camera) {
        this.camera = camera;
    }

    public void update(float delta) {
        boolean updateCamera = false;
        float offset = CAMERA_SPEED * delta;

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.translate(-offset, 0);
            updateCamera = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            camera.translate(offset, 0);
            updateCamera = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.translate(0, offset);
            updateCamera = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            camera.translate(0, -offset);
            updateCamera = true;
        }

        if (updateCamera) {
            camera.update();
        }
    }
}
