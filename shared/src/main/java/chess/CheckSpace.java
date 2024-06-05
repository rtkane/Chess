package chess;

import java.util.Collection;

public class CheckSpace {

    public static int incrementOne(int i) {
        i = i + 1;
        return i;
    }

    public static int incrementTwo(int i) {
        i = i + 2;
        return i;
    }

    public static int decrementOne(int i) {
        i = i - 1;
        return i;
    }

    public static int decrementTwo(int i) {
        i = i - 2;
        return i;
    }


    public static Boolean inBounds(ChessPosition position) {
        if (position.getRow() > 8) {
            return false;
        }
        if (position.getColumn() > 8) {
            return false;
        }
        if (position.getRow() < 1) {
            return false;
        }
        if (position.getColumn() < 1) {
            return false;
        }
        return true;
    }

    public static Boolean isSpaceEmpty(ChessBoard board, ChessPosition position) {

        return board.getPiece(position) == null;
    }

    public static Boolean checkTeamColor(ChessBoard board, ChessPosition newPosition, ChessPosition position) {
        if (board.getPiece(newPosition).getTeamColor().equals(ChessGame.TeamColor.WHITE) == board.getPiece(position).getTeamColor().equals(ChessGame.TeamColor.WHITE)) {
            return false;
        }
        if (board.getPiece(newPosition).getTeamColor().equals(ChessGame.TeamColor.BLACK) == board.getPiece(position).getTeamColor().equals(ChessGame.TeamColor.BLACK)) {
            return false;
        }


        return true;
    }


    public static boolean processMove(ChessBoard board, ChessPosition myPosition, int currRow, int currCol, Collection<ChessMove> validMoves) {
        ChessPosition newPos = new ChessPosition(currRow, currCol);
        if (CheckSpace.inBounds(newPos)) {
            if (CheckSpace.isSpaceEmpty(board, newPos)) {
                validMoves.add(new ChessMove(myPosition, newPos, null));
            } else {
                if (CheckSpace.checkTeamColor(board, newPos, myPosition)) {
                    validMoves.add(new ChessMove(myPosition, newPos, null));
                }
                return false;
            }
        }
        return true;
    }


}
