public class Queen extends Piece {

    public Queen(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValid(Spot start, Spot end, Board board) {
        if (end != null) {
            if (this.isObstructed(start, end, board)) {
                return false;
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

            if (startX == endX || startY == endY || Math.abs(endX - startX) == Math.abs(endY - startY)) {
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
            return " WQ  ";
        } else {
            return " BQ  ";
        }
    }

    public boolean isObstructed(Spot start, Spot end, Board board) {
        Spot[][] gameboard = board.getBoard();

        int startX = start.getCol();
        int startY = start.getRow();

        int endX = end.getCol();
        int endY = end.getRow();

        if (startX == endX || startY == endY) {
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
        } else {
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
    }
}
