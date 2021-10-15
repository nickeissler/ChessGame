

public class Pawn extends Piece {

    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValid(Spot start, Spot end, Board board) {
        int startX = start.getCol();
        int startY = start.getRow();

        if (end != null) {
            int endX = end.getCol();
            int endY = end.getRow();

            int dx = Math.abs(endX - startX);
            int dy = Math.abs(endY - startY);

            if (end.getPiece() != null) {
                if (dx * dy != 1) {
                    return false;
                }
                if (this.isWhite()) {
                    if (endY - startY == -1) {
                        if (this.causesCheck(start, end, board)) {
                            return false;
                        }
                        return true;
                    }
                    return false;
                }
                if (!this.isWhite()) {
                    if (endY - startY == 1) {
                        if (this.causesCheck(start, end, board)) {
                            return false;
                        }
                        return true;
                    }
                    return false;
                }
            } else {
                if (dx != 0) {
                    return false;
                }
                if (this.isWhite()) {
                    if (startY == 6 && endY == 4) {
                        if (!this.isObstructed(start, end, board) ) {
                            if (this.causesCheck(start, end, board)) {
                                return false;
                            }
                            return true;
                        }
                    }
                    if (endY - startY == -1) {
                        if (this.causesCheck(start, end, board)) {
                            return false;
                        }
                        return true;
                    }
                }
                else if (!this.isWhite()) {
                    if (startY == 1 && endY == 3) {
                        if (!this.isObstructed(start, end, board)) {
                            if (this.causesCheck(start, end, board)) {
                                return false;
                            }
                            return true;
                        }
                    }
                    if (endY - startY == 1) {
                        if (this.causesCheck(start, end, board)) {
                            return false;
                        }
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public boolean isObstructed(Spot start, Spot end, Board board) {
        int startX = start.getCol();
        int startY = start.getRow();

        int endY = end.getRow();

        int dy = endY - startY;

        if (dy > 0) {
            if (board.getSpot(startX, endY - 1).getPiece() != null) {
                return true;
            }
        }
        if (dy < 0) {
            if (board.getSpot(startX, endY + 1).getPiece() != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (this.isWhite()) {
            return " WP  ";
        } else {
            return " BP  ";
        }
    }

}
