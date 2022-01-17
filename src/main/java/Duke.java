import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner scan = new Scanner(System.in);
        Command command = new Command(scan, scan.next());
        command.execute(new TaskList());
    }
}
