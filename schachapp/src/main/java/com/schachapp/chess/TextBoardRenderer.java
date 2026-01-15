package com.schachapp.chess;

/**
 * Renders the board as a simple text grid with unicode symbols.
 */
public final class TextBoardRenderer {

    private TextBoardRenderer() {
    }

    public static String render(Board board) {
        StringBuilder sb = new StringBuilder();
        // ranks 10..1 (internal 9..0)
        for (int rank = Board.SIZE - 1; rank >= 0; rank--) {
            sb.append(String.format("%2d ", rank + 1));
            for (int file = 0; file < Board.SIZE; file++) {
                Piece piece = board.getPiece(file, rank);
                if (piece == null) {
                    sb.append(". ");
                } else {
                    sb.append(piece.getUnicodeSymbol()).append(' ');
                }
            }
            sb.append('\n');
        }
        sb.append("   ");
        for (int file = 0; file < Board.SIZE; file++) {
            char fileChar = (char) ('A' + file);
            sb.append(fileChar).append(' ');
        }
        sb.append('\n');
        return sb.toString();
    }
}

