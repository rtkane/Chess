package ui;

public class Observe {

    public static void print() {
        printChessboardWhiteDown();
        printChessboardBlackDown();
    }

    private static final String BLACK_ROOK = EscapeSequences.SET_TEXT_COLOR_BLUE + EscapeSequences.BLACK_ROOK + EscapeSequences.RESET_TEXT_COLOR;
    private static final String BLACK_KNIGHT = EscapeSequences.SET_TEXT_COLOR_BLUE + EscapeSequences.BLACK_ROOK + EscapeSequences.RESET_TEXT_COLOR;
    private static final String BLACK_BISHOP = EscapeSequences.SET_TEXT_COLOR_BLUE + EscapeSequences.BLACK_ROOK + EscapeSequences.RESET_TEXT_COLOR;
    private static final String BLACK_KING = EscapeSequences.SET_TEXT_COLOR_BLUE + EscapeSequences.BLACK_ROOK + EscapeSequences.RESET_TEXT_COLOR;
    private static final String BLACK_QUEEN = EscapeSequences.SET_TEXT_COLOR_BLUE + EscapeSequences.BLACK_ROOK + EscapeSequences.RESET_TEXT_COLOR;
    private static final String BLACK_PAWN = EscapeSequences.SET_TEXT_COLOR_BLUE + EscapeSequences.BLACK_ROOK + EscapeSequences.RESET_TEXT_COLOR;
    private static final String WHITE_PAWN = EscapeSequences.SET_TEXT_COLOR_RED + EscapeSequences.WHITE_PAWN + EscapeSequences.RESET_TEXT_COLOR;
    private static final String WHITE_ROOK = EscapeSequences.SET_TEXT_COLOR_RED + EscapeSequences.WHITE_PAWN + EscapeSequences.RESET_TEXT_COLOR;
    private static final String WHITE_KNIGHT = EscapeSequences.SET_TEXT_COLOR_RED + EscapeSequences.WHITE_PAWN + EscapeSequences.RESET_TEXT_COLOR;
    private static final String WHITE_QUEEN = EscapeSequences.SET_TEXT_COLOR_RED + EscapeSequences.WHITE_PAWN + EscapeSequences.RESET_TEXT_COLOR;
    private static final String WHITE_KING = EscapeSequences.SET_TEXT_COLOR_RED + EscapeSequences.WHITE_PAWN + EscapeSequences.RESET_TEXT_COLOR;
    private static final String WHITE_BISHOP = EscapeSequences.SET_TEXT_COLOR_RED + EscapeSequences.WHITE_PAWN + EscapeSequences.RESET_TEXT_COLOR;

    private static final  String EMPTY = EscapeSequences.EMPTY;
    private static String whiteHeader = "   a  b  c  d  e  f  g  h\n";
    private static String blackHeader = "   h  g  f  e  d  c  b  a\n";






    public static void printChessboardWhiteDown() {
        StringBuilder chessboard = new StringBuilder();

        chessboard.append(EscapeSequences.ERASE_SCREEN);


        String[][] board = {
                {BLACK_ROOK, BLACK_KNIGHT, BLACK_BISHOP, BLACK_QUEEN, BLACK_KING, BLACK_BISHOP, BLACK_KNIGHT, BLACK_ROOK},
                {BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                {WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN},
                {WHITE_ROOK, WHITE_KNIGHT, WHITE_BISHOP, WHITE_QUEEN, WHITE_KING, WHITE_BISHOP, WHITE_KNIGHT, WHITE_ROOK}
        };

        chessboard.append(whiteHeader);

        for (int row = 0; row < 8; row++) {
            chessboard.append(8 - row).append(" ");
            for (int col = 0; col < 8; col++) {
                if ((row + col) % 2 == 0) {
                    chessboard.append(EscapeSequences.SET_BG_COLOR_WHITE);
                } else {
                    chessboard.append(EscapeSequences.SET_BG_COLOR_DARK_GREY);
                }
                chessboard.append(board[row][col]);
                chessboard.append(EscapeSequences.RESET_BG_COLOR);
            }
            chessboard.append(" ").append(8 - row).append("\n"); // Move to the next line after each row
        }

        chessboard.append(whiteHeader);

        System.out.print(chessboard);
    }

    public static void printChessboardBlackDown() {
        StringBuilder chessboard = new StringBuilder();

        chessboard.append(EscapeSequences.ERASE_SCREEN);

        String[][] board = {
                {WHITE_ROOK, WHITE_KNIGHT, WHITE_BISHOP, WHITE_QUEEN, WHITE_KING, WHITE_BISHOP, WHITE_KNIGHT, WHITE_ROOK},
                {WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                {BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN},
                {BLACK_ROOK, BLACK_KNIGHT, BLACK_BISHOP,BLACK_QUEEN, BLACK_KING, BLACK_BISHOP, BLACK_KNIGHT, BLACK_ROOK}
        };

        chessboard.append(blackHeader);

        for (int row = 0; row < 8; row++) {
            chessboard.append(row + 1).append(" ");
            for (int col = 0; col < 8; col++) {
                if ((row + col) % 2 == 0) {
                    chessboard.append(EscapeSequences.SET_BG_COLOR_WHITE);
                } else {
                    chessboard.append(EscapeSequences.SET_BG_COLOR_DARK_GREY);
                }
                chessboard.append(board[row][7 - col]);
                chessboard.append(EscapeSequences.RESET_BG_COLOR);
            }
            chessboard.append(" ").append(row + 1).append("\n");
        }

        chessboard.append(blackHeader);

        System.out.print(chessboard);
    }
}