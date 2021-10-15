
import java.util.ArrayList;

import javax.swing.Icon;


public abstract class Piece {

    private final boolean WHITE;

    public Piece(boolean white) {
        this.WHITE = white;
    }

    public abstract boolean isValid(Spot start, Spot end, Board board);

    public abstract String toString();

    public boolean isWhite() {
        return this.WHITE;
    }

    public ArrayList<Spot> getPossibleMoves(Spot start, Board board) {
        ArrayList<Spot> moves = new ArrayList<Spot>();
        Spot[][] gameboard = board.getBoard();
        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard[i].length; j++) {
                if (this.isValid(start, gameboard[i][j], board)) {
                    moves.add(gameboard[i][j]);
                }
            }
        }
        return moves;
    }

    public static int inBounds(int val) {
        if (val > 7) {
            return 7;
        }
        if (val < 0) {
            return 0;
        } else {
            return val;
        }
    }

    public Board testMove(Spot start, Spot end, Board board) {
        Board test = new Board();

        int startX = start.getCol();
        int startY = start.getRow();

        int endX = end.getCol();
        int endY = end.getRow();

        Piece moved = start.getPiece();

        test.getSpot(startX, startY).setPiece(null);
        test.getSpot(endX, endY).setPiece(moved);
        return test;
    }

    public boolean causesCheck(Spot start, Spot end, Board board) {
        Board test = new Board();
        Spot newSpot;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Spot spot = board.getSpot(i, j);
                if (spot.getPiece() != null) {
                    newSpot = new Spot(i, j, spot.getPiece(), board);
                } else {
                    newSpot = new Spot(i, j, null, board);
                }
                test.setElement(newSpot, i, j);
            }
        }
        Piece moved = test.getSpot(start.getCol(), start.getRow()).getPiece();
        test.getSpot(start.getCol(), start.getRow()).setPiece(null);
        test.getSpot(end.getCol(), end.getRow()).setPiece(moved);
        if (test.inCheck(isWhite())) {
            return true;
        }
        return false;
    }

    public void move(Spot start, Spot end, Board board) {
        Piece moved = start.getPiece();
        Icon icon = start.getIcon();
        board.setElement(new Spot(start.getCol(), start.getRow(), null, board), start.getCol(), start.getRow());
        board.setElement(new Spot(end.getCol(), end.getRow(), moved, icon, board), end.getCol(), end.getRow());
    }

    public Icon getPng() {
        if (this.isWhite()) {
            if (this instanceof Pawn) {
                return Spot.wpawn;
            }
            if (this instanceof Rook) {
                return Spot.wrook;
            }
            if (this instanceof Bishop) {
                return Spot.wbishop;
            }
            if (this instanceof Knight) {
                return Spot.wknight;
            }
            if (this instanceof Queen) {
                return Spot.wqueen;
            }
            if (this instanceof King) {
                return Spot.wking;
            }
        }
        if (this instanceof Pawn) {
            return Spot.bpawn;
        }
        if (this instanceof Rook) {
            return Spot.brook;
        }
        if (this instanceof Bishop) {
            return Spot.bbishop;
        }
        if (this instanceof Knight) {
            return Spot.bknight;
        }
        if (this instanceof Queen) {
            return Spot.bqueen;
        }
        if (this instanceof King) {
            return Spot.bking;
        }
        return null;
    }

}
