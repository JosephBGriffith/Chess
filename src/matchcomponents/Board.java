package matchcomponents;

import matchcomponents.piece.Piece;
import matchcomponents.piece.PieceFactory;

public class Board {
    public static final int CHESS_BOARD_SIZE = 8;

    private Piece[][] pieces;

    public Board() {
        pieces = new Piece[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE];
        setupNewBoard();
    }

    public Piece getPieceAt(byte file, byte rank) {
        return pieces[rank][file];
    }

    public void setPieceAt(byte file, byte rank, Piece piece) {
        pieces[rank][file] = piece;
    }

    private void setupNewBoard() {
        setupNewBoardByColor(true);
        setupNewBoardByColor(false);
    }

    private void setupNewBoardByColor(boolean isWhite) {
        Piece[] rank = pieces[isWhite ? 1 : 6];

        for(int file = 0; file < CHESS_BOARD_SIZE; file++) {
            rank[file] = PieceFactory.pawn(isWhite);
        }

        rank = pieces[isWhite ? 0 : 7];

        rank[0] = PieceFactory.rook(isWhite);
        rank[7] = PieceFactory.rook(isWhite);

        rank[1] = PieceFactory.knight(isWhite);
        rank[6] = PieceFactory.knight(isWhite);

        rank[2] = PieceFactory.bishop(isWhite);
        rank[5] = PieceFactory.bishop(isWhite);

        rank[3] = PieceFactory.queen(isWhite);
        rank[4] = PieceFactory.king(isWhite);
    }
}
