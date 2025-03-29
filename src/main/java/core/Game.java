package core;

import java.util.List;

/** Implements basic functions of a two-player game */
public class Game {
    /** The players of the Game */
    enum Player {
        FIRST {
            @Override
            public Player nextPLayer() {
                return Player.SECOND;
            }
        },
        SECOND {
            @Override
            public Player nextPLayer() {
                return Player.FIRST;
            }
        };

        /**
         * Function to switch turns between players
         *
         * @return next player's move
         */
        public abstract Player nextPLayer();
    }

    /** Describes game's state */
    enum State {
        TURN,
        OVER;
    }

    /** Is the first player's {@link Grid} */
    private final Grid first;

    /** Is the second player's {@link Grid} */
    private final Grid second;

    /** The current player's turn */
    private Player current = Player.FIRST;

    /** The {@link Game} current state */
    private State state = State.TURN;

    /**
     * constructor of both player's {@link Grid}'s
     *
     * @param first first player's {@link Grid}
     * @param second second player's {@link Grid}
     */
    public Game(final Grid first, final Grid second) {
        this.first = first;
        this.second = second;
    }

    public Player getCurrent() {
        return current;
    }

    public void next() {
        if (state == State.OVER) {
            throw new RuntimeException("You should not be calling next if the game is over");
        } else if (areAllEnemyShipsSunk()) {
            state = State.OVER;
        } else {
            current = current.nextPLayer();
        }
    }

    public void shoot(final Coord coordinate) {
        getEnemyGrid().shoot(coordinate);
    }

    public boolean isOver() {
        return state == State.OVER;
    }

    public boolean isShipSunk(final Ship ship) {
        return getEnemyGrid().isShipSunk(ship);
    }

    private boolean areAllEnemyShipsSunk() {
        final List<Ship> ships = getEnemyGrid().getShipList();
        for (final Ship ship : ships) {
            if (!(isShipSunk(ship))) {
                return false;
            }
        }
        return true;
    }

    private Grid getEnemyGrid() {
        return this.current.equals(Player.FIRST) ? this.second : this.first;
    }
}
