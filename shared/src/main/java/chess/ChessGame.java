package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

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
        this.currTeam = TeamColor.BLACK;
        this.wKingPosition = new ChessPosition(1, 5);
        this.bKingPosition = new ChessPosition(1, 5);

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
        Collection<ChessMove> possibleMoves = this.board.getPiece(startPosition).pieceMoves(this.board, startPosition);
        Collection<ChessMove> validMoves = new HashSet<ChessMove>();

        for (ChessMove move : possibleMoves) {

            if (isKingExposed()) {
                continue;
            }
            validMoves.add(move);
        }

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
            if (isKingExposed()) {
                return true;
            }
        }
        if (teamColor == TeamColor.BLACK) {
            if (isKingExposed()) {
                return true;
            }
        }
        return false;
    }

    private boolean isKingExposed() {

        // Want to see if White King is in Danger, check if black pieces can get king
        if (currTeam == TeamColor.WHITE) {
            for (int i = 1; i < 8; i++) {
                for (int j = 1; j < 8; j++) {
                    if (this.board.getPiece(new ChessPosition(i, j)).getTeamColor().equals(TeamColor.WHITE) || this.board.getPiece(new ChessPosition(i, j)) == null) {
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
        if (currTeam == TeamColor.BLACK) {
            for (int i = 1; i < 8; i++) {
                for (int j = 1; j < 8; j++) {

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
