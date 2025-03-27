package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Grid firstGrid;
    private Grid secondGrid;
    private Game game;

    @BeforeEach
    public void setup() {
        firstGrid = mock(Grid.class);
        secondGrid = mock(Grid.class);
        game = new Game(firstGrid, secondGrid);
    }
}
