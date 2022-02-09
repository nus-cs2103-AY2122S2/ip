import java.util.Scanner;

public class DukeUI {
    private Scanner scanner;
    private WordList wordList;

    public DukeUI(Scanner scanner) {
        this.scanner = scanner;
    }

    public String waitForinput() {
        return scanner.nextLine();
    }

    public void replyWelcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void replyError(Exception e) {
        System.out.println("Oh no! Duke is encountering a problem :(");
        System.out.println(e.getMessage());
    }

    public void replyBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
