package duke;

import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    private final static Scanner IN = new Scanner(System.in);
    private final static PrintStream OUT = System.out;
    private TaskList tasks;

    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    public void run() {
        printMenu();
        while (true) {
            try {
                if (!takeInput()) break;
            } catch (DukeException e) {
                OUT.println(e.getMessage());
            }
        }
        printLine();
    }

    private void printLine() {
        String line = "\n~._.~\"~._.~\"~._.~\"~._.~\"~._.~\"~._.~\"" +
                "~._.~\"~._.~\"~._.~\"~._.~\"~._.~\"~._.~\"~._.~\n";
        OUT.println(line);
    }

    private void printMenu() {
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
        OUT.println(art);
        OUT.println(greet);
    }

    private boolean takeInput() throws DukeException {
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
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }

    private void list() {
        OUT.println("Here are the tasks in your list:");
        OUT.print(tasks.toString());
    }

    private void delete(String argument) throws DukeException {
        int index = Integer.parseInt(argument) - 1;
        OUT.println("Noted. I've removed this task:");
        OUT.println(tasks.deleteItem(index));
        OUT.println(tasks.listCount());
    }

    private void mark(String argument) throws DukeException {
        int index = Integer.parseInt(argument) - 1;
        OUT.println("Nice! I've marked this task as done:");
        OUT.println(tasks.markItem(index));
    }

    private void unmark(String argument) throws DukeException {
        int index = Integer.parseInt(argument) - 1;
        OUT.println("OK, I've marked this task as not done yet:");
        OUT.println(tasks.unmarkItem(index));
    }

    private void todo(String argument) throws DukeException {
        if (argument == null)
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        String response = tasks.addTodo(argument);
        OUT.println("Got it. I've added this task:");
        OUT.println(response);
        OUT.println(tasks.listCount());
    }

    private void deadline(String argument) throws DukeException {
        String[] split = argument.split(" /by ", 2);
        if (split.length != 2) {
            throw new DukeException("Date is not provided!");
        }
        String name = split[0];
        String date = split[1];
        String response = tasks.addDeadline(name, date);
        OUT.println("Got it. I've added this task:");
        OUT.println(response);
        OUT.println(tasks.listCount());
    }

    private void event(String argument) throws DukeException {
        String[] split = argument.split(" /at ", 2);
        if (split.length != 2) {
            throw new DukeException("Date is not provided!");
        }
        String name = split[0];
        String date = split[1];
        String response = tasks.addEvent(name, date);
        OUT.println("Got it. I've added this task:");
        OUT.println(response);
        OUT.println(tasks.listCount());
    }
}
