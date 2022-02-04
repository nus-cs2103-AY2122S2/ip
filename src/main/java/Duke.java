import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {
    private static final String SEG_LINE = "    ____________________________________________________________";
    private static final String INDENT = "    ";
    private static final String LOGO = INDENT + " ____        _        \n"
            + INDENT + "|  _ \\ _   _| | _____ \n"
            + INDENT + "| | | | | | | |/ / _ \\\n"
            + INDENT + "| |_| | |_| |   <  __/\n"
            + INDENT + "|____/ \\__,_|_|\\_\\___|\n";

    private final String name;
    private final TaskList tasks;

    public Duke(String name, String path) throws FileNotFoundException {
        this.name = name;
        this.tasks = new TaskList(path);
    }

    public void run() {
        System.out.println(LOGO);
        System.out.println(SEG_LINE);
        System.out.println(INDENT + "Hello! I'm " + name + ".");
        System.out.println(INDENT + "What can I do for you?");
        System.out.println(SEG_LINE);

        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String s = sc.nextLine();
                System.out.println(SEG_LINE);
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
                    System.out.println(INDENT + "Bye. Hope to see you again soon!");
                    break;
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            }
            catch (DukeException e) {
                System.out.println(INDENT + e);
            }
            finally {
                System.out.println(SEG_LINE);
            }
        }
    }

    public static void main(String[] args) {
        try {
            Duke cindy = new Duke("Duke-Cindy", "../../data/duke.txt");
            cindy.run();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}








