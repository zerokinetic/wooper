package org.example;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public enum MetaCommandResult {
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

    public static class Statement {
        public StatementType type;
    }

    public static PrepareResult PrepareStatement(String inputBuffer, Statement statement) {
        if (inputBuffer.startsWith("insert")) {
            statement.type = StatementType.STATEMENT_INSERT;
            return PrepareResult.PREPARE_SUCCESS;
        } else if (inputBuffer.equals("select")) {
            statement.type = StatementType.STATEMENT_SELECT;
            return PrepareResult.PREPARE_SUCCESS;
        }
        return PrepareResult.PREPARE_UNRECOGNIZED_STATEMENT;
    }

    public static void executeStatement(Statement statement) {
        switch (statement.type) {
            case STATEMENT_INSERT:
                System.out.println("This is where we would do an insert.");
                break;
            case STATEMENT_SELECT:
                System.out.println("This is where we would do a select.");
                break;
        }
    }

    public static void printPrompt() {
        System.out.print("wooper_db > ");
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
                if (inp == null) break; // handle EOF
                if (inp.isEmpty()) continue;

                if (inp.charAt(0) == '.') {
                    switch (doMetaCommand(inp)) {
                        case META_COMMAND_SUCCESS:
                            continue;
                        case META_COMMAND_UNRECOGNIZED_COMMAND:
                            System.out.printf("Unrecognized command '%s'\n", inp);
                            continue;
                    }
                }

                Statement statement = new Statement();
                switch (PrepareStatement(inp, statement)) {
                    case PREPARE_SUCCESS:
                        break;
                    case PREPARE_UNRECOGNIZED_STATEMENT:
                        System.out.printf("Unrecognized keyword at start of '%s'\n", inp);
                        continue;
                }

                executeStatement(statement);
                System.out.println("Executed");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}