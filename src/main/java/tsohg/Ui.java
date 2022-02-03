package tsohg;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * The class handling UI and IO from users.
 */
public class Ui {

    private static final Scanner IN = new Scanner(System.in);
    private static final PrintStream OUT = System.out;
    private TaskList tasks;

    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Runs the program.
     */
    public void run() {
        printMenu();
        while (true) {
            try {
                if (!takeInput()) {
                    break;
                }
            } catch (TsohgException e) {
                OUT.println(e.getMessage());
            }
        }
        printLine();
    }

    private void printLine() {
        String line = "\n~._.~\"~._.~\"~._.~\"~._.~\"~._.~\"~._.~\""
                + "~._.~\"~._.~\"~._.~\"~._.~\"~._.~\"~._.~\"~._.~\n";
        OUT.println(line);
    }

    private void printMenu() {
        String art = "                      .-.\n"
                + "         heehee      /aa \\_\n"
                + "                   __\\-  / )                 .-.\n"
                + "         .-.      (__/    /        haha    _/oo \\\n"
                + "       _/ ..\\       /     \\               ( \\v  /__\n"
                + "      ( \\  u/__    /       \\__             \\/   ___)\n"
                + "       \\    \\__)   \\_.-._._   )  .-.       /     \\\n"
                + "       /     \\             `-`  / ee\\_    /       \\_\n"
                + "    __/       \\               __\\  o/ )   \\_.-.__   )\n"
                + "   (   _._.-._/     hoho     (___   \\/           '-'\n"
                + "jgs '-'                        /     \\\n"
                + "                             _/       \\    teehee\n"
                + "                            (   __.-._/\n";
        String greet = "Heee hooo I'm Tsohg! How can I help you?";
        OUT.println(art);
        OUT.println(greet);
    }

    private boolean takeInput() throws TsohgException {
        printLine();
        String input = IN.nextLine();
        printLine();

        String[] splitInput = input.split("\\s+", 2);
        String command = splitInput[0];
        String argument = splitInput.length == 2 ? splitInput[1] : null;
        switch (command) {
        case "bye":
            OUT.println("Seeeee youuuu sooon...");
            return false;
        case "list":
            list();
            break;
        case "delete":
            delete(argument);
            break;
        case "mark":
            mark(argument);
            break;
        case "unmark":
            unmark(argument);
            break;
        case "todo":
            todo(argument);
            break;
        case "deadline":
            deadline(argument);
            break;
        case "event":
            event(argument);
            break;
        case "find":
            find(argument);
            break;
        default:
            throw new TsohgException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }

    private void list() {
        OUT.println("Here are the tasks in your list:");
        OUT.print(tasks.toString());
    }

    private void delete(String argument) throws TsohgException {
        int index = Integer.parseInt(argument) - 1;
        OUT.println("Noted. I've removed this task:");
        OUT.println(tasks.deleteItem(index));
        OUT.println(tasks.listCount());
    }

    private void mark(String argument) throws TsohgException {
        int index = Integer.parseInt(argument) - 1;
        OUT.println("Nice! I've marked this task as done:");
        OUT.println(tasks.markItem(index));
    }

    private void unmark(String argument) throws TsohgException {
        int index = Integer.parseInt(argument) - 1;
        OUT.println("OK, I've marked this task as not done yet:");
        OUT.println(tasks.unmarkItem(index));
    }

    private void todo(String argument) throws TsohgException {
        if (argument == null) {
            throw new TsohgException("OOPS!!! The description of a todo cannot be empty.");
        }
        String response = tasks.addTodo(argument);
        OUT.println("Got it. I've added this task:");
        OUT.println(response);
        OUT.println(tasks.listCount());
    }

    private void deadline(String argument) throws TsohgException {
        String[] split = argument.split(" /by ", 2);
        if (split.length != 2) {
            throw new TsohgException("Date is not provided!");
        }
        String name = split[0];
        String date = split[1];
        String response = tasks.addDeadline(name, date);
        OUT.println("Got it. I've added this task:");
        OUT.println(response);
        OUT.println(tasks.listCount());
    }

    private void event(String argument) throws TsohgException {
        String[] split = argument.split(" /at ", 2);
        if (split.length != 2) {
            throw new TsohgException("Date is not provided!");
        }
        String name = split[0];
        String date = split[1];
        String response = tasks.addEvent(name, date);
        OUT.println("Got it. I've added this task:");
        OUT.println(response);
        OUT.println(tasks.listCount());
    }

    private void find(String argument) throws TsohgException {
        if (argument == null) {
            throw new TsohgException("OOPS!!! The description of find cannot be empty.");
        }
        String response = tasks.find(argument);
        OUT.println("Here are the matching tasks in your list:");
        OUT.println(response);
    }
}
