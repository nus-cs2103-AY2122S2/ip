import java.util.Scanner;

public class Command {
    protected Scanner scan;
    protected String command;

    public Command(Scanner scan, String command) {
        this.scan = scan;
        this.command = command;
    }

    public void execute(TaskList taskList) {
        if (command.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
        else {
            if (command.equals("list")) {
                taskList.listTasks();
            }
            else if (command.equals("mark")) {
                taskList.markTask(scan.nextInt());
            }
            else if (command.equals("unmark")) {
                taskList.unmarkTask(scan.nextInt());
            }
            else {
                String remainingCommand = scan.nextLine();
                taskList.tasks.add(new Task(command + remainingCommand));
                System.out.println("added: " + command + remainingCommand);
            }
            Command nextCommand = new Command(scan,scan.next());
            nextCommand.execute(taskList);
        }
    }
}
