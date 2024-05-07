package chess;

import java.util.ArrayList;
import java.util.Collection;

public class RookMoveCalculator {
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position){

        Collection<ChessMove> validMoves = new ArrayList<>();

        int currRow = position.getRow();
        int currCol = position.getColumn();

        while(currCol < 8){
            currCol += 1;
            ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
            validMoves.add(move);
        }

        currRow = position.getRow();
        currCol = position.getColumn();
        while(currCol > 1){
            currCol -= 1;
            ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
            validMoves.add(move);
        }

        currRow = position.getRow();
        currCol = position.getColumn();
        while(currRow > 1){
            currRow -= 1;
            ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
            validMoves.add(move);
        }

        currRow = position.getRow();
        currCol = position.getColumn();
        while(currRow < 8){
            currRow += 1;
            ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
            validMoves.add(move);
        }


        return validMoves;
    }
}
