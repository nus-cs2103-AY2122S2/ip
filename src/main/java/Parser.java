import java.util.Scanner;

public class Parser {
    private static final String SEG_LINE = "    ____________________________________________________________";
    private static final String INDENT = "    ";

    private final Scanner sc;
    private final TaskList taskList;

    public Parser(Scanner sc, TaskList taskList) {
        this.sc = sc;
        this.taskList = taskList;
    }

    public void parse() {
        while (true) {
            try {
                String command = sc.nextLine();
                System.out.println(SEG_LINE);
                if (command.startsWith("list")) {
                    taskList.list();
                } else if (command.startsWith("todo")) {
                    if (command.length() <= 5) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    taskList.addTodo(command.substring(5));
                } else if (command.startsWith("deadline")) {
                    if (command.length() <= 9) {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    }
                    taskList.addDdl(command.substring(9));
                } else if (command.startsWith("event")) {
                    if (command.length() <= 6) {
                        throw new DukeException("The description of an event cannot be empty.");
                    }
                    taskList.addEvt(command.substring(6));
                } else if (command.startsWith("mark")) {
                    Scanner temp = new Scanner(command.substring(4));
                    if (temp.hasNextInt()) {
                        taskList.mark(temp.nextInt() - 1, true);
                    } else {
                        throw new DukeException("Please enter an index to mark.");
                    }
                } else if (command.startsWith("unmark")) {
                    Scanner temp = new Scanner(command.substring(6));
                    if (temp.hasNextInt()) {
                        taskList.mark(temp.nextInt() - 1, false);
                    } else {
                        throw new DukeException("Please enter an index to unmark.");
                    }
                } else if (command.startsWith("delete")) {
                    Scanner temp = new Scanner(command.substring(6));
                    if (temp.hasNextInt()) {
                        taskList.delete(temp.nextInt() - 1);
                    } else {
                        throw new DukeException("Please enter an index to delete.");
                    }
                } else if (command.startsWith("bye")) {
                    System.out.println(INDENT + "Bye. Hope to see you again soon!");
                    break;
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(INDENT + e);
            } finally {
                System.out.println(SEG_LINE);
            }
        }
    }
}
