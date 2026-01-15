package com.schachapp.chess;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChessGameAdditionalTest {

    @Test
    public void capturedPiecesAreTracked() {
        ChessGame game = ChessGame.newGame();
        Board board = game.getBoard();

        for (int file = 0; file < Board.SIZE; file++) {
            for (int rank = 0; rank < Board.SIZE; rank++) {
                board.clear(new Position(file, rank));
            }
        }

        Position white = Position.fromAlgebraic("A2");
        Position black = Position.fromAlgebraic("B3");
        board.setPiece(white, new Piece(Color.WHITE, PieceType.PAWN));
        board.setPiece(black, new Piece(Color.BLACK, PieceType.PAWN));

        assertTrue(game.playMove(new Move(white, black)));

        assertEquals(1, game.getCapturedPieces().size());
        assertEquals(Color.BLACK, game.getCapturedPieces().get(0).getColor());
    }

    @Test
    public void pawnDoubleStepIsRestrictedAndUsesIntermediateCheck() {
        ChessGame game = ChessGame.newGame();
        Board board = game.getBoard();

        Position from = Position.fromAlgebraic("C2");
        assertTrue(game.isLegalMove(new Move(from, Position.fromAlgebraic("C4")), Color.WHITE));
        board.setPiece(Position.fromAlgebraic("C3"), new Piece(Color.WHITE, PieceType.PAWN));
        assertFalse(game.isLegalMove(new Move(from, Position.fromAlgebraic("C4")), Color.WHITE));
    }

    @Test
    public void illegalMoveDoesNotChangeStatusOrTurn() {
        ChessGame game = ChessGame.newGame();
        GameStatus initialStatus = game.getStatus();
        Color initialColor = game.getActiveColor();

        boolean ok = game.playMove(new Move(Position.fromAlgebraic("A2"), Position.fromAlgebraic("A1")));

        assertFalse(ok);
        assertEquals(initialStatus, game.getStatus());
        assertEquals(initialColor, game.getActiveColor());
    }

    @Test
    public void activePlayerCheckDetectionUsesHelper() {
        ChessGame game = ChessGame.newGame();
        Board board = game.getBoard();

        for (int file = 0; file < Board.SIZE; file++) {
            for (int rank = 0; rank < Board.SIZE; rank++) {
                board.clear(new Position(file, rank));
            }
        }

        board.setPiece(Position.fromAlgebraic("F1"), new Piece(Color.WHITE, PieceType.KING));
        board.setPiece(Position.fromAlgebraic("F10"), new Piece(Color.BLACK, PieceType.ROOK));

        assertTrue(game.isKingInCheck(Color.WHITE));
    }
}

