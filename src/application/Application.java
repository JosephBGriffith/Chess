package application;

import interaction.Interaction;
import matchcomponents.Board;
import matchcomponents.piece.PieceType;
import matchcomponents.player.PlayerFactory;

public class Application {
    public static void main(String[] args) {
        PieceType.setupPathSets();
        Board board = new Board();
        ChessMatch chessMatch = new ChessMatch(PlayerFactory.whitePlayer(), PlayerFactory.blackPlayer(), board);
        Interaction interaction = new Interaction(chessMatch);
        interaction.resetClicks();
        interaction.update(board);
    }
}
