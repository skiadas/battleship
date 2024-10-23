import core.Presenter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.TextPresenter;

public class Main {
    public static void main(String[] args) throws IOException {
        setDebugLogging();
        Presenter presenter = new TextPresenter();
        core.GameDriver g = new core.GameDriver(presenter);
        g.start();
    }

    private static void setDebugLogging() {
        Logger logger = Logger.getLogger("org.jline");
        logger.setLevel(Level.ALL);
    }
}
