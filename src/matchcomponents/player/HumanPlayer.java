package matchcomponents.player;

import interaction.Interaction;
import matchcomponents.move.MoveAttempt;

public class HumanPlayer extends Player {

    public HumanPlayer(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public MoveAttempt getMove(Interaction interaction) {
        byte pieceFile = interaction.getPenultimateFile();
        byte pieceRank = interaction.getPenultimateRank();
        byte toFile = interaction.getUltimateFile();
        byte toRank = interaction.getUltimateRank();
        return new MoveAttempt(pieceFile, pieceRank, toFile, toRank, isWhite());
    }
}
