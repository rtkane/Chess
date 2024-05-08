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
                break;
            case PAWN:
                PawnMoveCalculator pawnMoveCalculator = new PawnMoveCalculator();
                moves.addAll(pawnMoveCalculator.pieceMoves(board, position));
                break;
            case ROOK:
                RookMoveCalculator rookMoveCalculator = new RookMoveCalculator();
                moves.addAll(rookMoveCalculator.pieceMoves(board, position));
                break;
            case KING:
                KingMoveCalculator kingMoveCalculator = new KingMoveCalculator();
                moves.addAll(kingMoveCalculator.pieceMoves(board, position));
                break;
            case KNIGHT:
                KnightsMoveCalculator knightsMoveCalculator = new KnightsMoveCalculator();
                moves.addAll(knightsMoveCalculator.pieceMoves(board,position));
                break;
            case QUEEN:
                QueenMoveCalculator queenMoveCalculator = new QueenMoveCalculator();
                moves.addAll(queenMoveCalculator.pieceMoves(board,position));
                break;

        }
        return moves;

    }

}
