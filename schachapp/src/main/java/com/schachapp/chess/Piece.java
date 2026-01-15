package com.schachapp.chess;

import java.util.Objects;

/**
 * Immutable representation of a piece on the board.
 */
public final class Piece {

    private final Color color;
    private final PieceType type;

    public Piece(Color color, PieceType type) {
        this.color = Objects.requireNonNull(color, "color");
        this.type = Objects.requireNonNull(type, "type");
    }

    public Color getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    public boolean isKing() {
        return type == PieceType.KING;
    }

    public boolean isLover() {
        return type == PieceType.LOVER;
    }

    /**
     * Unicode symbol used for text rendering.
     */
    public String getUnicodeSymbol() {
        switch (type) {
            case KING:
                return color == Color.WHITE ? "\u2654" : "\u265A";
            case QUEEN:
                return color == Color.WHITE ? "\u2655" : "\u265B";
            case ROOK:
                return color == Color.WHITE ? "\u2656" : "\u265C";
            case BISHOP:
                return color == Color.WHITE ? "\u2657" : "\u265D";
            case KNIGHT:
                return color == Color.WHITE ? "\u2658" : "\u265E";
            case PAWN:
                return color == Color.WHITE ? "\u2659" : "\u265F";
            case LOVER:
                // not part of standard chess unicode; use heart symbols
                return color == Color.WHITE ? "\u2665" : "\u2661";
            default:
                throw new IllegalStateException("Unknown type " + type);
        }
    }

    @Override
    public String toString() {
        return color + " " + type;
    }
}

