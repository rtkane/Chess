package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.lang.Math;


/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    private ChessBoard board;
    private TeamColor currTeam;
    private ChessPosition wKingPosition;
    private ChessPosition bKingPosition;


    public ChessGame() {
        this.board = new ChessBoard();
        this.currTeam = TeamColor.WHITE;
        this.wKingPosition = new ChessPosition(1, 5);
        this.bKingPosition = new ChessPosition(7, 5);

    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return this.currTeam;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        this.currTeam = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        ChessPiece currPiece = new ChessPiece(this.board.getPiece(startPosition).getTeamColor(), this.board.getPiece(startPosition).getPieceType());


        Collection<ChessMove> possibleMoves = this.board.getPiece(startPosition).pieceMoves(this.board, startPosition);
        Collection<ChessMove> validMoves = new HashSet<ChessMove>();
        wKingPosition = findKingPosition(TeamColor.WHITE);
        bKingPosition = findKingPosition(TeamColor.BLACK);



        for (ChessMove move : possibleMoves) {
            // If it is a capture, add to valid moves
            if (this.board.getPiece(new ChessPosition(move.getEndPosition().getRow(), move.getEndPosition().getColumn())) != null &&
                    this.board.getPiece(new ChessPosition(move.getEndPosition().getRow(), move.getEndPosition().getColumn())).getTeamColor() != currPiece.getTeamColor()) {
                validMoves.add(move);
                continue;
            }

            // if piece is a king check if possible move is adjacent to the other king
            if (currPiece.getPieceType().equals(ChessPiece.PieceType.KING)) {
                if (currPiece.getTeamColor().equals(TeamColor.WHITE)){
                    int rowDiff = Math.abs(move.getEndPosition().getRow() - bKingPosition.getRow());
                    int colDiff = Math.abs(move.getEndPosition().getColumn() - bKingPosition.getColumn());

                    if ((rowDiff <= 1) && (colDiff <= 1)) {
                        continue;
                    }
                }
                if (currPiece.getTeamColor().equals(TeamColor.BLACK)){
                    int rowDiff = Math.abs(move.getEndPosition().getRow() - wKingPosition.getRow());
                    int colDiff = Math.abs(move.getEndPosition().getColumn() - wKingPosition.getColumn());

                    if ((rowDiff <= 1) && (colDiff <= 1)) {
                        continue;
                    }
                }

            }

            // Set up temp piece and check if the move will put king in check
            this.board.removePiece(startPosition);
            ChessPiece tempPiece = new ChessPiece(currPiece.getTeamColor(), currPiece.getPieceType());
            this.board.addPiece(new ChessPosition(move.getEndPosition().getRow(), move.getEndPosition().getColumn()), tempPiece);


            if (tempPiece.getPieceType().equals(ChessPiece.PieceType.KING)){
                if (tempPiece.getTeamColor().equals(TeamColor.WHITE)){
                    ChessPosition currKingPos = wKingPosition;
                    ChessPosition tempKingPos = move.getEndPosition();
                    wKingPosition = tempKingPos;

                    if (isKingExposed(tempPiece.getTeamColor())) {
                        this.board.removePiece(move.getEndPosition());
                        wKingPosition = currKingPos;
                        continue;
                    }
                    this.board.removePiece(move.getEndPosition());
                    wKingPosition = currKingPos;
                    validMoves.add(move);
                }
                if (tempPiece.getTeamColor().equals(TeamColor.BLACK)){
                    ChessPosition currKingPos = bKingPosition;
                    ChessPosition tempKingPos = move.getEndPosition();
                    bKingPosition = tempKingPos;


                    if (isKingExposed(tempPiece.getTeamColor())) {
                        this.board.removePiece(move.getEndPosition());
                        bKingPosition = currKingPos;

                        continue;
                    }
                    this.board.removePiece(move.getEndPosition());
                    bKingPosition = currKingPos;

                    validMoves.add(move);
                }
            }
            else {


                if (isKingExposed(tempPiece.getTeamColor())) {
                    this.board.removePiece(move.getEndPosition());
                    continue;
                }
                this.board.removePiece(move.getEndPosition());
                validMoves.add(move);
            }
        }

        this.board.addPiece(new ChessPosition(startPosition.getRow(), startPosition.getColumn()), currPiece);
        return validMoves;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        if (teamColor == TeamColor.WHITE) {
            if (isKingExposed(TeamColor.WHITE)) {
                return true;
            }
        }
        if (teamColor == TeamColor.BLACK) {
            if (isKingExposed(TeamColor.BLACK)) {
                return true;
            }
        }
        return false;
    }

    private ChessPosition findKingPosition(TeamColor color) {
        if (color == TeamColor.WHITE) {
            for (int i = 1; i <= 8; i++) {
                for (int j = 1; j <= 8; j++) {
                    if (this.board.getPiece(new ChessPosition(i, j)) == null || this.board.getPiece(new ChessPosition(i, j)).getTeamColor().equals(TeamColor.BLACK)) {
                        continue;
                    } else {
                            if (this.board.getPiece(new ChessPosition(i,j)).getPieceType().equals(ChessPiece.PieceType.KING)) {
                                wKingPosition = new ChessPosition(i,j);
                                return wKingPosition;
                        }
                    }
                }
            }
        }
        if (color == TeamColor.BLACK) {
            for (int i = 1; i <= 8; i++) {
                for (int j = 1; j <= 8; j++) {
                    if (this.board.getPiece(new ChessPosition(i, j)) == null || this.board.getPiece(new ChessPosition(i, j)).getTeamColor().equals(TeamColor.WHITE)) {
                        continue;
                    } else {
                        if (this.board.getPiece(new ChessPosition(i,j)).getPieceType().equals(ChessPiece.PieceType.KING)) {
                            bKingPosition = new ChessPosition(i,j);
                            return bKingPosition;
                        }
                    }
                }
            }
        }

        return wKingPosition;
    }

    private boolean isKingExposed(TeamColor color) {

        // Want to see if White King is in Danger, check if black pieces can get king
        if (color == TeamColor.WHITE) {
            for (int i = 1; i <= 8; i++) {
                for (int j = 1; j <= 8; j++) {
                    if (this.board.getPiece(new ChessPosition(i, j)) == null || this.board.getPiece(new ChessPosition(i, j)).getTeamColor().equals(TeamColor.WHITE)) {
                        continue;
                    } else {
                        Collection<ChessMove> moves = this.board.getPiece(new ChessPosition(i, j)).pieceMoves(this.board, new ChessPosition(i, j));
                        for (ChessMove move : moves) {
                            if (wKingPosition.equals(move.getEndPosition())) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        if (color == TeamColor.BLACK) {
            for (int i = 1; i <= 8; i++) {
                for (int j = 1; j <= 8; j++) {

                    if (this.board.getPiece(new ChessPosition(i, j)) == null) {
                        continue;
                    }
                    if (this.board.getPiece(new ChessPosition(i, j)).getPieceType().equals(ChessPiece.PieceType.KING) && this.board.getPiece(new ChessPosition(i, j)).getTeamColor().equals(TeamColor.BLACK)) {
                        bKingPosition = new ChessPosition(i, j);
                    }
                    if (this.board.getPiece(new ChessPosition(i, j)).getTeamColor().equals(TeamColor.BLACK)) {
                        continue;
                    } else {
                        Collection<ChessMove> moves = this.board.getPiece(new ChessPosition(i, j)).pieceMoves(this.board, new ChessPosition(i, j));
                        for (ChessMove move : moves) {
                            if (bKingPosition.equals(move.getEndPosition()) == true) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }


    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return this.board;
    }
}
