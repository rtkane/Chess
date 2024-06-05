package chess;

import java.util.ArrayList;
import java.util.Collection;

import static chess.CheckSpace.processMove;

public class BishopMoveCalculator {
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> validMoves = new ArrayList<>();

        int currRow;
        int currCol;

        // Directions: Up-Right, Down-Right, Down-Left, Up-Left
        for (int i = 0; i < 4; i++) {
            currRow = myPosition.getRow();
            currCol = myPosition.getColumn();

            switch (i) {
                case 0:
                    // Up-Right
                    while (currRow < 8 && currCol < 8) {
                        currRow = CheckSpace.incrementOne(currRow);
                        currCol = CheckSpace.incrementOne(currCol);
                        if (!processMove(board, myPosition, currRow, currCol, validMoves)) break;
                    }
                    break;
                case 1:
                    // Down-Right
                    while (currRow > 1 && currCol < 8) {
                        currRow = CheckSpace.decrementOne(currRow);
                        currCol = CheckSpace.incrementOne(currCol);
                        if (!processMove(board, myPosition, currRow, currCol, validMoves)) break;
                    }
                    break;
                case 2:
                    // Down-Left
                    while (currRow > 1 && currCol > 1) {
                        currRow = CheckSpace.decrementOne(currRow);
                        currCol = CheckSpace.decrementOne(currCol);
                        if (!processMove(board, myPosition, currRow, currCol, validMoves)) break;
                    }
                    break;
                case 3:
                    // Up-Left
                    while (currRow < 8 && currCol > 1) {
                        currRow = CheckSpace.incrementOne(currRow);
                        currCol = CheckSpace.decrementOne(currCol);
                        if (!processMove(board, myPosition, currRow, currCol, validMoves)) break;
                    }
                    break;
            }
        }

        return validMoves;
    }

}
