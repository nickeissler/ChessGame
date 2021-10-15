import java.awt.ComponentOrientation;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class Board extends JPanel {

    private Spot[][] board = new Spot[8][8];

    public Board() {
        super(new GridLayout(8, 8));
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.newboard();
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.add(board[j][i]);
            }
        }
    }

    public Spot getSpot(int col, int row) {
        return this.board[col][row];
    }

    public Spot[][] getBoard() {
        Spot[][] copyBoard = new Spot[8][8];
        for (int i = 0; i < copyBoard.length; i++) {
            for (int j = 0; j < copyBoard[i].length; j++) {
                Spot original = this.board[i][j];
                if (original.getPiece() != null) {
                    copyBoard[i][j] = new Spot(i, j, original.getPiece(), this);
                } else {
                    copyBoard[i][j] = new Spot(i, j, null, this);
                }
            }
        }
        return copyBoard;
    }

    public Spot kingSpot(boolean white) {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (this.board[i][j].getPiece() != null) {
                    if (this.board[i][j].getPiece() instanceof King) {
                        if (this.board[i][j].getPiece().isWhite() == white) {
                            return this.board[i][j];
                        }
                    }
                }
            }
        }
        return null;
    }

    public boolean inCheck(boolean white) {
        Spot myKing = kingSpot(white);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getPiece() != null) {
                    if (board[i][j].getPiece().isWhite() != white) {
                        if (board[i][j].getPiece().isValid(board[i][j], myKing, this)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void setElement(Spot spot, int i, int j) {
        this.board[i][j] = spot;
    }

    public void setBoard(Spot[][] board) {
        this.board = board;
    }

    public void newboard() {
        board[0][0] = new Spot(0, 0, new Rook(false), Spot.brook, this);
        board[1][0] = new Spot(1, 0, new Knight(false), Spot.bknight, this);
        board[2][0] = new Spot(2, 0, new Bishop(false), Spot.bbishop, this);
        board[3][0] = new Spot(3, 0, new Queen(false), Spot.bqueen, this);
        board[4][0] = new Spot(4, 0, new King(false, false), Spot.bking, this);
        board[5][0] = new Spot(5, 0, new Bishop(false), Spot.bbishop, this);
        board[6][0] = new Spot(6, 0, new Knight(false), Spot.bknight, this);
        board[7][0] = new Spot(7, 0, new Rook(false, false), Spot.brook, this);

        for (int i = 0; i < board[1].length; i++) {
            board[i][1] = new Spot(i, 1, new Pawn(false), Spot.bpawn, this);
        }

        // white player pieces
        board[0][7] = new Spot(0, 7, new Rook(true), Spot.wrook, this);
        board[1][7] = new Spot(1, 7, new Knight(true), Spot.wknight, this);
        board[2][7] = new Spot(2, 7, new Bishop(true), Spot.wbishop, this);
        board[3][7] = new Spot(3, 7, new Queen(true), Spot.wqueen, this);
        board[4][7] = new Spot(4, 7, new King(true, false), Spot.wking, this);
        board[5][7] = new Spot(5, 7, new Bishop(true), Spot.wbishop, this);
        board[6][7] = new Spot(6, 7, new Knight(true), Spot.wknight, this);
        board[7][7] = new Spot(7, 7, new Rook(true, false), Spot.wrook, this);

        for (int i = 0; i < board[6].length; i++) {
            board[i][6] = new Spot(i, 6, new Pawn(true), Spot.wpawn, this);
        }

        // empty spots
        for (int i = 2; i < 5; i += 2) {
            for (int j = 0; j < board[i].length; j++) {
                board[j][i] = new Spot(j, i, null, this);
                board[j][i] = new Spot(j, i, null, this);
            }
        }

        for (int i = 3; i < 6; i += 2) {
            for (int j = 0; j < board[i].length; j++) {
                board[j][i] = new Spot(j, i, null, this);
                board[j][i] = new Spot(j, i, null, this);
            }
        }
    }

    public void move(Spot start, Spot end) {
        if (this.validCastle(start, end)) {
            this.castle(start, end);
        }
        if (start.getPiece() != null) {
            Piece piece = start.getPiece();
            Icon icon = start.getIcon();
            if (piece.isValid(start, end, this)) {
                this.board[start.getCol()][start.getRow()] = new Spot(start.getCol(), start.getRow(), null, this);
                this.board[end.getCol()][end.getRow()] = new Spot(end.getCol(), end.getRow(), piece, icon, this);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        this.removeAll();
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.add(board[j][i]);
            }
        }
        this.validate();
    }

    public void update() {
        this.repaint();
        this.validate();
    }

    public boolean pawnToPromote() {
        for (int i = 0; i < 8; i++) {
            if (this.board[i][0].getPiece() != null) {
                if (this.board[i][0].getPiece() instanceof Pawn) {
                    return true;
                }
            }
            if (this.board[i][7].getPiece() != null) {
                if (this.board[i][7].getPiece() instanceof Pawn) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean getPawnColor() {
        for (int i = 0; i < 8; i++) {
            if (this.board[i][0].getPiece() != null) {
                if (this.board[i][0].getPiece() instanceof Pawn) {
                    return true;
                }
            }
            if (this.board[i][7].getPiece() != null) {
                if (this.board[i][7].getPiece() instanceof Pawn) {
                    return false;
                }
            }
        }
        return false;
    }

    public void promotePawn(Piece piece) {
        for (int i = 0; i < 8; i++) {
            if (this.board[i][0].getPiece() != null) {
                if (this.board[i][0].getPiece() instanceof Pawn) {
                    board[i][0] = new Spot(i, 0, piece, piece.getPng(), this);
                }
            }
            if (this.board[i][7].getPiece() != null) {
                if (this.board[i][7].getPiece() instanceof Pawn) {
                    board[i][7] = new Spot(i, 7, piece, piece.getPng(), this);
                }
            }
        }
    }

    public boolean canMakeMove(boolean white) {
        ArrayList<Spot> allMoves = new ArrayList<Spot>();
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (this.board[i][j].getPiece() != null) {
                    if (this.board[i][j].getPiece().isWhite() == white) {
                        ArrayList<Spot> moves = this.board[i][j].getPiece().getPossibleMoves(this.board[i][j], this);
                        for (Spot spot : moves) {
                            allMoves.add(spot);
                        }
                    }
                }
            }
        }
        return !allMoves.isEmpty();
    }

    public boolean gameover() {
        if (Spot.whitesTurn()) {
            if (!this.canMakeMove(true)) {
                return true;
            }
            return false;
        }
        if (!Spot.whitesTurn()) {
            if (!this.canMakeMove(false)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean checkmate(boolean white) {
        if (!this.canMakeMove(white) && this.inCheck(white)) {
            return true;
        }
        return false;
    }

    public boolean stalemate(boolean white) {
        if (!this.canMakeMove(white) && !this.inCheck(white)) {
            return true;
        }
        return false;
    }

    public boolean validCastle(Spot start, Spot end) {
        if (start.getPiece() != null) {
            if (start.getPiece() instanceof King) {
                if (end.getPiece() != null) {
                    if (end.getPiece() instanceof Rook) {
                        King king = (King) start.getPiece();
                        Rook rook = (Rook) end.getPiece();
                        if (!king.getHasMoved() && !rook.getHasMoved()) {
                            if (king.isWhite()) {
                                if (end.getCol() == 7) {
                                    if (this.board[5][7].getPiece() == null && this.board[6][7].getPiece() == null) {
                                        return true;
                                    }
                                    return false;
                                }
                                if (end.getCol() == 0) {
                                    if (this.board[1][7].getPiece() == null && this.board[2][7].getPiece() == null
                                            && board[3][7].getPiece() == null) {
                                        return true;
                                    }
                                    return false;
                                }
                                return false;
                            }
                            if (!king.isWhite()) {
                                if (end.getCol() == 7) {
                                    if (this.board[5][0].getPiece() == null && this.board[6][0].getPiece() == null) {
                                        return true;
                                    }
                                    return false;
                                }
                                if (end.getCol() == 0) {
                                    if (this.board[1][0].getPiece() == null && this.board[2][0].getPiece() == null
                                            && board[3][0].getPiece() == null) {
                                        return true;
                                    }
                                    return false;
                                }
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public void castle(Spot start, Spot end) {
        if (this.validCastle(start, end)) {
            int startX = start.getCol();
            int startY = start.getRow();
            int endX = end.getCol();
            int endY = end.getRow();
            if (start.getPiece().isWhite()) {
                if (end.getCol() == 7) {
                    this.board[startX][startY] = new Spot(startX, startY, null, this);
                    this.board[6][7] = new Spot(6, 7, new King(true), Spot.wking, this);
                    this.board[5][7] = new Spot(5, 7, new Rook(true), Spot.wrook, this);
                    this.board[endX][endY] = new Spot(endX, endY, null, this);
                }
                if (end.getCol() == 0) {
                    this.board[startX][startY] = new Spot(startX, startY, null, this);
                    this.board[2][7] = new Spot(2, 7, new King(true), Spot.wking, this);
                    this.board[3][7] = new Spot(3, 7, new Rook(true), Spot.wrook, this);
                    this.board[endX][endY] = new Spot(endX, endY, null, this);
                }
            } else {
                if (end.getCol() == 7) {
                    this.board[startX][startY] = new Spot(startX, startY, null, this);
                    this.board[6][0] = new Spot(6, 0, new King(false), Spot.bking, this);
                    this.board[5][0] = new Spot(5, 0, new Rook(false), Spot.brook, this);
                    this.board[endX][endY] = new Spot(endX, endY, null, this);
                }
                if (end.getCol() == 0) {
                    this.board[startX][startY] = new Spot(startX, startY, null, this);
                    this.board[2][0] = new Spot(2, 0, new King(false), Spot.bking, this);
                    this.board[3][0] = new Spot(3, 0, new Rook(false), Spot.brook, this);
                    this.board[endX][endY] = new Spot(endX, endY, null, this);
                }
            }
        }
    }
}
