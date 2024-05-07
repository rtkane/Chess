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
                    validMoves.add(move);


                }
                return validMoves;
            } else {
                currRow += 1;
                ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                validMoves.add(move);

                return validMoves;
            }


        }

        if (piece.getTeamColor().equals(ChessGame.TeamColor.BLACK)) {
            if (currRow == 7){
                for (int i = 0; i < 2; i++){
                    currRow -= 1;
                    ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                    validMoves.add(move);
                }
                return validMoves;
            }
            else {
                currRow -= 1;
                ChessMove move = new ChessMove(position, new ChessPosition(currRow, currCol), null);
                validMoves.add(move);
                return validMoves;
            }

        }

        return validMoves;


    }
}
