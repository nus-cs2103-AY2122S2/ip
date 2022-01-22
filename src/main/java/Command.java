import java.io.IOException;
import java.util.Scanner;

public class Command {
    protected Scanner scan;
    protected String command;

    public Command(Scanner scan, String command) {
        this.scan = scan;
        this.command = command;
    }

    public void execute(TaskList taskList) throws DukeException {
        try {
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else if (command.equals("list")) {
                taskList.listTasks();
            } else if (command.equals("mark")) {
                taskList.markTask(scan.nextInt());
            } else if (command.equals("unmark")) {
                taskList.unmarkTask(scan.nextInt());
            } else if (command.equals("delete")) {
                taskList.deleteTask(scan.nextInt());
            } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                String description = scan.nextLine();
                if (description.equals("")) {
                    throw new DukeException(String.format("OOPS!!! The description of a %s cannot be empty.", command), taskList);
                }
                taskList.addTask(command, description);
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(", taskList);
            }
        }
        catch (DukeException | IOException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (!command.equals("bye")) {
                Command nextCommand = new Command(scan, scan.next());
                nextCommand.execute(taskList);
            }
        }
    }
}
