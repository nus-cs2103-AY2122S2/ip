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

    private void add(String s) {
        Task t = new Task(s);
        tasks.add(t);
        System.out.println(indent + "added: " + s);
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

    public void mark(int index, boolean done) {
        tasks.set(index, tasks.get(index).mark(done));
        if (done) {
            System.out.println(indent + "Nice! I've marked this task as done:");
        } else {
            System.out.println(indent + "OK, I've marked this task as not done yet:");
        }
        System.out.println(indent + "  " + tasks.get(index));
    }

    private void bye() {
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
            if (s.equals("list")) {
                cindy.list();
            } else if (s.startsWith("mark")) {
                Scanner temp = new Scanner(s.substring(5));
                if (temp.hasNextInt()) {
                    cindy.mark(temp.nextInt() - 1, true);
                }
                // else exception
            } else if (s.startsWith("unmark")) {
                Scanner temp = new Scanner(s.substring(7));
                if (temp.hasNextInt()) {
                    cindy.mark(temp.nextInt() - 1, false);
                }
                // else exception
            } else if (s.equals("bye")) {
                cindy.bye();
                break;
            } else {
                cindy.add(s);
            }
            System.out.println(segLine);
        }
    }
}
