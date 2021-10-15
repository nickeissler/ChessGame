public class King extends Piece {

    private boolean hasMoved;

    public King(boolean isWhite, boolean hasMoved) {
        super(isWhite);
        this.hasMoved = hasMoved;
    }

    public King(boolean isWhite) {
        super(isWhite);
        this.hasMoved = true;
    }

    @Override
    public boolean isValid(Spot start, Spot end, Board board) {
        if (end != null) {
            if (board.validCastle(start, end)) {
                return true;
            }
            if (end.getPiece() != null) {
                if (this.isWhite() == end.getPiece().isWhite()) {
                    return false;
                }
            }
            int startX = start.getCol();
            int startY = start.getRow();

            int endX = end.getCol();
            int endY = end.getRow();

            int dx = Math.abs(startX - endX);
            int dy = Math.abs(startY - endY);

            if (dx * dy == 1) {
                if (this.causesCheck(start, end, board)) {
                    return false;
                }
                return true;
            }
            if (dx == 0 && dy == 1) {
                if (this.causesCheck(start, end, board)) {
                    return false;
                }
                return true;
            }
            if (dx == 1 && dy == 0) {
                if (this.causesCheck(start, end, board)) {
                    return false;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public String toString() {
        if (this.isWhite()) {
            return " WK  ";
        } else {
            return " BK  ";
        }
    }
    
    public boolean getHasMoved() {
        return this.hasMoved;
    }
}
