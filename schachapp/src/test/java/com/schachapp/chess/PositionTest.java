package com.schachapp.chess;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class PositionTest {

    @Test
    public void parsesAndFormatsAlgebraicCoordinates() {
        Position p = Position.fromAlgebraic("C5");
        assertEquals(2, p.getFile());
        assertEquals(4, p.getRank());
        assertEquals("C5", p.toAlgebraic());
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsInvalidFile() {
        Position.fromAlgebraic("K1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsInvalidRank() {
        Position.fromAlgebraic("A11");
    }

    @Test
    public void equalityAndHashCodeDependOnFileAndRank() {
        Position a1 = Position.fromAlgebraic("A1");
        Position same = new Position(0, 0);
        Position different = Position.fromAlgebraic("B1");

        assertEquals(a1, same);
        assertEquals(a1.hashCode(), same.hashCode());
        assertNotEquals(a1, different);
    }

    @Test
    public void isOnBoardDetectsBounds() {
        assertTrue(Position.isOnBoard(0, 0));
        assertTrue(Position.isOnBoard(9, 9));
        assertFalse(Position.isOnBoard(-1, 0));
        assertFalse(Position.isOnBoard(10, 5));
    }
}

