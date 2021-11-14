package matchcomponents.piece;

public class PieceFactory {
    public static Piece pawn(boolean isWhite) {
        return new Piece(PieceType.PAWN, isWhite);
    }

    public static Piece rook(boolean isWhite) {
        return new Piece(PieceType.ROOK, isWhite);
    }

    public static Piece knight(boolean isWhite) {
        return new Piece(PieceType.KNIGHT, isWhite);
    }

    public static Piece bishop(boolean isWhite) {
        return new Piece(PieceType.BISHOP, isWhite);
    }

    public static Piece queen (boolean isWhite) {
        return new Piece(PieceType.QUEEN, isWhite);
    }

    public static Piece king (boolean isWhite) {
        return new Piece(PieceType.KING, isWhite);
    }
}
