package com.schachapp.chess;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TextBoardRendererTest {

    @Test
    public void rendersBoardWithCoordinatesAndPieces() {
        Board board = new Board();
        board.setPiece(Position.fromAlgebraic("A1"), new Piece(Color.WHITE, PieceType.LOVER));
        board.setPiece(Position.fromAlgebraic("J10"), new Piece(Color.BLACK, PieceType.LOVER));

        String rendered = TextBoardRenderer.render(board);

        assertTrue(rendered.contains("A B C D E F G H I J"));
        assertTrue(rendered.contains("10"));
        assertTrue(rendered.contains("1 "));
        assertTrue(rendered.contains("\u2665"));
        assertTrue(rendered.contains("\u2661"));
    }
}

