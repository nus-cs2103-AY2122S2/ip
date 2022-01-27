import exceptions.TaskException;
import java.util.Scanner;

public class Ui {

    public Ui() {
        initUi();
    }

    public void run(Scanner sc, TaskList tasks, Storage storage) {
        while (sc.hasNext()) {
            String userInput = sc.nextLine();
            try {

                Parser p = new Parser(userInput);
                p.executeCommand(tasks);
                storage.write(tasks);
            } catch (TaskException e) {
                System.out.println(e.getMessage());
            }
            printHorizontalLine();
            if (userInput.equals("bye")) break;
        }
    }

    private void initUi() {
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printHorizontalLine();
    }

    private static void printHorizontalLine() {
        System.out.println("_____________" +
                "_______________________________________________");
    }
}
