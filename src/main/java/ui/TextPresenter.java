package ui;

import core.Presenter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

public class TextPresenter implements Presenter {
    Terminal terminal;

    public TextPresenter() throws IOException {
//        terminal = TerminalBuilder.builder().providers("ffm").build();
    }

    public void displayMessage(String s) {
//        terminal.writer().println(s);
        System.out.println(s);
    }
}
