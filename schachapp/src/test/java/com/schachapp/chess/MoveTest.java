package com.schachapp.chess;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MoveTest {

    @Test
    public void exposesFromAndToAndStringRepresentation() {
        Position from = Position.fromAlgebraic("A1");
        Position to = Position.fromAlgebraic("B3");
        Move move = new Move(from, to);

        assertEquals(from, move.getFrom());
        assertEquals(to, move.getTo());
        assertEquals("A1-B3", move.toString());
    }
}

