package duke;

import duke.exception.DukeException;
import duke.exception.DukeIOException;
import duke.exception.DukeIllegalArgumentException;
import duke.exception.DukeInvalidCommandException;
import duke.util.IPrintable;

import java.util.Scanner;
import java.util.function.Function;

public class Ui {
    private static final String SEPARATOR = "\t------------------------------------";
    private static final String BOT_NAME = "Megumin";
    private static final String ERROR_INVALID_COMMAND = "I do not understand you!";
    private static final String ERROR_INVALID_SYNTAX = "There was a problem understanding you:";
    private static final String ERROR_IO = "I had a problem reading / writing my memory!";

    private static Ui instance;

    /** Global scanner used by the Ui class. */
    private final Scanner scanner;

    private Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a greeting for the initial startup.
     * @return The global instance of the <code>Ui</code> class.
     */
    public Ui greet() {
        printCommand((linePrinter) -> {
            linePrinter.print("Hi! I'm " + BOT_NAME);
            linePrinter.print("What do you need?");
            return true;
        });
        System.out.println();

        return this;
    }

    /**
     * Reads a line of input from {@link System#in}.
     * @return Line of input read.
     */
    public String readInput() {
        System.out.println("Enter a Command or New Task:");

        return this.scanner.nextLine();
    }

    /**
     * Provides a {@link IPrintable} object that can be printed to.
     * Handles formatting of printed contents, including indentation and surrounding separators.
     * @param action A <code>Function</code> object that accepts the provided {@link IPrintable} object
     *               and returns a boolean to indicate if the application should continue running.
     * @return <code>Boolean</code> indicating if the application should continue running.
     */
    public boolean printCommand(Function<IPrintable, Boolean> action) {
        System.out.println(SEPARATOR);
        boolean isRunning = action.apply((line) -> {
            System.out.println("\t" + line);
        });
        System.out.println(SEPARATOR);
        return isRunning;
    }

    /**
     * Prints an error message onto the {@link IPrintable} object provided.
     * Error message printer depends on the type of the provided {@link DukeException} object.
     * @param linePrinter {@link IPrintable} object that the error should be printed to.
     * @param ex Exception object that the error message should be printed for.
     */
    public void printError(IPrintable linePrinter, DukeException ex) {
        if (ex instanceof DukeInvalidCommandException) {
            linePrinter.print(ERROR_INVALID_COMMAND);
        } else if (ex instanceof DukeIllegalArgumentException) {
            linePrinter.print(ERROR_INVALID_SYNTAX);
            linePrinter.print(ex.getMessage());
        } else if (ex instanceof DukeIOException) {
            linePrinter.print(ERROR_IO);
        }
    }

    /**
     * Returns the global instance of the {@link Ui} class.
     * @return Global instance of the <code>Ui</code> class.
     */
    public static Ui getInstance() {
        if (instance == null) {
            instance = new Ui();
        }
        return instance;
    }
}
