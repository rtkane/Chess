package chess;

import java.util.ArrayList;
import java.util.Collection;

public class PawnMoveCalculator {
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> validMoves = new ArrayList<>();


        int currRow = position.getRow();
        int currCol = position.getColumn();

        ChessPiece piece = board.getPiece(position);

        if (piece.getTeamColor().equals(ChessGame.TeamColor.WHITE)) {
            if (currRow == 2) {
                for (int i = 0; i < 2; ++i) {
                    currRow += 1;
                    ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                    if (checkSpace.isSpaceEmpty(board, new ChessPosition(currRow, currCol))){
                        validMoves.add(move);
                    }
                    else {
                        break;
                    }


                }
                return validMoves;
            } else {
                for (int i = 0; i < 3; i++) {
                    if (i == 0) {
                        currRow += 1;
                        ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                        if (checkSpace.isSpaceEmpty(board, new ChessPosition(currRow, currCol))) {
                            validMoves.add(move);
                        }
                        return validMoves;
                    }
                    if (i == 1){
                        currRow += 1;
                        currCol += 1;
                        if (!checkSpace.isSpaceEmpty(board, new ChessPosition(currRow,currCol))){
                            if (checkSpace.checkValidMove(board,new ChessPosition(currRow,currCol), position)){
                                ChessMove move = new ChessMove(position, new ChessPosition(currRow,currCol), null);
                                validMoves.add(move);
                            }
                        }
                    }
                    if (i == 2){
                        currRow += 1;
                        currCol -= 1;
                        if (!checkSpace.isSpaceEmpty(board, new ChessPosition(currRow,currCol))){
                            if (checkSpace.checkValidMove(board,new ChessPosition(currRow,currCol), position)){
                                ChessMove move = new ChessMove(position, new ChessPosition(currRow,currCol), null);
                                validMoves.add(move);
                            }
                        }
                    }
                }
            }


        }

        if (piece.getTeamColor().equals(ChessGame.TeamColor.BLACK)) {
            if (currRow == 7){
                for (int i = 0; i < 2; i++){
                    currRow -= 1;
                    ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                    if (checkSpace.isSpaceEmpty(board, new ChessPosition(currRow,currCol))) {
                        validMoves.add(move);
                    }
                    else {
                        break;
                    }
                }
                return validMoves;
            }
            else {
                currRow -= 1;
                ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                if (checkSpace.isSpaceEmpty(board, new ChessPosition(currRow,currCol))) {
                    validMoves.add(move);
                }
                return validMoves;
            }

        }

        return validMoves;


    }
}
