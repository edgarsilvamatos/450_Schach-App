package com.schachapp.chess;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MoveParserTest {

    @Test
    public void parsesDashSeparatedCoordinates() {
        Move move = MoveParser.parse("A2-A4");
        assertEquals("A2", move.getFrom().toAlgebraic());
        assertEquals("A4", move.getTo().toAlgebraic());
    }

    @Test
    public void parsesCompactCoordinates() {
        Move move = MoveParser.parse("b1c3");
        assertEquals("B1", move.getFrom().toAlgebraic());
        assertEquals("C3", move.getTo().toAlgebraic());
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsEmptyInput() {
        MoveParser.parse("   ");
    }
}

