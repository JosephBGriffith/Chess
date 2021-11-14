package matchcomponents.player;

public class PlayerFactory {
    public static Player whitePlayer() {
        return new HumanPlayer(true);
    }

    public static Player blackPlayer() {
        return new HumanPlayer(false);
    }
}
