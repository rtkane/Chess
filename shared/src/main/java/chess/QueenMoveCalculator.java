package chess;

import java.util.ArrayList;
import java.util.Collection;

public class QueenMoveCalculator {
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position){
        Collection<ChessMove> validMoves = new ArrayList<>();
        int currRow;
        int currCol;

        for (int i = 0; i < 8; i++){
            currCol = position.getColumn();
            currRow = position.getRow();

            if (i == 0){
                while (currRow <= 7){
                    currRow +=1;
                    ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                    validMoves.add(move);
                }
            }
            if (i == 1){
                while (currRow <= 7 && currCol <= 7){
                    currRow +=1;
                    currCol +=1;
                    ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                    validMoves.add(move);
                }
            }
            if (i == 2){
                while (currCol <= 7){
                    currCol +=1;
                    ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                    validMoves.add(move);
                }
            }
            if (i == 3){
                while (currRow > 1 && currCol <= 7){
                    currRow -=1;
                    currCol +=1;
                    ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                    validMoves.add(move);
                }
            }
            if (i == 4){
                while (currRow > 1){
                    currRow -=1;
                    ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                    validMoves.add(move);
                }
            }
            if (i == 5){
                while (currRow > 1 && currCol > 1){
                    currRow -=1;
                    currCol -=1;
                    ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                    validMoves.add(move);
                }
            }
            if (i == 6){
                while (currCol > 1){
                    currCol -=1;
                    ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                    validMoves.add(move);
                }
            }
            if (i == 7){
                while (currRow <= 7 && currCol > 1){
                    currRow +=1;
                    currCol -=1;
                    ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                    validMoves.add(move);
                }
            }




        }

        return validMoves;
    }
}
