package chess;

/**
 * Represents a single square position on a chess board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPosition {

    private final int row;
    private final int col;

    public ChessPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * @return which row this position is in
     * 1 codes for the bottom row
     */
    public int getRow() {
        return row;
    }

    @Override
    public int hashCode() {
        // Generate a hash code based on the row and column values
        return row * 31 + col;
    }

    @Override
    public boolean equals(Object obj) {
        // Check if the object is the same instance
        if (this == obj)
            return true;
        // Check if the object is null or of different type
        if (obj == null || getClass() != obj.getClass())
            return false;
        // Since the object is of the same type, cast it to ChessPosition
        ChessPosition other = (ChessPosition) obj;
        // Check if row and column values are equal
        return row == other.row && col == other.col;
    }

    /**
     * @return which column this position is in
     * 1 codes for the left row
     */
    public int getColumn() {
        return col;
    }

}
