
package yaroslav.bugaev.rouge;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Rouge";
        config.useGL30 = false;
        config.width = Config.WINDOW_WIDTH;
        config.height = Config.WINDOW_HEIGHT;
        config.resizable = false;
        new LwjglApplication(new Rouge(), config);
    }
}
