
package yaroslav.bugaev.rouge;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Rouge";
        config.useGL20 = true;
        config.width = 800;
        config.height = 600;
        config.resizable = false;
        config.useGL20 = true;
        new LwjglApplication(new Rouge(), config);
    }
}
