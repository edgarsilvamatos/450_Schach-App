package com.schachapp.chess;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ChessGameSetupTest {

    @Test
    public void boardIs10x10AndInitialPiecesPlaced() {
        ChessGame game = ChessGame.newGame();
        Board board = game.getBoard();

        // Lover positions
        assertEquals(PieceType.LOVER, board.getPiece(Position.fromAlgebraic("A1")).getType());
        assertEquals(PieceType.LOVER, board.getPiece(Position.fromAlgebraic("J10")).getType());

        // Kings
        assertEquals(PieceType.KING, board.getPiece(Position.fromAlgebraic("F1")).getType());
        assertEquals(PieceType.KING, board.getPiece(Position.fromAlgebraic("E10")).getType());

        // Pawns on second and ninth ranks
        for (char file = 'A'; file <= 'J'; file++) {
            assertEquals(PieceType.PAWN, board.getPiece(Position.fromAlgebraic(file + "2")).getType());
            assertEquals(PieceType.PAWN, board.getPiece(Position.fromAlgebraic(file + "9")).getType());
        }

        // Empty middle squares
        assertNull(board.getPiece(Position.fromAlgebraic("E5")));
        assertNull(board.getPiece(Position.fromAlgebraic("F6")));

        // Active color is white at start
        assertEquals(Color.WHITE, game.getActiveColor());
    }
}

