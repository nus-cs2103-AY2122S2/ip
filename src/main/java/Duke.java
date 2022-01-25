import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static TaskList taskList;

    public static void main(String[] args) {
        Printer.welcome();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        taskList = new TaskList();

        while (!input.equals("bye")) {
            try {
                Parser.parse(input, taskList);
            } catch (DukeException e) {
                System.out.println(e);
            } finally {
                input = scanner.nextLine();
            }
        }

        scanner.close();
        Printer.exit();
    }
}
