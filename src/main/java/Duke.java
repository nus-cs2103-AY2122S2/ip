import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String segLine = "    ____________________________________________________________";
    private static final String indent = "    ";
    private static final String logo = indent + " ____        _        \n"
            + indent + "|  _ \\ _   _| | _____ \n"
            + indent + "| | | | | | | |/ / _ \\\n"
            + indent + "| |_| | |_| |   <  __/\n"
            + indent + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String byePhrase = "Bye. Hope to see you again soon!";

    private final String name;
    private final List<Task> tasks;

    private Duke(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    private void boot() {
        System.out.println(logo);
        System.out.println(segLine);
        System.out.println(indent + "Hello! I'm " + name + ".");
        System.out.println(indent + "What can I do for you?");
        System.out.println(segLine);
    }

    private void addTodo(String s) {
        Todo t = new Todo(s);
        tasks.add(t);
        this.printAdd();
    }

    private void addDdl(String s) {
        int i = s.indexOf(" /by ");
        if (i != -1) {
            Deadline t = new Deadline(s.substring(0, i), s.substring(i + 5));
            tasks.add(t);
            this.printAdd();
        }
        // else exception
    }

    private void addEvt(String s) {
        int i = s.indexOf(" /at ");
        if (i != -1) {
            Event t = new Event(s.substring(0, i), s.substring(i + 5));
            tasks.add(t);
            this.printAdd();
        }
        // else exception
    }

    private void printAdd() {
        System.out.println(indent + "Got it. I've added this task:");
        int n = tasks.size();
        System.out.println(indent + "  " + tasks.get(n - 1));
        System.out.println(indent + "Now you have " + n + " tasks in the list.");
    }

    private void list() {
        if (tasks.size() == 0) {
            System.out.println(indent + "You don't have tasks listed.");
        } else {
            System.out.println(indent + "Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.print(indent);
                System.out.print(i + 1);
                System.out.println("." + tasks.get(i));
            }
        }
    }

    private void mark(int index, boolean done) {
        tasks.set(index, tasks.get(index).mark(done));
        if (done) {
            System.out.println(indent + "Nice! I've marked this task as done:");
        } else {
            System.out.println(indent + "OK, I've marked this task as not done yet:");
        }
        System.out.println(indent + "  " + tasks.get(index));
    }

    private static void unknown() {
        System.out.println(indent + "Unknown Command.");
    }

    private static void bye() {
        System.out.println(indent + byePhrase);
        System.out.println(segLine);
    }

    public static void main(String[] args) {
        Duke cindy = new Duke("Duke-Cindy");
        cindy.boot();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String s = sc.nextLine();
            System.out.println(segLine);
            if (s.startsWith("list")) {
                cindy.list();
            } else if (s.startsWith("todo ")) {
                cindy.addTodo(s.substring(5));
            } else if (s.startsWith("deadline ")) {
                cindy.addDdl(s.substring(9));
            } else if (s.startsWith("event ")) {
                cindy.addEvt(s.substring(6));
            } else if (s.startsWith("mark ")) {
                Scanner temp = new Scanner(s.substring(5));
                if (temp.hasNextInt()) {
                    cindy.mark(temp.nextInt() - 1, true);
                }
                // else exception
            } else if (s.startsWith("unmark ")) {
                Scanner temp = new Scanner(s.substring(7));
                if (temp.hasNextInt()) {
                    cindy.mark(temp.nextInt() - 1, false);
                }
                // else exception
            } else if (s.startsWith("bye")) {
                Duke.bye();
                break;
            } else {
                Duke.unknown();
            }
            System.out.println(segLine);
        }
    }
}
