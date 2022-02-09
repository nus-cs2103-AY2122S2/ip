package duke;

import java.util.Scanner;

/**
 * Represents a parser that can parse the commands sent to Duke and execute.
 */
public class Parser {
    private static final String SEG_LINE = "    ____________________________________________________________";
    private static final String INDENT = "    ";

    private final Scanner sc;
    private final TaskList taskList;

    /**
     * Class constructor.
     *
     * @param sc user input.
     * @param taskList taskList to call commands on.
     */
    public Parser(Scanner sc, TaskList taskList) {
        this.sc = sc;
        this.taskList = taskList;
    }

    /**
     * Waits for user commands, interprets them, and takes actions accordingly.
     */
    public void parse() {
        while (true) {
            try {
                String command = sc.nextLine();
                System.out.println(SEG_LINE);
                if (command.startsWith("list")) {
                    taskList.list();
                } else if (command.startsWith("todo ")) {
                    taskList.addTodo(command.substring(5));
                } else if (command.startsWith("deadline ")) {
                    taskList.addDdl(command.substring(9));
                } else if (command.startsWith("event ")) {
                    taskList.addEvt(command.substring(6));
                } else if (command.startsWith("mark ")) {
                    taskList.mark(command.substring(5), true);
                } else if (command.startsWith("unmark ")) {
                    taskList.mark(command.substring(7), false);
                } else if (command.startsWith("delete ")) {
                    taskList.delete(command.substring(7));
                } else if (command.startsWith("find ")) {
                    taskList.find(command.substring(5));
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
