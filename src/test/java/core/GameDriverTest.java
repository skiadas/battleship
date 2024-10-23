package core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameDriverTest {
    @Test
    void whenGameStarts_welcomeMessageIsDisplayed() {
        PresenterDouble presenter = new PresenterDouble();
        GameDriver driver = new GameDriver(presenter);
        driver.start();
        assertEquals(1, presenter.displayedMessages.size());
        assertEquals("Welcome to Battleship!", presenter.displayedMessages.get(0));
    }
}
