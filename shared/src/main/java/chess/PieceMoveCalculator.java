package chess;

import java.util.ArrayList;
import java.util.Collection;

public class PieceMoveCalculator {

    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position){

        Collection<ChessMove> moves = new ArrayList<>();
        ChessPiece.PieceType piece = board.getPiece(position).getPieceType();
        switch (piece){
            case BISHOP:
                BishopMoveCalculator bishopMoveCalculator = new BishopMoveCalculator();
                moves.addAll(bishopMoveCalculator.pieceMoves(board, position));
        }
        return moves;

    }

}
