import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void testInitialPlayer() {
        Grid grid1 = new Grid(); 
        Grid grid2 = new Grid();
        Game game = new Game(grid1, grid2);

        assertEquals(Game.Player.First, game.getCurrent());
    }
}
