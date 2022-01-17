import java.util.Scanner;
import java.util.function.Consumer;

public class Duke {
    private static final String SEPARATOR = "\t------------------------------------";
    private static final String BOT_NAME = "Megumin";

    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = readInput(sc);
            processCommand(command);
            System.out.println();
        }
    }

    private static void greet() {
        printBlock((linePrinter) -> {
            linePrinter.accept("Hi! I'm " + BOT_NAME);
            linePrinter.accept("What do you need?");
        });
        System.out.println();
    }

    private static String readInput(Scanner sc) {
        System.out.println("Enter a Command:");
        String line = sc.nextLine();

        return line;
    }

    private static void processCommand(String command) {
        printBlock((linePrinter) -> {
            linePrinter.accept("\t" + command);
        });
    }

    private static void printBlock(Consumer<Consumer<String>> action) {
        System.out.println(SEPARATOR);
        action.accept((line) -> {
            System.out.println("\t" + line);
        });
        System.out.println(SEPARATOR);
    }
}
