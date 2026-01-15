package com.schachapp.chess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class ChessGame {

    private final Board board;
    private Color activeColor;
    private GameStatus status;
    private final List<Piece> capturedPieces = new ArrayList<>();

    private ChessGame(Board board, Color activeColor, GameStatus status) {
        this.board = board;
        this.activeColor = activeColor;
        this.status = status;
    }

    public static ChessGame newGame() {
        Board board = new Board();
        setupInitialPosition(board);
        return new ChessGame(board, Color.WHITE, GameStatus.NORMAL);
    }

    public Board getBoard() {
        return board;
    }

    public Color getActiveColor() {
        return activeColor;
    }

    public GameStatus getStatus() {
        return status;
    }

    public List<Piece> getCapturedPieces() {
        return Collections.unmodifiableList(capturedPieces);
    }

    public boolean playMove(Move move) {
        if (!isLegalMove(move, activeColor)) {
            return false;
        }
        executeMove(move);

        activeColor = activeColor.opposite();
        if (isKingInCheck(activeColor)) {
            status = GameStatus.CHECK;
        } else {
            status = GameStatus.NORMAL;
        }
        return true;
    }

    private void executeMove(Move move) {
        Piece moving = board.getPiece(move.getFrom());
        Piece target = board.getPiece(move.getTo());
        if (target != null) {
            capturedPieces.add(target);
        }
        board.setPiece(move.getTo(), moving);
        board.clear(move.getFrom());
    }

    private static void setupInitialPosition(Board board) {
        Piece wLover = new Piece(Color.WHITE, PieceType.LOVER);
        Piece wRook = new Piece(Color.WHITE, PieceType.ROOK);
        Piece wKnight = new Piece(Color.WHITE, PieceType.KNIGHT);
        Piece wBishop = new Piece(Color.WHITE, PieceType.BISHOP);
        Piece wQueen = new Piece(Color.WHITE, PieceType.QUEEN);
        Piece wKing = new Piece(Color.WHITE, PieceType.KING);
        Piece wPawn = new Piece(Color.WHITE, PieceType.PAWN);

        Piece bLover = new Piece(Color.BLACK, PieceType.LOVER);
        Piece bRook = new Piece(Color.BLACK, PieceType.ROOK);
        Piece bKnight = new Piece(Color.BLACK, PieceType.KNIGHT);
        Piece bBishop = new Piece(Color.BLACK, PieceType.BISHOP);
        Piece bQueen = new Piece(Color.BLACK, PieceType.QUEEN);
        Piece bKing = new Piece(Color.BLACK, PieceType.KING);
        Piece bPawn = new Piece(Color.BLACK, PieceType.PAWN);

        board.setPiece(Position.fromAlgebraic("A1"), wLover);
        board.setPiece(Position.fromAlgebraic("B1"), wRook);
        board.setPiece(Position.fromAlgebraic("C1"), wKnight);
        board.setPiece(Position.fromAlgebraic("D1"), wBishop);
        board.setPiece(Position.fromAlgebraic("E1"), wQueen);
        board.setPiece(Position.fromAlgebraic("F1"), wKing);
        board.setPiece(Position.fromAlgebraic("G1"), wBishop);
        board.setPiece(Position.fromAlgebraic("H1"), wKnight);
        board.setPiece(Position.fromAlgebraic("I1"), wRook);
        board.setPiece(Position.fromAlgebraic("J1"), wRook);

        for (char file = 'A'; file <= 'J'; file++) {
            board.setPiece(Position.fromAlgebraic(file + "2"), wPawn);
        }

        board.setPiece(Position.fromAlgebraic("A10"), bRook);
        board.setPiece(Position.fromAlgebraic("B10"), bRook);
        board.setPiece(Position.fromAlgebraic("C10"), bKnight);
        board.setPiece(Position.fromAlgebraic("D10"), bBishop);
        board.setPiece(Position.fromAlgebraic("E10"), bKing);
        board.setPiece(Position.fromAlgebraic("F10"), bQueen);
        board.setPiece(Position.fromAlgebraic("G10"), bBishop);
        board.setPiece(Position.fromAlgebraic("H10"), bKnight);
        board.setPiece(Position.fromAlgebraic("I10"), bRook);
        board.setPiece(Position.fromAlgebraic("J10"), bLover);

        for (char file = 'A'; file <= 'J'; file++) {
            board.setPiece(Position.fromAlgebraic(file + "9"), bPawn);
        }
    }

    public boolean isLegalMove(Move move, Color player) {
        Position from = move.getFrom();
        Position to = move.getTo();
        if (from.equals(to)) {
            return false;
        }

        Piece moving = board.getPiece(from);
        if (moving == null || moving.getColor() != player) {
            return false;
        }
        Piece dest = board.getPiece(to);
        if (dest != null && dest.getColor() == player) {
            // cannot capture own piece
            return false;
        }

        if (!isBasicMovementAllowed(moving, from, to, dest != null)) {
            return false;
        }

        return !leavesKingInCheck(move, player);
    }

    private boolean leavesKingInCheck(Move move, Color player) {
        Board tmp = new Board();
        for (int file = 0; file < Board.SIZE; file++) {
            for (int rank = 0; rank < Board.SIZE; rank++) {
                Position p = new Position(file, rank);
                tmp.setPiece(p, board.getPiece(p));
            }
        }

        Piece moving = tmp.getPiece(move.getFrom());
        tmp.setPiece(move.getTo(), moving);
        tmp.clear(move.getFrom());

        Position kingPos = findKing(tmp, player);
        if (kingPos == null) {
            return false;
        }
        return isSquareAttacked(tmp, kingPos, player.opposite());
    }

    private Position findKing(Board board, Color color) {
        for (int file = 0; file < Board.SIZE; file++) {
            for (int rank = 0; rank < Board.SIZE; rank++) {
                Piece p = board.getPiece(file, rank);
                if (p != null && p.getColor() == color && p.getType() == PieceType.KING) {
                    return new Position(file, rank);
                }
            }
        }
        return null;
    }

    public boolean isKingInCheck(Color color) {
        Position kingPos = findKing(board, color);
        if (kingPos == null) {
            return false;
        }
        return isSquareAttacked(board, kingPos, color.opposite());
    }

    public boolean isActivePlayerInCheck() {
        return isKingInCheck(activeColor);
    }

    private boolean isSquareAttacked(Board board, Position square, Color attackerColor) {
        for (int file = 0; file < Board.SIZE; file++) {
            for (int rank = 0; rank < Board.SIZE; rank++) {
                Position from = new Position(file, rank);
                Piece p = board.getPiece(from);
                if (p == null || p.getColor() != attackerColor) {
                    continue;
                }
                if (isBasicMovementAllowed(p, from, square, board.getPiece(square) != null)) {
                    // additionally ensure path is clear for sliding pieces
                    if (pathClearForAttacker(board, p, from, square)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Only checks the movement pattern / distance rules of a single piece.
     */
    private boolean isBasicMovementAllowed(Piece piece, Position from, Position to, boolean isCapture) {
        int dx = to.getFile() - from.getFile();
        int dy = to.getRank() - from.getRank();
        int adx = Math.abs(dx);
        int ady = Math.abs(dy);

        switch (piece.getType()) {
            case KING:
            case LOVER:
                return adx <= 1 && ady <= 1;
            case QUEEN:
                return isStraightOrDiagonal(dx, dy) && pathClear(from, to);
            case ROOK:
                return isStraight(dx, dy) && pathClear(from, to);
            case BISHOP:
                // like normal bishop but max distance 6
                if (!isDiagonal(dx, dy)) {
                    return false;
                }
                if (Math.max(adx, ady) > 6) {
                    return false;
                }
                return pathClear(from, to);
            case KNIGHT:
                // modified knight: 3 forward / 1 sideways (only forward)
                int forward = piece.getColor() == Color.WHITE ? 1 : -1;
                if (dy != 3 * forward) {
                    return false;
                }
                return adx == 1;
            case PAWN:
                return isPawnMoveAllowed(piece, from, to, isCapture);
            default:
                return false;
        }
    }

    private boolean isPawnMoveAllowed(Piece piece, Position from, Position to, boolean isCapture) {
        int dir = piece.getColor() == Color.WHITE ? 1 : -1;
        int dx = to.getFile() - from.getFile();
        int dy = to.getRank() - from.getRank();

        if (isCapture) {
            // diagonal capture one step
            return Math.abs(dx) == 1 && dy == dir;
        } else {
            if (dx != 0) {
                return false;
            }
            if (dy == dir) {
                // single step forward; target must be empty (checked by caller)
                return board.getPiece(to) == null;
            } else if (dy == 2 * dir) {
                // double step from starting rank (rank 2 for white, 9 for black)
                int startRank = piece.getColor() == Color.WHITE ? 1 : 8;
                if (from.getRank() != startRank) {
                    return false;
                }
                int intermediateRank = from.getRank() + dir;
                if (board.getPiece(new Position(from.getFile(), intermediateRank)) != null) {
                    return false;
                }
                return board.getPiece(to) == null;
            }
        }
        return false;
    }

    private boolean isStraight(int dx, int dy) {
        return (dx == 0 && dy != 0) || (dx != 0 && dy == 0);
    }

    private boolean isDiagonal(int dx, int dy) {
        return Math.abs(dx) == Math.abs(dy) && dx != 0;
    }

    private boolean isStraightOrDiagonal(int dx, int dy) {
        return isStraight(dx, dy) || isDiagonal(dx, dy);
    }

    private boolean pathClear(Position from, Position to) {
        int dx = Integer.compare(to.getFile(), from.getFile());
        int dy = Integer.compare(to.getRank(), from.getRank());
        int file = from.getFile() + dx;
        int rank = from.getRank() + dy;
        while (file != to.getFile() || rank != to.getRank()) {
            if (!Board.isInside(file, rank)) {
                return false;
            }
            if (board.getPiece(file, rank) != null) {
                return false;
            }
            file += dx;
            rank += dy;
        }
        return true;
    }

    private boolean pathClearForAttacker(Board b, Piece piece, Position from, Position to) {
        switch (piece.getType()) {
            case KING:
            case LOVER:
            case KNIGHT:
            case PAWN:
                return true;
            case ROOK:
            case BISHOP:
            case QUEEN:
                int dx = Integer.compare(to.getFile(), from.getFile());
                int dy = Integer.compare(to.getRank(), from.getRank());
                int file = from.getFile() + dx;
                int rank = from.getRank() + dy;
                while (file != to.getFile() || rank != to.getRank()) {
                    if (!Board.isInside(file, rank)) {
                        return false;
                    }
                    if (b.getPiece(file, rank) != null) {
                        return false;
                    }
                    file += dx;
                    rank += dy;
                }
                return true;
            default:
                return false;
        }
    }
}

