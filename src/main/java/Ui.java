import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public Ui() {

    }

    public String readCommand() throws DukeException {
        String response = "";
        try {
            System.out.println("Enter command here: ");
            response = reader.readLine().trim();
        } catch (IOException e) {
            throw new DukeException("Invalid user input. Please try again.");
        } catch (NullPointerException e) {
            throw new DukeException("CTRL + C maybe? How did you know of this hack? bye!");
        }

        return response;
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showWelcome() {
        String welcome = "Hello! I'm Duke. Here is a list of commands for your reference!\n\n";
        System.out.println(welcome);
        showCommands();
    }

    public void showCommands() {
        StringBuilder welcome = new StringBuilder(
                "1. todo [task]\n");
        welcome.append("2. deadline [task] /by [date]\n");
        welcome.append("3. event [task] /at [location]\n");
        welcome.append("4. list\n");
        welcome.append("5. mark X (mark X task as done)\n");
        welcome.append("6. unmark X (mark X task as undone)\n");
        welcome.append("7. delete X (delete X task from the list)\n");
        welcome.append("8. bye - exit Duke bot\n");
        System.out.println(welcome.toString());
    }

    public void showListOfTasksMessage(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println("You have 0 task at the moment. Start by adding these commands:\n");
            showCommands();
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + ". " + tasks.get(i).toString());
            }
        }
    }

    public void showResponseMessage(String res) {
        switch (res) {
            case "mark":
                System.out.println("Nice! I've marked this task as done:");
                break;
            case "unmark":
                System.out.println("OK, I've marked this task as not done yet:");
                break;
            case "delete":
                System.out.println("Noted. I've removed this task:");
                break;
            case "todo":
            case "deadline":
            case "event":
                System.out.println("Got it. I've added this task:");
                break;
        }
    }

    public void showIncorrectMessage() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :(");
    }

    public void showTerminatingMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        System.out.println("Cache conversation not detected. Creating a new session with Duke bot.");
    }

    public void printTasksCountMessage(TaskList tasks) {
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    public void showInvalidRange() {
        System.out.println("Invalid number. Try again!");
    }

    public void showTaskMessage(Task task) {
        System.out.println(task.toString());
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
