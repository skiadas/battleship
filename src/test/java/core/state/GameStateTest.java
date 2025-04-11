package core.state;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import core.Presenter;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

public class GameStateTest {

    private GameState gameState;

    @Test
    public void whenMainMenuGameStateCreated_ThenDisplayOptionsActionIsProperlyCalled() {
        Presenter presenter = mock(Presenter.class);
        gameState = new MainMenu(presenter);
        ArgumentCaptor<Map<String, Action>> choicesCaptor = ArgumentCaptor.forClass(Map.class);
        verify(presenter).displayOptionsActions(any(), choicesCaptor.capture());
        Map<String, Action> choices = choicesCaptor.getValue();
        assertEquals(new Start(), choices.get("start"));
        assertEquals(new Stop(), choices.get("stop"));
        assertEquals(2, choices.size());
    }

    @Test
    public void whenRunningGameStateCreated_ThenGameStartMessageIsDisplayed() {
        Presenter presenter = mock(Presenter.class);
        gameState = new RunningState(presenter);
        verify(presenter).displayMessage("Game is starting...");
    }
}
