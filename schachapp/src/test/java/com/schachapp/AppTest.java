package com.schachapp;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class AppTest {

    @Test
    public void mainExitsOnQuitAndPrintsBoard() {
        String input = "quit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        java.io.InputStream originalIn = System.in;
        try {
            System.setIn(in);
            System.setOut(new PrintStream(outContent));

            App.main(new String[0]);

            String output = outContent.toString("UTF-8");
            assertTrue(output.contains("Abgeändertes Schach"));
            assertTrue(output.contains("A B C D E F G H I J"));
        } catch (Exception e) {
            throw new AssertionError(e);
        } finally {
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
    }
}
