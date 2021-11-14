package application;

import interaction.Interaction;
import matchcomponents.Board;
import matchcomponents.move.MoveAttempt;
import matchcomponents.piece.Piece;
import matchcomponents.player.Player;

public class ChessMatch {
    private final Player white;
    private final Player black;
    private final Board board;
    private Player currentPlayer;

    public ChessMatch(Player white, Player black, Board board) {
        currentPlayer = this.white = white;
        this.black = black;
        this.board = board;
    }

    public void move(Interaction interaction) {
        if(move(interaction, currentPlayer)) {
            interaction.update(board);
            switchPlayers();
        }

        interaction.resetClicks();
    }

    private boolean move(Interaction interaction, Player player) {
        MoveAttempt attempt = player.getMove(interaction);

        if(attempt.isValid(board)) {
            Piece piece = board.getPieceAt(attempt.getPieceFile(), attempt.getPieceRank());
            board.setPieceAt(attempt.getPieceFile(), attempt.getPieceRank(), null);
            board.setPieceAt(attempt.getToFile(), attempt.getToRank(), piece);
            return true;
        }

        return false;
    }

    private void switchPlayers() {
        currentPlayer = currentPlayer.isWhite() ? black : white;
    }
}
