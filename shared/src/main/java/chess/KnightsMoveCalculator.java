package chess;

import java.util.ArrayList;
import java.util.Collection;

import static chess.CheckSpace.processMove;

public class KnightsMoveCalculator {
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> validMoves = new ArrayList<>();

        int currRow;
        int currCol;

        // Directions: Up-left, Up-Right, Right-Up, Right-Down, Down-Right, Down-Left, Left-Up, Left-Down
        for (int i = 0; i < 8; i++) {
            currRow = position.getRow();
            currCol = position.getColumn();

            switch (i) {
                case 0:
                    // Up-left
                    currRow = CheckSpace.incrementTwo(currRow);
                    currCol = CheckSpace.decrementOne(currCol);
                    break;
                case 1:
                    // Up-Right
                    currRow = CheckSpace.incrementTwo(currRow);
                    currCol = CheckSpace.incrementOne(currCol);
                    break;
                case 2:
                    // Right-Up

                    currRow = CheckSpace.incrementOne(currRow);
                    currCol = CheckSpace.incrementTwo(currCol);

                    break;
                case 3:
                    // Right-Down
                    currRow = CheckSpace.decrementOne(currRow);
                    currCol = CheckSpace.incrementTwo(currCol);
                    break;
                case 4:
                    // Down-Right
                    currRow = CheckSpace.incrementOne(currRow);
                    currCol = CheckSpace.decrementTwo(currCol);
                    break;
                case 5:
                    // Down-Left
                    currRow = CheckSpace.decrementOne(currRow);
                    currCol = CheckSpace.decrementTwo(currCol);
                    break;
                case 6:
                    // Left-Up
                    currRow = CheckSpace.decrementTwo(currRow);
                    currCol = CheckSpace.incrementOne(currCol);
                    break;
                case 7:
                    // Left-Down
                    currRow = CheckSpace.decrementTwo(currRow);
                    currCol = CheckSpace.decrementOne(currCol);
                    break;
            }
            processMove(board, position, currRow, currCol, validMoves);
        }

        return validMoves;
    }
}