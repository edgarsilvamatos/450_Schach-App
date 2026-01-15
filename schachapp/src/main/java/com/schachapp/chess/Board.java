package com.schachapp.chess;

import java.util.Objects;

public class Board {

    public static final int SIZE = 10;

    private final Piece[][] squares = new Piece[SIZE][SIZE];

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

    public Piece getPiece(int file, int rank) {
        if (!isInside(file, rank)) {
            throw new IllegalArgumentException("Out of bounds");
        }
        return squares[file][rank];
    }
}

