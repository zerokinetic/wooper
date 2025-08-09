package org.example;
import java.io.BufferedReader;
import  java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public enum MetaCommandResult{
        META_COMMAND_SUCCESS,
        META_COMMAND_UNRECOGNIZED_COMMAND
    }

    public enum PrepareResult {
        PREPARE_SUCCESS,
        PREPARE_UNRECOGNIZED_STATEMENT
    }


    public enum StatementType {
        STATEMENT_INSERT,
        STATEMENT_SELECT
    }


    public class Statement {
        public StatementType type;
    }

    public static PrepareResult PrepareStatement(String inputBuffer, String statement) {
        if (inputBuffer.equals("insert")) {
            return PrepareResult.PREPARE_SUCCESS;
        } else if (inputBuffer.equals("select")) {
            return PrepareResult.PREPARE_SUCCESS;
        }
        return PrepareResult.PREPARE_UNRECOGNIZED_STATEMENT;
    }

    public static void executeStatement(Statement statement) {
        switch (statement.type) {
            case STATEMENT_INSERT:
                System.out.printf("This is where we would do an insert.\n");
                break;
            case STATEMENT_SELECT:
                System.out.printf("This is where we would do a select.\n");
                break;

        }
    }

    public static void printPrompt() {
        System.out.printf("wooper_db > ");
    }

    public static MetaCommandResult doMetaCommand(String inputBuffer) {
        if (inputBuffer.trim().equals(".exit")) {
            System.exit(0);
        }
        return MetaCommandResult.META_COMMAND_UNRECOGNIZED_COMMAND;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                printPrompt();
                String inp = reader.readLine();
                if (inp.charAt(0) == '.') {
                     switch (doMetaCommand(inp)) {
                         case MetaCommandResult.META_COMMAND_SUCCESS:
                             continue;
                         case MetaCommandResult.META_COMMAND_UNRECOGNIZED_COMMAND:
                             System.out.printf("Unrecognized command '%s'", inp);
                             continue;
                     }

                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}