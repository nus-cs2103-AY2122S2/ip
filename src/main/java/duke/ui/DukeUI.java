package duke.ui;

import duke.tasks.Todo;
import duke.tasks.WordList;
import duke.tasks.WordListItem;

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

    public void echoAddedItem(WordListItem wordListItem, WordList wordList) {
        System.out.println("  ------------------------------------");
        System.out.println("  Got it. I've added this task: ");
        System.out.println("    " + wordListItem);
        System.out.println("  You currently have " + wordList.length() + " tasks in your list");
        System.out.println("  ------------------------------------");
    }

    public void echoRemovedItem(WordListItem wordListItem, WordList wordList) {
        System.out.println("  ------------------------------------");
        System.out.println("  Noted. I've removed this task: ");
        System.out.println("    " + wordListItem);
        System.out.println("  You currently have " + wordList.length() + " tasks in your list");
        System.out.println("  ------------------------------------");
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
