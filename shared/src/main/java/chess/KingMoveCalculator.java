package chess;

import java.util.ArrayList;
import java.util.Collection;

import static chess.CheckSpace.processMove;

public class KingMoveCalculator {

    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> validMoves = new ArrayList<>();

        int currRow;
        int currCol;

        // Directions: Up, Up-Right, Right, Down-Right, Down, Down-Left, Left, Up-Left
        for (int i = 0; i < 8; i++) {
            currRow = position.getRow();
            currCol = position.getColumn();

            switch (i) {
                case 0:
                    // Up
                    currRow = CheckSpace.incrementOne(currRow);
                    break;
                case 1:
                    // Up-Right
                    currRow = CheckSpace.incrementOne(currRow);
                    currCol = CheckSpace.incrementOne(currCol);
                    break;
                case 2:
                    // Right
                    currCol = CheckSpace.incrementOne(currCol);
                    break;
                case 3:
                    // Down-Right
                    currRow = CheckSpace.decrementOne(currRow);
                    currCol = CheckSpace.incrementOne(currCol);
                    break;
                case 4:
                    // Down
                    currRow = CheckSpace.decrementOne(currRow);
                    break;
                case 5:
                    // Down-Left
                    currRow = CheckSpace.decrementOne(currRow);
                    currCol = CheckSpace.decrementOne(currCol);
                    break;
                case 6:
                    // Left
                    currCol = CheckSpace.decrementOne(currCol);
                    break;
                case 7:
                    // Up-Left
                    currRow = CheckSpace.incrementOne(currRow);
                    currCol = CheckSpace.decrementOne(currCol);
                    break;
            }
            processMove(board, position, currRow, currCol, validMoves);
        }

        return validMoves;
    }
}
