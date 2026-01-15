package com.schachapp;

import com.schachapp.chess.ChessGame;
import com.schachapp.chess.GameStatus;
import com.schachapp.chess.Move;
import com.schachapp.chess.MoveParser;
import com.schachapp.chess.TextBoardRenderer;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ChessGame game = ChessGame.newGame();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Abgeändertes Schach – 10x10");
        System.out.println("Eingabeformat: z.B. A2-A4 oder A2A4, 'quit' zum Beenden.");

        while (true) {
            System.out.println();
            System.out.println(TextBoardRenderer.render(game.getBoard()));
            System.out.println("Am Zug: " + game.getActiveColor());
            System.out.print("Zug eingeben: ");

            if (!scanner.hasNextLine()) {
                break;
            }
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("quit") || line.equalsIgnoreCase("exit")) {
                break;
            }
            if (line.isEmpty()) {
                continue;
            }

            try {
                Move move = MoveParser.parse(line);
                boolean ok = game.playMove(move);
                if (!ok) {
                    System.out.println("Ungültiger Zug.");
                } else if (game.getStatus() == GameStatus.CHECK) {
                    System.out.println("SCHACH gegen " + game.getActiveColor() + "!");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Fehlerhafte Eingabe: " + ex.getMessage());
            }
        }

        System.out.println("Spiel beendet.");
    }
}
