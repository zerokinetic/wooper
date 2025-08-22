package com.github.zerokinetic;
import java.util.Scanner;

public class Main {

    public static void printPrompt() {
        System.out.print("wooper_db> ");
    }

    private static class InputBuffer {
        StringBuffer buffer;
    }

    static InputBuffer newInputBuffer() {
        InputBuffer inputBuffer = new InputBuffer();
        inputBuffer.buffer = new StringBuffer();
        return inputBuffer;
    }

    static void readInputBuffer(InputBuffer inputBuffer, Scanner scanner) {
        inputBuffer.buffer.append(scanner.nextLine());

//        System.out.println(inputBuffer.buffer.toString());
//        System.out.println(inputBuffer.bufferLength);
//        System.out.println(inputBuffer.inputLength);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printPrompt();
            InputBuffer inputBuffer = newInputBuffer();
            readInputBuffer(inputBuffer, scanner);


            if(inputBuffer.buffer.toString().trim().equals(".exit")) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.printf("Unrecognized command '%s'.\n", inputBuffer.buffer.toString());
            }
        }
        scanner.close();
    }
}