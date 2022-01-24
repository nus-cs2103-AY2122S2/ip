import java.util.Scanner;
import java.util.function.Function;

public class Duke {
    private static final String SEPARATOR = "\t------------------------------------";
    private static final String BOT_NAME = "Megumin";
    private static final String ERROR_INVALID_COMMAND = "I do not understand you!";
    private static final String ERROR_INVALID_SYNTAX = "There was a problem understanding you:";

    private static TaskList taskList;

    public static void main(String[] args) {
        init();
        greet();

        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            String command = readInput(sc);
            isRunning = printCommand((linePrinter) -> {
                try {
                    return Parser.parse(command, linePrinter, taskList);
                } catch (DukeInvalidCommandException ex) {
                    linePrinter.print(ERROR_INVALID_COMMAND);
                } catch (DukeIllegalArgumentException ex) {
                    linePrinter.print(ERROR_INVALID_SYNTAX);
                    linePrinter.print(ex.getMessage());
                }
                return true;
            });

            System.out.println();
        }
    }

    private static void init() {
        try {
            taskList = Storage.load();
            taskList.registerListener(store -> {
                try {
                    Storage.save(store);
                } catch (DukeIOException ex) {
                    System.out.println("Warning: An error occurred while saving Task list");
                }
            });
        } catch (DukeIOException ex) {
            System.out.println("Cannot write to working directory.\n"
                    + "Please check that you have write to the directory permissions.\n"
                    + "Will not save any changes!");
        }
    }

    private static void greet() {
        printCommand((linePrinter) -> {
            linePrinter.print("Hi! I'm " + BOT_NAME);
            linePrinter.print("What do you need?");
            return true;
        });
        System.out.println();
    }

    private static String readInput(Scanner sc) {
        System.out.println("Enter a Command or New Task:");

        return sc.nextLine();
    }

    private static boolean printCommand(Function<IPrintable, Boolean> action) {
        System.out.println(SEPARATOR);
        boolean isRunning = action.apply((line) -> {
            System.out.println("\t" + line);
        });
        System.out.println(SEPARATOR);
        return isRunning;
    }
}
