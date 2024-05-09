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
                    if (inBounds(new ChessPosition(currRow, currCol))) {
                        if (isSpaceEmpty(board, new ChessPosition(currRow, currCol))) {
                            ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);

                            validMoves.add(move);
                        }
                        else {
                            if (checkTeamColor(board,new ChessPosition(currRow, currCol), position)){
                                ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                                validMoves.add(move);
                                break;
                            }
                            else {
                                break;
                            }

                        }
                    }
                    else {
                        break;
                    }

                }
            }
            // Move to the left
            if (i == 1){
                while (currCol > 1){
                    currCol -= 1;
                    if (inBounds(new ChessPosition(currRow, currCol))) {
                        if (isSpaceEmpty(board, new ChessPosition(currRow, currCol))) {
                            ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);

                            validMoves.add(move);
                        }
                        else {
                            if (checkTeamColor(board,new ChessPosition(currRow, currCol), position)){
                                ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                                validMoves.add(move);
                                break;
                            }
                            else {
                                break;
                            }

                        }
                    }
                    else {
                        break;
                    }

                }
            }
            // Move down
            if (i == 2){
                while (currRow > 1){
                    currRow -= 1;
                    if (inBounds(new ChessPosition(currRow, currCol))) {
                        if (isSpaceEmpty(board, new ChessPosition(currRow, currCol))) {
                            ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);

                            validMoves.add(move);
                        }
                        else {
                            if (checkTeamColor(board,new ChessPosition(currRow, currCol), position)){
                                ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                                validMoves.add(move);
                                break;
                            }
                            else {
                                break;
                            }

                        }
                    }
                    else {
                        break;
                    }

                }
            }
            if (i == 3){
                while (currRow < 8){
                    currRow += 1;
                    if (inBounds(new ChessPosition(currRow, currCol))) {
                        if (isSpaceEmpty(board, new ChessPosition(currRow, currCol))) {
                            ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);

                            validMoves.add(move);
                        }
                        else {
                            if (checkTeamColor(board,new ChessPosition(currRow, currCol), position)){
                                ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                                validMoves.add(move);
                                break;
                            }
                            else {
                                break;
                            }

                        }
                    }
                    else {
                        break;
                    }
                }
            }


        }
        return validMoves;
    }



    private Boolean inBounds(ChessPosition position){
        if (position.getRow() > 8){
            return false;
        }
        if (position.getColumn() > 8){
            return false;
        }
        if (position.getRow() < 1){
            return false;
        }
        if (position.getColumn() < 1){
            return false;
        }
        return true;
    }

    private Boolean isSpaceEmpty(ChessBoard board, ChessPosition position){
        int currRow = position.getRow() - 1;
        int currCol = position.getColumn() - 1;

        if (board.getPiece(position) == null){
            return true;
        }
        return false;
    }

    public Boolean checkTeamColor(ChessBoard board, ChessPosition newPosition, ChessPosition position){
        if (board.getPiece(newPosition).getTeamColor().equals(ChessGame.TeamColor.WHITE) == board.getPiece(position).getTeamColor().equals(ChessGame.TeamColor.WHITE)){
            return false;
        }
        if (board.getPiece(newPosition).getTeamColor().equals(ChessGame.TeamColor.BLACK) == board.getPiece(position).getTeamColor().equals(ChessGame.TeamColor.BLACK)){
            return false;
        }


        return true;
    }
}
