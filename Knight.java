public class Knight extends Piece {

    public Knight(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValid(Spot start, Spot end, Board board) {
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

            int dX = Math.abs(startX - endX);
            int dY = Math.abs(startY - endY);

            if (dX * dY == 2) {
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
            return " WKn ";
        } else {
            return " BKn ";
        }
    }

}
