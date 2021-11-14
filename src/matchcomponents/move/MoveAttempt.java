package matchcomponents.move;

import matchcomponents.Board;
import matchcomponents.move.path.Displacement;
import matchcomponents.move.path.Path;
import matchcomponents.piece.Piece;
import matchcomponents.piece.PieceType;

public class MoveAttempt {
    private final byte pieceFile;
    private final byte pieceRank;
    private final byte toFile;
    private final byte toRank;
    private final boolean isWhite;

    private boolean isEnPassant;
    private boolean isCastle;
    private boolean isCheck;

    public MoveAttempt(byte pieceFile, byte pieceRank, byte toFile, byte toRank, boolean isWhite) {
        this.pieceFile = pieceFile;
        this.pieceRank = pieceRank;
        this.toFile = toFile;
        this.toRank = toRank;
        this.isWhite = isWhite;
        isEnPassant = false;
        isCastle = false;
        isCheck = false;
    }

    public byte getPieceFile() {
        return pieceFile;
    }

    public byte getPieceRank() {
        return pieceRank;
    }

    public byte getToFile() {
        return toFile;
    }

    public byte getToRank() {
        return toRank;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public boolean isEnPassant() {
        return isEnPassant;
    }

    public boolean isCastle() {
        return isCastle;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public boolean isValid(Board board) {
        Piece piece = board.getPieceAt(getPieceFile(), getPieceRank());

        if(piece == null || piece.isWhite() != isWhite()) {
            return false;
        }

        PieceType type = piece.getType();

        switch(type) {
            case PAWN:
                return isPawnRegularMove(board) || isPawnFirstMove(board) || isPawnDiagonalMove(board) || setIsEnPassant(board);
            case KING:
                if(setIsCheck()) {
                    return false;
                }
            case ROOK:
                if(setIsCastle()) {
                    return false;
                }
            default:
                return hasClearPath(type, board);
        }
    }

    private boolean hasClearPath(PieceType pieceType, Board board) {

        for(Path path : pieceType.getPathSet()) {
            if(pathIsClear(path, board)) {
                return true;
            }
        }

        return false;
    }

    private boolean pathIsClear(Path path, Board board){
        Displacement[] displacements = path.getDisplacements();

        for(Displacement displacement : displacements) {
            byte filePosition = (byte) (displacement.getFileDisplacement() + getPieceFile());
            byte rankPosition = (byte) (displacement.getRankDisplacement() + getPieceRank());

            if(isOutOfBounds(filePosition, rankPosition)) {
                return false;
            }

            if(filePosition == getToFile() && rankPosition == getToRank()) {
                Piece toPiece = toPiece(board);
                return toPiece == null || toPiece.isWhite() != isWhite();
            }

            if(board.getPieceAt(filePosition, rankPosition) != null) {
                return false;
            }
        }

        return false;
    }

    private boolean setIsEnPassant(Board board) {
        return isEnPassant();
    }

    private boolean setIsCastle() {
        return isCastle();
    }

    private boolean setIsCheck() {
        return isCheck();
    }

    private boolean isPawnRegularMove(Board board) {
        if(fileDifference() != 0 || rankDifference() != (isWhite() ? (byte) 1 : (byte) -1)) {
            return false;
        }

        return board.getPieceAt(toFile, toRank) == null;
    }

    private boolean isPawnFirstMove(Board board) {
        if(fileDifference() != 0 || rankDifference() != (isWhite() ? (byte) 2 : (byte) -2)) {
            return false;
        }

        if(getPieceRank() != (isWhite() ? 1 : 6)) {
            return false;
        }

        if(board.getPieceAt(getToFile(), (byte) (getToRank() + (isWhite() ? -1 : 1))) != null) {
            return false;
        }

        return toPiece(board) == null;
    }

    private boolean isPawnDiagonalMove(Board board) {
        if((fileDifference() != -1 && fileDifference() != 1) || rankDifference() != (isWhite() ? (byte) 1 : (byte) -1)) {
            return false;
        }

        Piece piece = toPiece(board);

        return piece != null && piece.isWhite() != isWhite();
    }

    private Piece toPiece(Board board) {
        return board.getPieceAt(getToFile(), getToRank());
    }

    private boolean isOutOfBounds(byte file, byte rank) {
        byte[] fileAndRank = new byte[2];
        fileAndRank[0] = file;
        fileAndRank[1] = rank;

        for(byte fileOrRank : fileAndRank) {
            if(fileOrRank < 0 || fileOrRank >= Board.CHESS_BOARD_SIZE) {
                return true;
            }
        }

        return false;
    }

    private byte fileDifference() {
        return (byte) (getToFile() - getPieceFile());
    }

    private byte rankDifference() {
        return (byte) (getToRank() - getPieceRank());
    }
}
