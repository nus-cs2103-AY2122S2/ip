package duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * Ui is a class that the user interacts with on the CLI
 */
public class Ui {

    TaskList tasks;

    Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    enum Commands {
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete");

        final String command;

        Commands(String command) {
            this.command = command;
        }
    }

    /**
     * This method will handle user input from the CLI, it has multiple commands such as
     * list, mark, unmark, todo, deadline, event, delete, bye
     *
     * @throws DukeException
     * @throws IOException
     */
    void run() throws DukeException, IOException {
        Scanner sc = new Scanner(System.in);
        String intro = "Hello! I'm duke.Duke\n" +
                "What can I do for you?";
        System.out.println(intro);

        String cmd = sc.next();
        Parser parser = new Parser(tasks);

        while (!cmd.equals("bye")) {
            if (cmd.equals(Ui.Commands.LIST.command)) {
                parser.listTasks();

            } else if (cmd.equals(Ui.Commands.MARK.command)) {
                int taskNumber = 0;
                taskNumber = sc.nextInt();
                parser.mark(taskNumber);

            } else if (cmd.equals(Ui.Commands.UNMARK.command)) {
                int taskNumber = 0;
                taskNumber = sc.nextInt();
                parser.unmark(taskNumber);

            } else if (cmd.equals(Ui.Commands.TODO.command)) {
                String description = sc.nextLine();
                if (description.length() == 0) {
                    throw new DukeException("OOPS!!! The description of " +
                            "a todo cannot be empty.");
                }
                ToDo todo = new ToDo(description);
                parser.addToDo(todo);

            } else if (cmd.equals(Ui.Commands.DEADLINE.command)) {
                String info = sc.nextLine();
                String[] split = info.split(" /by ");
                String description = split[0];
                String time = split[1];
                Deadline deadline = new Deadline(description, time);
                parser.addDeadline(deadline);

            } else if (cmd.equals(Ui.Commands.EVENT.command)) {
                String info = sc.nextLine();
                String[] split = info.split(" /at ");
                String description = split[0];
                String time = split[1];
                Event event = new Event(description, time);
                parser.addEvent(event);

            } else if (cmd.equals(Ui.Commands.DELETE.command)) {
                int taskNumber = sc.nextInt();
                parser.deleteTask(taskNumber);

            } else {
                throw new DukeException("OOPS!!! I'm sorry, " +
                        "but I don't know what that means :-(");
            }
            Storage.saveToFile(tasks);
            cmd = sc.next();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }


}
