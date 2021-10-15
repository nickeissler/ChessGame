public class Bishop extends Piece {

    public Bishop(boolean isWhite) {
        super(isWhite);
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

            if (Math.abs(endX - startX) == Math.abs(endY - startY)) {
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
            return " WB  ";
        } else {
            return " BB  ";
        }
    }

    public boolean isObstructed(Spot start, Spot end, Board board) {
        Spot[][] gameboard = board.getBoard();
        int startX = start.getCol();
        int startY = start.getRow();

        if (end != null) {
            int endX = end.getCol();
            int endY = end.getRow();

            int dx, dy;
            if (endX > startX) {
                dx = 1;
            } else {
                dx = -1;
            }
            if (endY > startY) {
                dy = 1;
            } else {
                dy = -1;
            }
            for (int i = 1; i < Math.abs(endX - startX); i++) {
                int col = inBounds(startX + dx * i);
                int row = inBounds(startY + dy * i);
                if (gameboard[col][row].getPiece() != null) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

}
