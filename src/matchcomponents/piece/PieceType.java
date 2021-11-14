package matchcomponents.piece;

import matchcomponents.Board;
import matchcomponents.move.path.Path;

import java.util.HashSet;
import java.util.Set;

public enum PieceType {
    PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING;

    private static final Set<Path> rookPathSet = new HashSet<>();
    private static final Set<Path> knightPathSet = new HashSet<>();
    private static final Set<Path> bishopPathSet = new HashSet<>();
    private static final Set<Path> queenPathSet = new HashSet<>();
    private static final Set<Path> kingPathSet = new HashSet<>();

    private static final byte NUMBER_OF_ROTATIONS = 4;

    public Set<Path> getPathSet() {
        if(this == ROOK) {
            return getRookPathSet();
        }

        if(this == KNIGHT) {
            return getKnightPathSet();
        }

        if(this == BISHOP) {
            return getBishopPathSet();
        }

        if(this == QUEEN) {
            return getQueenPathSet();
        }

        return getKingPathSet();
    }

    private static Set<Path> getRookPathSet() {
        return rookPathSet;
    }

    private static Set<Path> getKnightPathSet() {
        return knightPathSet;
    }

    private static Set<Path> getBishopPathSet() {
        return bishopPathSet;
    }

    private static Set<Path> getQueenPathSet() {
        return queenPathSet;
    }

    private static Set<Path> getKingPathSet() {
        return kingPathSet;
    }

    public static void setupPathSets() {
        setupKingPathSet();
        setupRookBishopQueenPathSet();
        setupKnightPathSet();
    }

    private static void setupKingPathSet() {
        Path path = new Path(1);
        path.appendDisplacement((byte) 0, (byte) 1);
        addRotations(kingPathSet, path);

        path = new Path(1);
        path.appendDisplacement((byte) 1, (byte) 1);
        addRotations(kingPathSet, path);
    }

    private static void setupRookBishopQueenPathSet() {
        Path rookPath = new Path(Board.CHESS_BOARD_SIZE - 1);
        Path bishopPath = new Path(Board.CHESS_BOARD_SIZE - 1);

        for(byte i = 1; i < Board.CHESS_BOARD_SIZE; i++) {
            rookPath.appendDisplacement((byte) 0, i);
            bishopPath.appendDisplacement(i, i);
        }

        addRotations(rookPathSet, rookPath);
        addRotations(bishopPathSet, bishopPath);

        queenPathSet.addAll(rookPathSet);
        queenPathSet.addAll(bishopPathSet);
    }

    private static void setupKnightPathSet() {
        Path path = new Path(1);
        path.appendDisplacement((byte) 1, (byte) 2);
        addRotations(knightPathSet, path);

        path = new Path(1);
        path.appendDisplacement((byte) 2, (byte) 1);
        addRotations(knightPathSet, path);
    }

    private static void addRotations(Set<Path> pathSet, Path path) {
        for(byte i = 0; i < NUMBER_OF_ROTATIONS; i++) {
            if(i > 0) {
                path = Path.pathRotated90DegreesClockwise(path);
            }

            pathSet.add(path);
        }
    }
}