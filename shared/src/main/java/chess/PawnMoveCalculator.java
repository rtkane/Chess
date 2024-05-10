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
            for (int i = 0; i < 4; i++){
                currRow = position.getRow();
                currCol = position.getColumn();

                // Two Space move. There won't be an enemy capture.
                if (i == 0){
                    if ( currRow == 2){
                        currRow += 1;
                        if (inBounds(new ChessPosition(currRow,currCol))){
                            if (isSpaceEmpty(board, new ChessPosition(currRow,currCol))){
                                currRow += 1;
                                if (inBounds(new ChessPosition(currRow, currCol))){
                                    if (isSpaceEmpty(board, new ChessPosition(currRow, currCol))){
                                        ChessMove moves = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                                        validMoves.add(moves);
                                    }
                                }
                            }
                        }

                    }
                }

                // Normal move for pawn.
                if (i == 1){
                    currRow += 1;
                    if (inBounds(new ChessPosition(currRow, currCol))){
                        if (isSpaceEmpty(board, new ChessPosition(currRow,currCol))){

                            // Promotion
                            if (currRow == 8){

                                validMoves.add(new ChessMove(position, new ChessPosition(currRow,currCol), ChessPiece.PieceType.QUEEN));
                                validMoves.add(new ChessMove(position, new ChessPosition(currRow,currCol), ChessPiece.PieceType.BISHOP));
                                validMoves.add(new ChessMove(position, new ChessPosition(currRow,currCol), ChessPiece.PieceType.KNIGHT));
                                validMoves.add(new ChessMove(position, new ChessPosition(currRow,currCol), ChessPiece.PieceType.ROOK));
                            }
                            else {
                                ChessMove moves = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                                validMoves.add(moves);
                            }
                        }
                    }

                }

                // Capture up to the Right.
                if (i == 2){
                    currRow += 1;
                    currCol += 1;
                    if (inBounds(new ChessPosition(currRow,currCol))){
                        if (!isSpaceEmpty(board, new ChessPosition(currRow,currCol))){
                            if (checkTeamColor(board, new ChessPosition(currRow,currCol), position)){

                                // Capture into a promotion
                                if (currRow == 8){

                                    validMoves.add(new ChessMove(position, new ChessPosition(currRow,currCol), ChessPiece.PieceType.QUEEN));
                                    validMoves.add(new ChessMove(position, new ChessPosition(currRow,currCol), ChessPiece.PieceType.BISHOP));
                                    validMoves.add(new ChessMove(position, new ChessPosition(currRow,currCol), ChessPiece.PieceType.KNIGHT));
                                    validMoves.add(new ChessMove(position, new ChessPosition(currRow,currCol), ChessPiece.PieceType.ROOK));
                                }
                                else {
                                    ChessMove moves = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                                    validMoves.add(moves);
                                }
                            }
                        }
                    }

                }
                // Capture up to the Left.
                if (i == 3){
                    currRow += 1;
                    currCol -= 1;
                    if (inBounds(new ChessPosition(currRow,currCol))){
                        if (!isSpaceEmpty(board, new ChessPosition(currRow,currCol))){
                            if (checkTeamColor(board, new ChessPosition(currRow,currCol), position)){

                                // Capture into a promotion
                                if (currRow == 8){

                                    validMoves.add(new ChessMove(position, new ChessPosition(currRow,currCol), ChessPiece.PieceType.QUEEN));
                                    validMoves.add(new ChessMove(position, new ChessPosition(currRow,currCol), ChessPiece.PieceType.BISHOP));
                                    validMoves.add(new ChessMove(position, new ChessPosition(currRow,currCol), ChessPiece.PieceType.KNIGHT));
                                    validMoves.add(new ChessMove(position, new ChessPosition(currRow,currCol), ChessPiece.PieceType.ROOK));
                                }
                                else {
                                    ChessMove moves = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                                    validMoves.add(moves);
                                }
                            }
                        }
                    }

                }






            }

            return validMoves;
        }

        if (piece.getTeamColor().equals(ChessGame.TeamColor.BLACK)) {
            for (int i = 0; i < 4; i++){
                currRow = position.getRow();
                currCol = position.getColumn();

                // Two Space move. There won't be an enemy capture.
                if (i == 0){
                    if ( currRow == 7){
                        currRow -= 1;
                        if (inBounds(new ChessPosition(currRow,currCol))){
                            if (isSpaceEmpty(board, new ChessPosition(currRow,currCol))){
                                currRow -= 1;
                                if (inBounds(new ChessPosition(currRow, currCol))){
                                    if (isSpaceEmpty(board, new ChessPosition(currRow, currCol))){
                                        ChessMove moves = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                                        validMoves.add(moves);
                                    }
                                }
                            }
                        }

                    }
                }

                // Normal move for pawn.
                if (i == 1){
                    currRow -= 1;
                    if (inBounds(new ChessPosition(currRow, currCol))){
                        if (isSpaceEmpty(board, new ChessPosition(currRow,currCol))){

                            // Promotion
                            if (currRow == 1){

                                validMoves.add(new ChessMove(position, new ChessPosition(currRow,currCol), ChessPiece.PieceType.QUEEN));
                                validMoves.add(new ChessMove(position, new ChessPosition(currRow,currCol), ChessPiece.PieceType.BISHOP));
                                validMoves.add(new ChessMove(position, new ChessPosition(currRow,currCol), ChessPiece.PieceType.KNIGHT));
                                validMoves.add(new ChessMove(position, new ChessPosition(currRow,currCol), ChessPiece.PieceType.ROOK));
                            }
                            else {
                                ChessMove moves = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                                validMoves.add(moves);
                            }
                        }
                    }

                }

                // Capture Down to the Right.
                if (i == 2){
                    currRow -= 1;
                    currCol -= 1;
                    if (inBounds(new ChessPosition(currRow,currCol))){
                        if (!isSpaceEmpty(board, new ChessPosition(currRow,currCol))){
                            if (checkTeamColor(board, new ChessPosition(currRow,currCol), position)){

                                // Capture into a promotion
                                if (currRow == 1){

                                    validMoves.add(new ChessMove(position, new ChessPosition(currRow,currCol), ChessPiece.PieceType.QUEEN));
                                    validMoves.add(new ChessMove(position, new ChessPosition(currRow,currCol), ChessPiece.PieceType.BISHOP));
                                    validMoves.add(new ChessMove(position, new ChessPosition(currRow,currCol), ChessPiece.PieceType.KNIGHT));
                                    validMoves.add(new ChessMove(position, new ChessPosition(currRow,currCol), ChessPiece.PieceType.ROOK));
                                }
                                else {
                                    ChessMove moves = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                                    validMoves.add(moves);
                                }
                            }
                        }
                    }

                }
                // Capture Down to the Left.
                if (i == 3){
                    currRow -= 1;
                    currCol += 1;
                    if (inBounds(new ChessPosition(currRow,currCol))){
                        if (!isSpaceEmpty(board, new ChessPosition(currRow,currCol))){
                            if (checkTeamColor(board, new ChessPosition(currRow,currCol), position)){

                                // Capture into a promotion
                                if (currRow == 1){

                                    validMoves.add(new ChessMove(position, new ChessPosition(currRow,currCol), ChessPiece.PieceType.QUEEN));
                                    validMoves.add(new ChessMove(position, new ChessPosition(currRow,currCol), ChessPiece.PieceType.BISHOP));
                                    validMoves.add(new ChessMove(position, new ChessPosition(currRow,currCol), ChessPiece.PieceType.KNIGHT));
                                    validMoves.add(new ChessMove(position, new ChessPosition(currRow,currCol), ChessPiece.PieceType.ROOK));
                                }
                                else {
                                    ChessMove moves = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                                    validMoves.add(moves);
                                }
                            }
                        }
                    }

                }






            }

            return validMoves;
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
