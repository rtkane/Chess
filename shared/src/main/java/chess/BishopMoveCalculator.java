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

            if (isSpaceEmpty(board, new ChessPosition(currRow, currCol)) == true){
                validMoves.add(move);
            }
            else{
                if (checkValidMove(board, new ChessPosition(currRow, currCol), position) == false){
                    break;
                }
                else {
                    validMoves.add(move);
                    break;
                }
            }

        }

        currRow = position.getRow();
        currCol = position.getColumn();

        while (currRow > 1 && currCol < 8){
            currRow -= 1;
            currCol += 1;
            ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
            if (isSpaceEmpty(board, new ChessPosition(currRow, currCol)) == true){
                validMoves.add(move);
            }
            else{
                if (checkValidMove(board, new ChessPosition(currRow, currCol), position) == false){
                    break;
                }
                else {
                    validMoves.add(move);
                    break;
                }
            }
        }

        currRow = position.getRow();
        currCol = position.getColumn();

        while (currRow > 1 && currCol > 1){
            currRow -= 1;
            currCol -= 1;
            ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
            if (isSpaceEmpty(board, new ChessPosition(currRow, currCol)) == true){
                validMoves.add(move);
            }
            else{
                if (checkValidMove(board, new ChessPosition(currRow, currCol), position) == false){
                    break;
                }
                else {
                    validMoves.add(move);
                    break;
                }
            }
        }

        currRow = position.getRow();
        currCol = position.getColumn();

        while (currRow < 8 && currCol > 1){
            currRow += 1;
            currCol -= 1;
            ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
            if (isSpaceEmpty(board, new ChessPosition(currRow, currCol)) == true){
                validMoves.add(move);
            }
            else{
                if (checkValidMove(board, new ChessPosition(currRow, currCol), position) == false){
                    break;
                }
                else {
                    validMoves.add(move);
                    break;
                }
            }
        }






        System.out.println(validMoves);
        return validMoves;
    }


    private Boolean checkValidMove(ChessBoard board, ChessPosition newPosition, ChessPosition position){
        if (board.getPiece(newPosition).getTeamColor().equals(ChessGame.TeamColor.WHITE) == board.getPiece(position).getTeamColor().equals(ChessGame.TeamColor.WHITE)){
            return false;
        }
        if (board.getPiece(newPosition).getTeamColor().equals(ChessGame.TeamColor.BLACK) == board.getPiece(position).getTeamColor().equals(ChessGame.TeamColor.BLACK)){
            return false;
        }


        return true;
    }

    private Boolean isSpaceEmpty(ChessBoard board, ChessPosition newPosition){
        if (newPosition.getRow() == 8 || newPosition.getColumn() == 8){
            return true;
        }
        if (board.getPiece(newPosition) == null){
            return true;
        }
        return false;

    }



}
