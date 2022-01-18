import java.io.PrintStream;
import java.util.Scanner;

public class Duke {

    private final static Scanner ioIn = new Scanner(System.in);
    private final static PrintStream ioOut = System.out;
    private final static TodoList todoList = new TodoList();

    private static void printLine() {
        String line = "\n~._.~\"~._.~\"~._.~\"~._.~\"~._.~\"~._.~\"" +
                "~._.~\"~._.~\"~._.~\"~._.~\"~._.~\"~._.~\"~._.~\n";
        ioOut.println(line);
    }

    private static void printMenu() {
        String art = "                      .-.\n" +
                "         heehee      /aa \\_\n" +
                "                   __\\-  / )                 .-.\n" +
                "         .-.      (__/    /        haha    _/oo \\\n" +
                "       _/ ..\\       /     \\               ( \\v  /__\n" +
                "      ( \\  u/__    /       \\__             \\/   ___)\n" +
                "       \\    \\__)   \\_.-._._   )  .-.       /     \\\n" +
                "       /     \\             `-`  / ee\\_    /       \\_\n" +
                "    __/       \\               __\\  o/ )   \\_.-.__   )\n" +
                "   (   _._.-._/     hoho     (___   \\/           '-'\n" +
                "jgs '-'                        /     \\\n" +
                "                             _/       \\    teehee\n" +
                "                            (   __.-._/\n";
        String greet = "Heee hooo I'm Tsohg! How can I help you?";
        ioOut.println(art);
        ioOut.println(greet);
    }

    private static boolean takeInput() {
        printLine();
        String input = ioIn.nextLine();
        printLine();

        String[] splitInput = input.split("\\s+");
        String command = splitInput[0];
        switch (command) {
            case "bye":
                ioOut.println("Seeeee youuuu sooon...");
                return false;
            case "list":
                list(); break;
            case "mark":
                mark(splitInput); break;
            case "unmark":
                unmark(splitInput); break;
            default:
                add(input); break;
        }
        return true;
    }

    private static void list() {
        ioOut.println("Here are the tasks in your list:");
        ioOut.print(todoList.showList());
    }

    private static void mark(String[] splitInput) {
        int index = Integer.parseInt(splitInput[1]) - 1;
        ioOut.println("Nice! I've marked this task as done: ");
        ioOut.println(todoList.markItem(index));
    }

    private static void unmark(String[] splitInput) {
        int index = Integer.parseInt(splitInput[1]) - 1;
        ioOut.println("OK, I've marked this task as not done yet:");
        ioOut.println(todoList.unmarkItem(index));
    }

    private static void add(String input) {
        todoList.addItem(input);
        ioOut.println("added: " + input);
    }

    public static void main(String[] args) {
        printMenu();
        while (true) {
            if (!takeInput()) break;
        }
        printLine();
    }
}
