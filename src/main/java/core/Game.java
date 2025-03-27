package core;

import java.util.List;

public class Game {
     enum Player {
        First {
            public Player nextPLayer(){
                return Player.Second;
            }
        }, Second {
            public Player nextPLayer(){
                return Player.First;
            }
        };

        public abstract Player nextPLayer();
    }
    
    private Grid first;

    private Grid second;

    private Player current = Player.First;

    private State state = State.Turn;

    public Game(Grid first, Grid second) {
        this.first = first;
        this.second = second;
    }

    public Player getCurrent() {
        return current;
    }
}

