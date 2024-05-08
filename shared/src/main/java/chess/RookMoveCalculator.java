package chess;

import java.util.ArrayList;
import java.util.Collection;

public class RookMoveCalculator {
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> validMoves = new ArrayList<>();
        int currRow;
        int currCol;

        for (int i = 0; i < 4; i++){
            currCol = position.getColumn();
            currRow = position.getRow();
            // Move to the right
            if (i == 0) {
                while (currCol < 8) {
                    currCol += 1;
                    ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                    validMoves.add(move);

                }
            }
            // Move to the left
            if (i == 1){
                while (currCol > 1){
                    currCol -= 1;
                    ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null );
                    validMoves.add(move);

                }
            }
            // Move down
            if (i == 2){
                while (currRow > 1){
                    currRow -= 1;
                    ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null );
                    validMoves.add(move);

                }
            }
            if (i == 3){
                while (currRow < 8){
                    currRow += 1;
                    ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null );
                    validMoves.add(move);

                }
            }


        }


        return validMoves;
    }
}
