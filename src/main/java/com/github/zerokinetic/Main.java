package com.github.zerokinetic;

import java.util.Scanner;

public class Main {

    public static void printPrompt() {
        System.out.printf("wooper_db> ");
    }
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();

        while (true) {
            printPrompt();
            if(sb.equals(".exit")) {
                System.exit(1);
            } else {
                System.out.printf("Unrecognized command '%s'.\n", sb);
                System.exit(0);
            }
        }
    }
}