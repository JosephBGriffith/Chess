package interaction;

import application.ChessMatch;
import matchcomponents.Board;
import matchcomponents.piece.Piece;
import matchcomponents.piece.PieceType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Interaction extends JFrame implements ActionListener {
    private final ChessMatch chessMatch;
    private final LocalizedButton[][] buttons;
    private byte penultimateFile;
    private byte penultimateRank;
    private byte ultimateFile;
    private byte ultimateRank;

    public Interaction(ChessMatch chessMatch) {
        this.chessMatch = chessMatch;
        buttons = new LocalizedButton[Board.CHESS_BOARD_SIZE][Board.CHESS_BOARD_SIZE];
        Container container = getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        boolean isWhite = false;

        LocalizedButton button;
        for(byte rank = Board.CHESS_BOARD_SIZE - 1; rank >= 0; rank--) {
            isWhite = !isWhite;
            for(byte file = 0; file < Board.CHESS_BOARD_SIZE; file++) {
                button = buttons[file][rank] = new LocalizedButton(file, rank);
                button.setPreferredSize(new Dimension(100, 100));
                button.setFont(new Font("SansSerif", Font.BOLD, 64));
                button.setBackground(isWhite ? Color.WHITE : Color.BLACK);
                button.addActionListener(this);
                container.add(button);
                isWhite = !isWhite;
            }
        }

        setVisible(true);
        setSize(816, 839);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void update(Board board) {
        for(byte file = 0; file < Board.CHESS_BOARD_SIZE; file++) {
            for(byte rank = 0; rank < Board.CHESS_BOARD_SIZE; rank++) {
                String buttonText;
                boolean isWhite = true;
                Piece piece = board.getPieceAt(file, rank);
                if(piece == null) {
                    buttonText = "";
                }
                else {
                    PieceType type = piece.getType();
                    isWhite = piece.isWhite();

                    switch(type) {
                        case PAWN:
                            buttonText = isWhite ? "\u2659" : "\u265F";
                            break;
                        case ROOK:
                            buttonText = isWhite ? "\u2656" : "\u265C";
                            break;
                        case KNIGHT:
                            buttonText = isWhite ? "\u2658" : "\u265E";
                            break;
                        case BISHOP:
                            buttonText = isWhite ? "\u2657" : "\u265D";
                            break;
                        case QUEEN:
                            buttonText = isWhite ? "\u2655" : "\u265B";
                            break;
                        default:
                            buttonText = isWhite ? "\u2654" : "\u265A";
                    }
                }
                buttons[file][rank].setText(buttonText);
                buttons[file][rank].setForeground(isWhite ? Color.ORANGE : Color.RED);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        LocalizedButton button = (LocalizedButton) event.getSource();

        if(penultimateFile == -1) {
           penultimateFile = button.getFile();
           penultimateRank = button.getRank();
        }
        else {
            ultimateFile = button.getFile();
            ultimateRank = button.getRank();

            chessMatch.move(this);
        }
    }

    public void resetClicks() {
        penultimateFile = -1;
        penultimateRank = -1;
        ultimateFile = -1;
        ultimateRank = -1;
    }

    public byte getUltimateFile() {
        return ultimateFile;
    }

    public byte getUltimateRank() {
        return ultimateRank;
    }

    public byte getPenultimateFile() {
        return penultimateFile;
    }

    public byte getPenultimateRank() {
        return penultimateRank;
    }
}
