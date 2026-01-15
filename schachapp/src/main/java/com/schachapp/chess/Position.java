package com.schachapp.chess;

import java.util.Objects;

/**
 * Board coordinates on a 10x10 board.
 * Internally zero-based (file 0-9, rank 0-9).
 */
public final class Position {

    public static final int BOARD_SIZE = 10;

    private final int file; // 0..9 -> A..J
    private final int rank; // 0..9 -> 1..10

    public Position(int file, int rank) {
        if (file < 0 || file >= BOARD_SIZE || rank < 0 || rank >= BOARD_SIZE) {
            throw new IllegalArgumentException("Position out of bounds: " + file + "," + rank);
        }
        this.file = file;
        this.rank = rank;
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    public static boolean isOnBoard(int file, int rank) {
        return file >= 0 && file < BOARD_SIZE && rank >= 0 && rank < BOARD_SIZE;
    }

    /**
     * Parse algebraic coordinates like "A1".."J10".
     */
    public static Position fromAlgebraic(String notation) {
        Objects.requireNonNull(notation, "notation");
        String trimmed = notation.trim().toUpperCase();
        if (trimmed.length() < 2 || trimmed.length() > 3) {
            throw new IllegalArgumentException("Invalid coordinate: " + notation);
        }
        char fileChar = trimmed.charAt(0);
        if (fileChar < 'A' || fileChar > 'J') {
            throw new IllegalArgumentException("Invalid file: " + notation);
        }
        int file = fileChar - 'A';
        String rankStr = trimmed.substring(1);
        int rankNumeric;
        try {
            rankNumeric = Integer.parseInt(rankStr);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid rank: " + notation, ex);
        }
        if (rankNumeric < 1 || rankNumeric > 10) {
            throw new IllegalArgumentException("Rank out of bounds: " + notation);
        }
        // convert to 0-based; rank 1 is bottom (index 0), rank 10 is top (index 9)
        int rank = rankNumeric - 1;
        return new Position(file, rank);
    }

    public String toAlgebraic() {
        char fileChar = (char) ('A' + file);
        int rankNumeric = rank + 1;
        return "" + fileChar + rankNumeric;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public String toString() {
        return toAlgebraic();
    }
}

