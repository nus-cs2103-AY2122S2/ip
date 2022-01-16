import java.util.Scanner;

public class Command {
    protected Scanner scan;
    protected String command;

    public Command(Scanner scan, String command) {
        this.scan = scan;
        this.command = command;
    }

    public void execute() {
        if (!command.equals("bye")) {
            System.out.println(command);
            Command nextCommand = new Command(scan,scan.next());
            nextCommand.execute();
        }
        else {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}
