package com.schachapp.chess;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PieceTest {

    @Test
    public void kingAndLoverFlagsWork() {
        Piece king = new Piece(Color.WHITE, PieceType.KING);
        Piece lover = new Piece(Color.BLACK, PieceType.LOVER);

        assertTrue(king.isKing());
        assertFalse(king.isLover());

        assertTrue(lover.isLover());
        assertFalse(lover.isKing());
    }

    @Test
    public void unicodeSymbolsMatchPieceAndColor() {
        assertEquals("\u2654", new Piece(Color.WHITE, PieceType.KING).getUnicodeSymbol());
        assertEquals("\u265A", new Piece(Color.BLACK, PieceType.KING).getUnicodeSymbol());
        assertEquals("\u2665", new Piece(Color.WHITE, PieceType.LOVER).getUnicodeSymbol());
        assertEquals("\u2661", new Piece(Color.BLACK, PieceType.LOVER).getUnicodeSymbol());
    }

    @Test
    public void toStringContainsColorAndType() {
        Piece piece = new Piece(Color.WHITE, PieceType.QUEEN);
        String s = piece.toString();
        assertTrue(s.contains("WHITE"));
        assertTrue(s.contains("QUEEN"));
    }
}

