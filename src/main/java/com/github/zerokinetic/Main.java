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

    static MetaCommandResult doMetaCommand(InputBuffer inputBuffer) {
        if (inputBuffer.buffer.toString().trim().equals(".exit")) {
            System.out.println("Exiting...");
            System.exit(1);
        }
        return MetaCommandResult.META_COMMAND_UNRECOGNIZED_COMMAND;
    }

    public static class RowToInsert {
        int id;
        String username;
        String email;
    }

    public static class Statement {
        StatementType statementType;
        RowToInsert rowToInsert;
    }

    static PrepareResult prepareStatement(InputBuffer inputBuffer, Statement statement) {
        if (inputBuffer.buffer.toString().equals("insert")) {
            statement.statementType = StatementType.STATEMENT_INSERT;
            String[] args = inputBuffer.buffer.toString().split(" ");
            statement.rowToInsert.id = Integer.parseInt(args[1]);
            statement.rowToInsert.username = args[2];
            statement.rowToInsert.email = args[3];


            return PrepareResult.PREPARE_SUCCESS;
        } else if(inputBuffer.buffer.toString().equals("select")) {
            statement.statementType = StatementType.STATEMENT_SELECT;
            return PrepareResult.PREPARE_SUCCESS;
        }
        return PrepareResult.PREPARE_UNRECOGNIZED_STATEMENT;
    }

    static void executeStatement(Statement statement) {
        switch (statement.statementType) {
            case STATEMENT_INSERT:
                System.out.print("This is where we would do an insert.\n");
                break;
            case STATEMENT_SELECT:
                System.out.print("This is where we would do a select.\n");
                break;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printPrompt();
            InputBuffer inputBuffer = newInputBuffer();
            readInputBuffer(inputBuffer, scanner);


//            if(inputBuffer.buffer.toString().trim().equals(".exit")) {
//                System.out.println("Exiting...");
//                break;
//            } else {
//                System.out.printf("Unrecognized command '%s'.\n", inputBuffer.buffer);
//            }

            if (inputBuffer.buffer.toString().charAt(0) == '.') {
                switch (doMetaCommand(inputBuffer)) {
                    case MetaCommandResult.META_COMMAND_SUCCESS:
                        continue;
                    case MetaCommandResult.META_COMMAND_UNRECOGNIZED_COMMAND:
                        System.out.printf("Unrecognized command '%s'\n", inputBuffer.buffer);
                }
            } else {
                Statement statement = new Statement();
                switch (prepareStatement(inputBuffer, statement)) {
                    case PrepareResult.PREPARE_SUCCESS:
                        executeStatement(statement);
                        System.out.print("Executed!!");
                        break;
                    case PrepareResult.PREPARE_UNRECOGNIZED_STATEMENT:
                        System.out.printf("Unrecognized keyword at start of '%s'.\n",inputBuffer.buffer);
                }
            }
        }
    }
}