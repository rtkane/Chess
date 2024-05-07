package chess;

import java.util.ArrayList;
import java.util.Collection;

public class BishopMoveCalculator {

    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> validMoves = new ArrayList<>();



        int currRow = position.getRow();
        int currCol = position.getColumn();

        while (currRow < 8 && currCol < 8){
            currRow += 1;
            currCol += 1;
            ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
            validMoves.add(move);
        }

        currRow = position.getRow();
        currCol = position.getColumn();

        while (currRow > 0 && currCol < 8){
            currRow -= 1;
            currCol += 1;
            ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
            validMoves.add(move);
        }

        currRow = position.getRow();
        currCol = position.getColumn();

        while (currRow > 1 && currCol > 1){
            currRow -= 1;
            currCol -= 1;
            ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
            validMoves.add(move);
        }

        currRow = position.getRow();
        currCol = position.getColumn();

        while (currRow < 8 && currCol > 0){
            currRow += 1;
            currCol -= 1;
            ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
            validMoves.add(move);
        }






        System.out.println(validMoves);
        return validMoves;
    }



}
