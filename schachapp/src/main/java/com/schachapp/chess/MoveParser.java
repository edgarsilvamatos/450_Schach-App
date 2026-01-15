package com.schachapp.chess;

/**
 * Simple move parser for coordinate notation like "A2-A4" or "A2 A4".
 */
public final class MoveParser {

    private MoveParser() {
        // utility
    }

    public static Move parse(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input must not be null");
        }
        String trimmed = input.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Empty move");
        }
        String normalized = trimmed.toUpperCase().replaceAll("\\s+", "");
        String[] parts;
        if (normalized.contains("-")) {
            parts = normalized.split("-");
        } else {
            if (normalized.length() < 4) {
                throw new IllegalArgumentException("Move too short: " + input);
            }
            // split in half, supports ranks 1..10
            // we assume from and to each start with a letter
            int secondStart = findSecondCoordinateStart(normalized);
            parts = new String[] { normalized.substring(0, secondStart), normalized.substring(secondStart) };
        }
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid move: " + input);
        }
        Position from = Position.fromAlgebraic(parts[0]);
        Position to = Position.fromAlgebraic(parts[1]);
        return new Move(from, to);
    }

    private static int findSecondCoordinateStart(String s) {
        // find index of second file letter
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'A' && c <= 'J') {
                return i;
            }
        }
        throw new IllegalArgumentException("Cannot split coordinates: " + s);
    }
}

