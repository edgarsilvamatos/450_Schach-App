package com.schachapp.chess;

/**
 * Player color.
 */
public enum Color {
    WHITE,
    BLACK;

    public Color opposite() {
        return this == WHITE ? BLACK : WHITE;
    }
}

