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
    private final TaskList tasks;

    private Duke(String name) {
        this.name = name;
        this.tasks = new TaskList();
    }

    private void run() {
        System.out.println(logo);
        System.out.println(segLine);
        System.out.println(indent + "Hello! I'm " + name + ".");
        System.out.println(indent + "What can I do for you?");
        System.out.println(segLine);

        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String s = sc.nextLine();
                System.out.println(segLine);
                if (s.startsWith("list")) {
                    tasks.list();
                } else if (s.startsWith("todo")) {
                    if (s.length() <= 5) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    tasks.addTodo(s.substring(5));
                } else if (s.startsWith("deadline")) {
                    if (s.length() <= 9) {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    }
                    tasks.addDdl(s.substring(9));
                } else if (s.startsWith("event")) {
                    if (s.length() <= 6) {
                        throw new DukeException("The description of an event cannot be empty.");
                    }
                    tasks.addEvt(s.substring(6));
                } else if (s.startsWith("mark")) {
                    Scanner temp = new Scanner(s.substring(4));
                    if (temp.hasNextInt()) {
                        tasks.mark(temp.nextInt() - 1, true);
                    } else {
                        throw new DukeException("Please enter an index to mark.");
                    }
                } else if (s.startsWith("unmark")) {
                    Scanner temp = new Scanner(s.substring(6));
                    if (temp.hasNextInt()) {
                        tasks.mark(temp.nextInt() - 1, false);
                    } else {
                        throw new DukeException("Please enter an index to unmark.");
                    }
                } else if (s.startsWith("delete")) {
                    Scanner temp = new Scanner(s.substring(6));
                    if (temp.hasNextInt()) {
                        tasks.delete(temp.nextInt() - 1);
                    } else {
                        throw new DukeException("Please enter an index to delete.");
                    }
                } else if (s.startsWith("bye")) {
                    System.out.println(indent + byePhrase);
                    break;
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            }
            catch (DukeException e) {
                System.out.println(indent + e);
            }
            finally {
                System.out.println(segLine);
            }
        }
    }

    public static void main(String[] args) {
        Duke cindy = new Duke("Duke-Cindy");
        cindy.run();
    }
}
