package chess;

import java.util.ArrayList;
import java.util.Collection;
import static chess.CheckSpace.*;

public class PawnMoveCalculator {
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> validMoves = new ArrayList<>();

        int currRow, currCol;
        ChessPiece piece = board.getPiece(position);

        if (piece.getTeamColor().equals(ChessGame.TeamColor.WHITE)) {
            for (int i = 0; i < 4; i++) {
                currRow = position.getRow();
                currCol = position.getColumn();

                switch (i) {
                    case 0:
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
                        currRow += 1;
                        currCol += 1;
                        processMoveForCapture(board, position, currRow, currCol, validMoves, 8);
                        break;

                    case 3:
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
                        currRow -= 1;
                        currCol -= 1;
                        processMoveForCapture(board, position, currRow, currCol, validMoves, 1);
                        break;
                    case 3:
                        currRow -= 1;
                        currCol += 1;
                        processMoveForCapture(board, position, currRow, currCol, validMoves, 1);
                        break;
                }
            }
        }
        return validMoves;
    }
}
