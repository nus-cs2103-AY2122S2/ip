package duke;

import duke.exception.DukeException;
import duke.exception.DukeIoException;
import duke.exception.DukeIllegalArgumentException;
import duke.exception.DukeInvalidCommandException;
import duke.util.Printable;

import java.util.Scanner;
import java.util.function.Function;

public class Ui {
    private static final String SEPARATOR = "\t------------------------------------";
    private static final String BOT_NAME = "Megumin";
    private static final String ERROR_INVALID_COMMAND = "I do not understand you!";
    private static final String ERROR_INVALID_SYNTAX = "There was a problem understanding you:";
    private static final String ERROR_IO = "I had a problem reading / writing my memory!";

    private static Ui instance;

    private final Scanner scanner;

    private Ui() {
        this.scanner = new Scanner(System.in);
    }

    public Ui greet() {
        printCommand((linePrinter) -> {
            linePrinter.print("Hi! I'm " + BOT_NAME);
            linePrinter.print("What do you need?");
            return true;
        });
        System.out.println();

        return this;
    }

    public String readInput() {
        System.out.println("Enter a Command or New Task:");

        return this.scanner.nextLine();
    }

    public boolean printCommand(Function<Printable, Boolean> action) {
        System.out.println(SEPARATOR);
        final boolean isRunning = action.apply((line) -> {
            System.out.println("\t" + line);
        });
        System.out.println(SEPARATOR);
        return isRunning;
    }

    public void printError(Printable linePrinter, DukeException ex) {
        if (ex instanceof DukeInvalidCommandException) {
            linePrinter.print(ERROR_INVALID_COMMAND);
        } else if (ex instanceof DukeIllegalArgumentException) {
            linePrinter.print(ERROR_INVALID_SYNTAX);
            linePrinter.print(ex.getMessage());
        } else if (ex instanceof DukeIoException) {
            linePrinter.print(ERROR_IO);
        }
    }

    public static Ui getInstance() {
        if (instance == null) {
            instance = new Ui();
        }
        return instance;
    }
}
