package chess;

import java.util.ArrayList;
import java.util.Collection;

public class KingMoveCalculator {

    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position){
        Collection<ChessMove> validMoves = new ArrayList<>();

        int currRow;
        int currCol;

        for(int i = 0; i < 8; i++){
            currRow = position.getRow();
            currCol = position.getColumn();
            if(i == 0){
                currRow += 1;
                ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                validMoves.add(move);
            }
            if(i == 1){
                currRow += 1;
                currCol += 1;
                ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                validMoves.add(move);
            }
            if(i == 2){
                currCol += 1;
                ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                validMoves.add(move);
            }
            if(i == 3){
                currRow -= 1;
                currCol += 1;
                ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                validMoves.add(move);
            }
            if(i == 4){
                currRow -= 1;
                ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                validMoves.add(move);
            }
            if(i == 5){
                currRow -= 1;
                currCol -= 1;
                ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                validMoves.add(move);
            }
            if(i == 6){
                currCol -= 1;
                ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                validMoves.add(move);
            }
            if(i == 7){
                currRow += 1;
                currCol -= 1;
                ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                validMoves.add(move);
            }

        }



        return validMoves;
    }
}
