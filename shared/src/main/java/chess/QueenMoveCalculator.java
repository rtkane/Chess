package chess;

import java.util.ArrayList;
import java.util.Collection;

import static chess.CheckSpace.processMove;

public class QueenMoveCalculator {
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
                    while (currRow < 8) {
                        currRow = CheckSpace.incrementOne(currRow);
                        if (!processMove(board, position, currRow, currCol, validMoves)) break;
                    }
                    break;
                case 1:
                    // Up-Right
                    while (currRow < 8 && currCol < 8) {
                        currRow = CheckSpace.incrementOne(currRow);
                        currCol = CheckSpace.incrementOne(currCol);
                        if (!processMove(board, position, currRow, currCol, validMoves)) break;
                    }
                    break;
                case 2:
                    // Right
                    while (currCol < 8) {
                        currCol = CheckSpace.incrementOne(currCol);
                        if (!processMove(board, position, currRow, currCol, validMoves)) break;
                    }
                    break;
                case 3:
                    // Down-Right
                    while (currRow > 1 && currCol < 8) {
                        currRow = CheckSpace.decrementOne(currRow);
                        currCol = CheckSpace.incrementOne(currCol);
                        if (!processMove(board, position, currRow, currCol, validMoves)) break;
                    }
                    break;
                case 4:
                    // Down
                    while (currRow > 1) {
                        currRow = CheckSpace.decrementOne(currRow);
                        if (!processMove(board, position, currRow, currCol, validMoves)) break;
                    }
                    break;
                case 5:
                    // Down-Left
                    while (currRow > 1 && currCol > 1) {
                        currRow = CheckSpace.decrementOne(currRow);
                        currCol = CheckSpace.decrementOne(currCol);
                        if (!processMove(board, position, currRow, currCol, validMoves)) break;
                    }
                    break;
                case 6:
                    // Left
                    while (currCol > 1) {
                        currCol = CheckSpace.decrementOne(currCol);
                        if (!processMove(board, position, currRow, currCol, validMoves)) break;
                    }
                    break;
                case 7:
                    // Up-Left
                    while (currRow < 8 && currCol > 1) {
                        currRow = CheckSpace.incrementOne(currRow);
                        currCol = CheckSpace.decrementOne(currCol);
                        if (!processMove(board, position, currRow, currCol, validMoves)) break;
                    }
                    break;
            }
        }

        return validMoves;
    }
}
