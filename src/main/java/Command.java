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
            else {
                taskList.tasks.add(command);
                System.out.println("added: " + command);
            }
            Command nextCommand = new Command(scan,scan.nextLine());
            nextCommand.execute(taskList);
        }
    }
}
