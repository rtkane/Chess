package ui;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static ui.EscapeSequences.*;

public class Example {

    private static final int BOARD_SIZE_IN_SQUARES = 8;
    private static final int SQUARE_SIZE_IN_CHARS = 4;
    private static final int LINE_WIDTH_IN_CHARS = 1;

    private static final String EMPTY = "    ";

    private static final String[][] INITIAL_BOARD = {
            {BLACK_ROOK, BLACK_KNIGHT, BLACK_BISHOP, BLACK_QUEEN, BLACK_KING, BLACK_BISHOP, BLACK_KNIGHT, BLACK_ROOK},
            {BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN},
            {WHITE_ROOK, WHITE_KNIGHT, WHITE_BISHOP, WHITE_QUEEN, WHITE_KING, WHITE_BISHOP, WHITE_KNIGHT, WHITE_ROOK}
    };

    public static void main(String[] args) {
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

        out.print(ERASE_SCREEN);

        drawChessBoard(out, true);  // White at the bottom
        out.println();
        drawChessBoard(out, false); // Black at the bottom

        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_WHITE);
    }

    private static void drawChessBoard(PrintStream out, boolean whiteAtBottom) {
        drawColumnHeaders(out);

        for (int row = 0; row < BOARD_SIZE_IN_SQUARES; ++row) {
            drawRow(out, whiteAtBottom ? row : BOARD_SIZE_IN_SQUARES - 1 - row);
            drawRowDivider(out);
        }

        drawColumnHeaders(out);
    }

    private static void drawColumnHeaders(PrintStream out) {
        setBlack(out);
        out.print("   ");  // Initial padding for row numbers

        char[] headers = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        for (char header : headers) {
            out.print("  " + header + " ");
        }
        out.println();
    }

    private static void drawRow(PrintStream out, int row) {
        drawRowNumber(out, row);

        for (int squareRow = 0; squareRow < SQUARE_SIZE_IN_CHARS; ++squareRow) {
            for (int col = 0; col < BOARD_SIZE_IN_SQUARES; ++col) {
                if ((row + col) % 2 == 0) {
                    setWhite(out);
                } else {
                    setBlack(out);
                }

                if (squareRow == SQUARE_SIZE_IN_CHARS / 2) {
                    int prefixLength = (SQUARE_SIZE_IN_CHARS - 1) / 2;
                    int suffixLength = SQUARE_SIZE_IN_CHARS - prefixLength - 1;

                    out.print(EMPTY.substring(0, prefixLength));
                    printPiece(out, INITIAL_BOARD[row][col]);
                    out.print(EMPTY.substring(0, suffixLength));
                } else {
                    out.print(EMPTY);
                }

                setBlack(out);
            }

            out.print(" ");
            if (squareRow == SQUARE_SIZE_IN_CHARS / 2) {
                drawRowNumber(out, row);
            }
            out.println();
        }
    }

    private static void drawRowNumber(PrintStream out, int row) {
        setBlack(out);
        out.print(" " + (BOARD_SIZE_IN_SQUARES - row) + " ");
    }

    private static void drawRowDivider(PrintStream out) {
        for (int i = 0; i < BOARD_SIZE_IN_SQUARES; ++i) {
            out.print(EMPTY);
        }
        out.println();
    }

    private static void setWhite(PrintStream out) {
        out.print(SET_BG_COLOR_LIGHT_GREY);
        out.print(SET_TEXT_COLOR_BLACK);
    }

    private static void setBlack(PrintStream out) {
        out.print(SET_BG_COLOR_DARK_GREY);
        out.print(SET_TEXT_COLOR_WHITE);
    }

    private static void printPiece(PrintStream out, String piece) {
        out.print(piece);
    }
}
