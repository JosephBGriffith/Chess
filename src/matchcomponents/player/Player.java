package matchcomponents.player;

import interaction.Interaction;
import matchcomponents.move.MoveAttempt;

public abstract class Player {
    private final boolean isWhite;

    public Player(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public abstract MoveAttempt getMove(Interaction interaction);

    public boolean isWhite() {
        return isWhite;
    }
}
