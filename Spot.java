
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Spot extends JButton {

    private final int row;
    private final int col;
    private Piece piece;
    private static Spot clicked = null;

    private Board board;

    private static int buttonsClicked = 0;
    private static int movesCompleted = 0;
    private static Piece beingMoved = null;

    public static Icon wpawn = new ImageIcon("chess pieces/wpawn.png");
    public static Icon bpawn = new ImageIcon("chess pieces/bpawn.png");
    public static Icon wrook = new ImageIcon("chess pieces/wrook.png");
    public static Icon brook = new ImageIcon("chess pieces/brook.png");
    public static Icon wbishop = new ImageIcon("chess pieces/wbishop.png");
    public static Icon bbishop = new ImageIcon("chess pieces/bbishop.png");
    public static Icon wknight = new ImageIcon("chess pieces/wknight.png");
    public static Icon bknight = new ImageIcon("chess pieces/bknight.png");
    public static Icon wqueen = new ImageIcon("chess pieces/wqueen.png");
    public static Icon bqueen = new ImageIcon("chess pieces/bqueen.png");
    public static Icon wking = new ImageIcon("chess pieces/wking.png");
    public static Icon bking = new ImageIcon("chess pieces/bking.png");

    public Spot(int col, int row, Piece piece, Icon icon, Board board) {
        super(icon);
        this.col = col;
        this.row = row;
        this.piece = piece;
        this.board = board;
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (buttonsClicked % 2 == 0) {
                    if (Spot.this.piece != null) {
                        if (Spot.this.piece.isWhite() == whitesTurn() || !Spot.this.piece.isWhite() == !whitesTurn()) {
                            beingMoved = Spot.this.piece;
                            clicked = Spot.this;
                            buttonsClicked++;
                        }
                    }
                } else {
                    if (beingMoved.isValid(clicked, Spot.this, Spot.this.board)) {
                        Spot.this.board.move(clicked, Spot.this);
                        buttonsClicked++;
                        movesCompleted++;
                    } else {
                        buttonsClicked--;
                        beingMoved = null;
                    }
                }
            }
        });

    }

    public Spot(int col, int row, Piece piece, Board board) {
        this.col = col;
        this.row = row;
        this.piece = piece;
        this.board = board;
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (buttonsClicked % 2 == 0) {
                    ;
                } else {
                    if (beingMoved.isValid(clicked, Spot.this, Spot.this.board)) {
                        Spot.this.board.move(clicked, Spot.this);
                        buttonsClicked++;
                        movesCompleted++;
                    } else {
                        buttonsClicked--;
                        beingMoved = null;
                    }
                }
            }
        });
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isOccupied() {
        if (this.getPiece() != null) {
            return true;
        }
        return false;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }


    public void draw() {
        if ((this.col + this.row) % 2 == 0) {
            this.setBackground(Color.LIGHT_GRAY);
            this.setOpaque(false);
        } else {
            this.setBackground(Color.DARK_GRAY);
            this.setOpaque(false);
        }
    }

    public Icon getPng() {
        if (this.getPiece() != null) {
            if (this.getPiece().isWhite()) {
                if (this.getPiece() instanceof Pawn) {
                    return wpawn;
                }
                if (this.getPiece() instanceof Rook) {
                    return wrook;
                }
                if (this.getPiece() instanceof Bishop) {
                    return wbishop;
                }
                if (this.getPiece() instanceof Knight) {
                    return wknight;
                }
                if (this.getPiece() instanceof Queen) {
                    return wqueen;
                }
                if (this.getPiece() instanceof King) {
                    return wking;
                }
            }
            if (this.getPiece() instanceof Pawn) {
                return bpawn;
            }
            if (this.getPiece() instanceof Rook) {
                return brook;
            }
            if (this.getPiece() instanceof Bishop) {
                return bbishop;
            }
            if (this.getPiece() instanceof Knight) {
                return bknight;
            }
            if (this.getPiece() instanceof Queen) {
                return bqueen;
            }
            if (this.getPiece() instanceof King) {
                return bking;
            }
        }
        return null;
    }

    public int getClicks() {
        return buttonsClicked;
    }

    public int getMoves() {
        return movesCompleted;
    }

    public static boolean whitesTurn() {
        return movesCompleted % 2 == 0;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    
}
