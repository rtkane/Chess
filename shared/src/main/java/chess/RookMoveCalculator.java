package chess;

import java.util.ArrayList;
import java.util.Collection;

import static chess.CheckSpace.processMove;

public class RookMoveCalculator {
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> validMoves = new ArrayList<>();

        int currRow;
        int currCol;

        // Directions: Up, Right, Down, Left
        for (int i = 0; i < 4; i++) {
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
                    // Right
                    while (currCol < 8) {
                        currCol = CheckSpace.incrementOne(currCol);
                        if (!processMove(board, position, currRow, currCol, validMoves)) break;
                    }
                    break;
                case 2:
                    // Down
                    while (currRow > 1) {
                        currRow = CheckSpace.decrementOne(currRow);
                        if (!processMove(board, position, currRow, currCol, validMoves)) break;
                    }
                    break;

                case 3:
                    // Left
                    while (currCol > 1) {
                        currCol = CheckSpace.decrementOne(currCol);
                        if (!processMove(board, position, currRow, currCol, validMoves)) break;
                    }
                    break;
            }
        }

        return validMoves;
    }
}
