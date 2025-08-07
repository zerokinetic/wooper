package org.example;
import java.io.BufferedReader;
import  java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            printPrompt();
            while (true) {
                String inp = reader.readLine();
                if (inp.equals(".exit")) {
                    System.exit(1);
                } else {
                    System.out.printf("Unrecognized command: " + inp);
                    System.exit(1);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printPrompt() {
        System.out.printf("wooper_db > ");
    }
}