package com.schachapp.chess;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PieceMovementTest {

    @Test
    public void knightMovesThreeForwardOneSide() {
        ChessGame game = ChessGame.newGame();
        Board board = game.getBoard();

        // move white knight from C1 to B4 should be legal
        Move move = new Move(Position.fromAlgebraic("C1"), Position.fromAlgebraic("B4"));
        assertTrue(game.isLegalMove(move, Color.WHITE));
    }

    @Test
    public void bishopLimitedToSixSquares() {
        ChessGame game = ChessGame.newGame();
        Board board = game.getBoard();

        // clear path and test bishop distance > 6 is illegal
        // manually place a white bishop on C3
        Position from = Position.fromAlgebraic("C3");
        board.setPiece(from, new Piece(Color.WHITE, PieceType.BISHOP));
        Position toFar = Position.fromAlgebraic("J10"); // distance 7

        Move longMove = new Move(from, toFar);
        assertFalse(game.isLegalMove(longMove, Color.WHITE));
    }

    @Test
    public void loverMovesLikeKing() {
        ChessGame game = ChessGame.newGame();
        Board board = game.getBoard();

        Position from = Position.fromAlgebraic("A1");
        Position to = Position.fromAlgebraic("A2");

        // remove pawn at A2 to allow move
        board.clear(to);
        Move move = new Move(from, to);
        assertTrue(game.isLegalMove(move, Color.WHITE));
    }
}

