package chess;

import java.util.ArrayList;
import java.util.Collection;

import static chess.CheckSpace.processMove;

public class PawnMoveCalculator {
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> validMoves = new ArrayList<>();

        int currRow;
        int currCol;

        ChessPiece piece = board.getPiece(position);

        if (piece.getTeamColor().equals(ChessGame.TeamColor.WHITE)) {
            for (int i = 0; i < 4; i++) {
                currRow = position.getRow();
                currCol = position.getColumn();

                switch (i) {
                    case 0:
                        // Two Space move. There won't be an enemy capture.
                        if (currRow == 2) {
                            currRow += 1;
                            if (CheckSpace.inBounds(new ChessPosition(currRow, currCol)) && CheckSpace.isSpaceEmpty(board, new ChessPosition(currRow, currCol))) {
                                currRow += 1;
                                if (CheckSpace.inBounds(new ChessPosition(currRow, currCol)) && CheckSpace.isSpaceEmpty(board, new ChessPosition(currRow, currCol))) {
                                    validMoves.add(new ChessMove(position, new ChessPosition(currRow, currCol), null));
                                }
                            }
                        }
                        break;

                    case 1:
                        // Normal move for pawn.
                        currRow += 1;
                        if (CheckSpace.inBounds(new ChessPosition(currRow, currCol)) && CheckSpace.isSpaceEmpty(board, new ChessPosition(currRow, currCol))) {
                            if (currRow == 8) { // Promotion
                                addPromotionMoves(validMoves, position, currRow, currCol);
                            } else {
                                validMoves.add(new ChessMove(position, new ChessPosition(currRow, currCol), null));
                            }
                        }
                        break;

                    case 2:
                        // Capture up to the Right.
                        currRow += 1;
                        currCol += 1;
                        processMoveForCapture(board, position, currRow, currCol, validMoves, 8);
                        break;

                    case 3:
                        // Capture up to the Left.
                        currRow += 1;
                        currCol -= 1;
                        processMoveForCapture(board, position, currRow, currCol, validMoves, 8);
                        break;
                }
            }
        }

        if (piece.getTeamColor().equals(ChessGame.TeamColor.BLACK)) {
            for (int i = 0; i < 4; i++) {
                currRow = position.getRow();
                currCol = position.getColumn();

                switch (i) {
                    case 0:
                        // Two Space move. There won't be an enemy capture.
                        if (currRow == 7) {
                            currRow -= 1;
                            if (CheckSpace.inBounds(new ChessPosition(currRow, currCol)) && CheckSpace.isSpaceEmpty(board, new ChessPosition(currRow, currCol))) {
                                currRow -= 1;
                                if (CheckSpace.inBounds(new ChessPosition(currRow, currCol)) && CheckSpace.isSpaceEmpty(board, new ChessPosition(currRow, currCol))) {
                                    validMoves.add(new ChessMove(position, new ChessPosition(currRow, currCol), null));
                                }
                            }
                        }
                        break;

                    case 1:
                        // Normal move for pawn.
                        currRow -= 1;
                        if (CheckSpace.inBounds(new ChessPosition(currRow, currCol)) && CheckSpace.isSpaceEmpty(board, new ChessPosition(currRow, currCol))) {
                            if (currRow == 1) { // Promotion
                                addPromotionMoves(validMoves, position, currRow, currCol);
                            } else {
                                validMoves.add(new ChessMove(position, new ChessPosition(currRow, currCol), null));
                            }
                        }
                        break;

                    case 2:
                        // Capture Down to the Right.
                        currRow -= 1;
                        currCol -= 1;
                        processMoveForCapture(board, position, currRow, currCol, validMoves, 1);
                        break;

                    case 3:
                        // Capture Down to the Left.
                        currRow -= 1;
                        currCol += 1;
                        processMoveForCapture(board, position, currRow, currCol, validMoves, 1);
                        break;
                }
            }
        }

        return validMoves;
    }

    private void addPromotionMoves(Collection<ChessMove> validMoves, ChessPosition position, int row, int col) {
        validMoves.add(new ChessMove(position, new ChessPosition(row, col), ChessPiece.PieceType.QUEEN));
        validMoves.add(new ChessMove(position, new ChessPosition(row, col), ChessPiece.PieceType.BISHOP));
        validMoves.add(new ChessMove(position, new ChessPosition(row, col), ChessPiece.PieceType.KNIGHT));
        validMoves.add(new ChessMove(position, new ChessPosition(row, col), ChessPiece.PieceType.ROOK));
    }

    private void processMoveForCapture(ChessBoard board, ChessPosition position, int currRow, int currCol, Collection<ChessMove> validMoves, int promotionRow) {
        ChessPosition newPos = new ChessPosition(currRow, currCol);
        if (CheckSpace.inBounds(newPos) && !CheckSpace.isSpaceEmpty(board, newPos) && CheckSpace.checkTeamColor(board, newPos, position)) {
            if (currRow == promotionRow) {
                addPromotionMoves(validMoves, position, currRow, currCol);
            } else {
                validMoves.add(new ChessMove(position, newPos, null));
            }
        }
    }
}
