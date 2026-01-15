package com.schachapp.chess;

import java.util.Objects;

/**
 * 10x10 board representation.
 */
public class Board {

    public static final int SIZE = 10;

    private final Piece[][] squares = new Piece[SIZE][SIZE]; // [file][rank]

    public Piece getPiece(Position position) {
        Objects.requireNonNull(position, "position");
        return squares[position.getFile()][position.getRank()];
    }

    public void setPiece(Position position, Piece piece) {
        Objects.requireNonNull(position, "position");
        squares[position.getFile()][position.getRank()] = piece;
    }

    public void clear(Position position) {
        setPiece(position, null);
    }

    public static boolean isInside(int file, int rank) {
        return Position.isOnBoard(file, rank);
    }

    /**
     * Convenience for iteration in tests and rendering.
     */
    public Piece getPiece(int file, int rank) {
        if (!isInside(file, rank)) {
            throw new IllegalArgumentException("Out of bounds");
        }
        return squares[file][rank];
    }
}

