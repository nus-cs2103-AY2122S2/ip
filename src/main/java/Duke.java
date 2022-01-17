import java.util.Scanner;
import java.util.function.Consumer;

public class Duke {
    private static final String SEPARATOR = "\t------------------------------------";
    private static final String BOT_NAME = "Megumin";
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";

    private static TaskStore taskStore;

    public static void main(String[] args) {
        init();
        greet();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = readInput(sc);
            if (command.equalsIgnoreCase(COMMAND_EXIT)) {
                break;
            }
            printBlock((linePrinter) -> {
                processCommand(command, linePrinter);
            });

            System.out.println();
        }
        printBlock((linePrinter) -> {
            linePrinter.print("Goodbye! Have a Nice Day.");
        });
    }

    private static void init() {
        taskStore = new TaskStore();
    }

    private static void greet() {
        printBlock((linePrinter) -> {
            linePrinter.print("Hi! I'm " + BOT_NAME);
            linePrinter.print("What do you need?");
        });
        System.out.println();
    }

    private static String readInput(Scanner sc) {
        System.out.println("Enter a Command or New Task:");
        String line = sc.nextLine();

        return line;
    }

    private static void processCommand(String command, IPrintable linePrinter) {
        if (command.equalsIgnoreCase(COMMAND_LIST)) {
            taskStore.forEach((index, task) -> {
                // Note that index passed into this consumer is 0-based. Increment by 1 for readability
                linePrinter.print(String.format("%d. %s", index + 1, task.getDescription()));
            });
        } else {
            taskStore.addTask(command);
            linePrinter.print(String.format("added: %s", command));
        }
    }

    private static void printBlock(Consumer<IPrintable> action) {
        System.out.println(SEPARATOR);
        action.accept((line) -> {
            System.out.println("\t" + line);
        });
        System.out.println(SEPARATOR);
    }
}
