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
        if (input.equals("bye")) {
            ioOut.println("Seeeee youuuu sooon...");
            return false;
        } else if (input.equals("list")) {
            ioOut.print(todoList.showList());
        } else {
            todoList.addItem(input);
            ioOut.println("added: " + input);
        }
        return true;
    }

    public static void main(String[] args) {
        printMenu();
        while (true) {
            if (!takeInput()) break;
        }
        printLine();
    }
}
