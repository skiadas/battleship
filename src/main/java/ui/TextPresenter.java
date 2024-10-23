package ui;

import core.Grid;
import core.Presenter;
import java.io.IOException;
import org.jline.terminal.Terminal;

public class TextPresenter implements Presenter {
    Terminal terminal;

    public TextPresenter() throws IOException {
        //        terminal = TerminalBuilder.builder().providers("ffm").build();
    }

    public void displayMessage(String s) {
        //        terminal.writer().println(s);
        System.out.println(s);
    }

    public void displayGrid(Grid g) {}
}
