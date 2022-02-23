package duke.ui;

import duke.tasks.Todo;
import duke.tasks.WordList;
import duke.tasks.WordListItem;

import java.util.Scanner;

/**
 * the UI handling the frontside of the app.
 * DukeUI handles the receiving of input and displaying of output.
 */
public class DukeUI {
    private Scanner scanner;

    /**
     * Constructor for DukeUI. Accepts a scanner.
     * @param scanner a scanner to receive inputs.
     */
    public DukeUI(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Wait and returns the input received by the UI.
     * @return a string from the line of input.
     */
    public String waitForinput() {
        return scanner.nextLine();
    }

    /**
     * Display the object given to the UI.
     * @param object any object to be displayed.
     */
    public void display(Object object) {
        System.out.println(object);
    }

    /**
     * Display the tasks found with the matching keyword in the list of tasks.
     * @param wordListItems array of list of tasks
     * @param keyword the keyword
     */
    public void displayFoundItem(WordListItem[] wordListItems, String keyword) {
        System.out.println("  ------------------------------------");
        System.out.println("  Here are the tasks matching '" + keyword + "':");
        for (WordListItem wordListItem: wordListItems) {
            System.out.println("    " + wordListItem);
        }
        System.out.println("  You have " + wordListItems.length + " matching results.");
        System.out.println("  ------------------------------------");
    }

    /**
     * Echo a task when added to the wordlist.
     * @param wordListItem - the task
     * @param wordList - the wordList
     */
    public void echoAddedItem(WordListItem wordListItem, WordList wordList) {
        System.out.println("  ------------------------------------");
        System.out.println("  Got it. I've added this task: ");
        System.out.println("    " + wordListItem);
        System.out.println("  You currently have " + wordList.length() + " tasks in your list");
        System.out.println("  ------------------------------------");
    }

    /**
     * Echo a task when removed from the wordlist.
     * @param wordListItem - the task
     * @param wordList - the wordList
     */
    public void echoRemovedItem(WordListItem wordListItem, WordList wordList) {
        System.out.println("  ------------------------------------");
        System.out.println("  Noted. I've removed this task: ");
        System.out.println("    " + wordListItem);
        System.out.println("  You currently have " + wordList.length() + " tasks in your list");
        System.out.println("  ------------------------------------");
    }

    /**
     * Echo a welcome message when the app is initiated.
     */
    public void replyWelcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Echo an error message when an exception occurs.
     * @param e the error message
     */
    public void replyError(Exception e) {
        System.out.println("Oh no! Duke is encountering a problem :(");
        System.out.println(e.getMessage());
    }

    /**
     * Echo a goodbye message when the app is closed.
     */
    public void replyBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
