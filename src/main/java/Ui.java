import java.util.List;
import java.util.Scanner;

public class Ui {
    Scanner reader = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("Hello, traveller! My name in Paimon.\nHow can I help you today?");
    }

    public void showGoodbye() {
        System.out.println("Bye, hope to see you again soon!");
        reader.close();
    }

    public String readInput() {
        System.out.print("| \r");
        return reader.nextLine();
    }

    public void showLoadingError() {
        System.out.println("Oh no, an error occurred with processing the data file :c");
    }

    public void showError(String s) {
        System.out.println("Oh no, the following error occurred while running the program:");
        System.out.println(s);
    }

    public void showCommandMessage(String command, TaskList tasks) {
        switch (command) {
        case "list":
            System.out.println("Hmm... Paimon keeps a clear record in her diary.");
            System.out.println(tasks);
            break;
        case "do":
            System.out.println("Task successfully updated.");
            break;
        case "undo":
            System.out.println("Task successfully updated.");
            break;
        case "delete":
            System.out.println("Noted, the task has been scrubbed off the list!");
            System.out.println(tasks);
            break;
        case "todo":
            System.out.println("Got it! I have noted down the following task in your list.");
            break;
        case "deadline":
            System.out.println("Got it! I have noted down the following task in your list. " +
                    "\nRemember the deadline!");
            break;
        case "event":
            System.out.println("Got it! I have noted down the following task in your list. " +
                    "\nDo be there on time!");
            break;
        case "bye":
            showGoodbye();
            break;
        default:
            System.out.println("That went over Paimon's head a little...");
        }
    }
}
