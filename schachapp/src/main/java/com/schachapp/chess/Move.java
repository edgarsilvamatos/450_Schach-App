package com.schachapp.chess;

import java.util.Objects;
public final class Move {

    private final Position from;
    private final Position to;

    public Move(Position from, Position to) {
        this.from = Objects.requireNonNull(from, "from");
        this.to = Objects.requireNonNull(to, "to");
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    @Override
    public String toString() {
        return from + "-" + to;
    }
}

