package com.schachapp.chess;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckDetectionTest {

    @Test
    public void detectsSimpleCheckOnWhiteKing() {
        ChessGame game = ChessGame.newGame();
        Board board = game.getBoard();

        // Clear board and construct a minimal check position: black rook on F10, white king on F1
        for (int file = 0; file < Board.SIZE; file++) {
            for (int rank = 0; rank < Board.SIZE; rank++) {
                board.clear(new Position(file, rank));
            }
        }

        Position whiteKingPos = Position.fromAlgebraic("F1");
        Position blackRookPos = Position.fromAlgebraic("F10");
        board.setPiece(whiteKingPos, new Piece(Color.WHITE, PieceType.KING));
        board.setPiece(blackRookPos, new Piece(Color.BLACK, PieceType.ROOK));

        assertTrue(game.isKingInCheck(Color.WHITE));
        assertFalse(game.isKingInCheck(Color.BLACK));
    }
}

