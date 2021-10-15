
public class Rook extends Piece {
    
    private boolean hasMoved;

    public Rook(boolean isWhite) {
        super(isWhite);
        this.hasMoved = true;
    }
    
    public Rook(boolean isWhite, boolean hasMoved) {
        super(isWhite);
        this.hasMoved = hasMoved;
    }

    @Override
    public boolean isValid(Spot start, Spot end, Board board) {
        if (this.isObstructed(start, end, board)) {
            return false;
        }
        if (end != null) {
            if (end.getPiece() != null) {
                if (this.isWhite() == end.getPiece().isWhite()) {
                    return false;
                }
            }
            int startX = start.getCol();
            int startY = start.getRow();

            int endX = end.getCol();
            int endY = end.getRow();

            if (startX == endX || startY == endY) {
                if (this.causesCheck(start, end, board)) {
                    return false;
                }
                return true;
            } else {
                return false;
            }
        }
        return false;

    }

    @Override
    public String toString() {
        if (this.isWhite()) {
            return " WR  ";
        } else {
            return " BR  ";
        }
    }

    public boolean isObstructed(Spot start, Spot end, Board board) {
        Spot[][] gameboard = board.getBoard();

        int startX = start.getCol();
        int startY = start.getRow();

        if (end != null) {
            int endX = end.getCol();
            int endY = end.getRow();

            int dX = endX - startX;
            int dY = endY - startY;

            if (dX > 0) {
                for (int i = startX + 1; i < endX; i++) {
                    if (gameboard[i][startY].isOccupied()) {
                        return true;
                    }
                }
                return false;
            }
            if (dX < 0) {
                for (int i = startX - 1; i > endX; i--) {
                    if (gameboard[i][startY].isOccupied()) {
                        return true;
                    }
                }
                return false;
            }
            if (dY > 0) {
                for (int i = startY + 1; i < endY; i++) {
                    if (gameboard[startX][i].isOccupied()) {
                        return true;
                    }
                }
                return false;
            }
            if (dY < 0) {
                for (int i = startY - 1; i > endY; i--) {
                    if (gameboard[startX][i].isOccupied()) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
        return false;
    }
    
    public boolean getHasMoved() {
        return this.hasMoved;
    }

}
