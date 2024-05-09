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
            if (i == 1){
                while (currRow <= 7 && currCol <= 7){
                    currRow +=1;
                    currCol +=1;
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
            if (i == 2){
                while (currCol <= 7){
                    currCol +=1;
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
                while (currRow > 1 && currCol <= 7){
                    currRow -=1;
                    currCol +=1;
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
            if (i == 4){
                while (currRow > 1){
                    currRow -=1;
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
            if (i == 5){
                while (currRow > 1 && currCol > 1){
                    currRow -=1;
                    currCol -=1;
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
            if (i == 6){
                while (currCol > 1){
                    currCol -=1;
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
            if (i == 7){
                while (currRow <= 7 && currCol > 1){
                    currRow +=1;
                    currCol -=1;
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
