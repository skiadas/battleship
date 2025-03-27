public class Game {
    private Grid first;
    private Grid second;

    public Game(Grid first, Grid second) {
        this.first = first;
        this.second = second;
    }

    public Grid getFirst() {
        return first;
    }

    public Grid getSecond() {
        return second;
    }
}
