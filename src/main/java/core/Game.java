public class Game {
    private Grid first;
    private Grid second;
    private Player current = Player.First;

    public Game(Grid first, Grid second) {
        this.first = first;
        this.second = second;
    }

    public enum Player {
        First {
            @Override
            public Player nextPlayer() {
                return Second;
            }
        },
        Second {
            @Override
            public Player nextPlayer() {
                return First;
            }
        };

        public abstract Player nextPlayer();
    }

    public Player getCurrent() {
        return current;
    }

    public Grid getFirst() {
        return first;
    }

    public Grid getSecond() {
        return second;
    }
}
