import java.util.Scanner;

public class Ui {

    public Ui() {
        this.showWelcome();
    }

    private void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n");
    }

    public String readCommand() {
        System.out.println("What can I do for you next?");
        Scanner userInput = new Scanner(System.in);
        return userInput.nextLine();
    }

    public void showLoadingError() {
        System.out.println("I couldn't find any task list data to load, so I've created an empty task list.");
    }

    public void showLoadingSuccess(TaskList taskList) {
        if (taskList.getLength() != 0) {
            System.out.println("I've retrieved your latest task list data");
            System.out.println(taskList);
        }
    }
}
