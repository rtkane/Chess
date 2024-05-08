package chess;

public class checkSpace {

    public static Boolean checkValidMove(ChessBoard board, ChessPosition newPosition, ChessPosition position){
        if (board.getPiece(newPosition).getTeamColor().equals(ChessGame.TeamColor.WHITE) == board.getPiece(position).getTeamColor().equals(ChessGame.TeamColor.WHITE)){
            return false;
        }
        if (board.getPiece(newPosition).getTeamColor().equals(ChessGame.TeamColor.BLACK) == board.getPiece(position).getTeamColor().equals(ChessGame.TeamColor.BLACK)){
            return false;
        }


        return true;
    }

    public static Boolean isSpaceEmpty(ChessBoard board, ChessPosition newPosition){
        if (newPosition.getRow() ==
                8 || newPosition.getColumn() == 8){
            return true;
        }
        if (board.getPiece(newPosition) == null){
            return true;
        }
        return false;

    }
}
