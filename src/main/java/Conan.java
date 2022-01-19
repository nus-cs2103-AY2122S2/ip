/**
 * @author Saravanan Anuja Harish
 * This file contains the implementation of Conan class.
 */

import java.util.Scanner;
import java.util.ArrayList;

public class Conan {

    // GREETING variable contains the introductory greetings.
    private final static String GREETING = "Hello There, My name is Conan! \n"
            + "Hope you're doing fine today! (^_^) \n"
            + "I'm a task manager, and I can help you keep up with your tasks.\n"
            + "Now before we start, lets get acquainted! Lets start with our names!";

    // SEPARATOR helps us distinguish between user commands and the task managers comments.
    private final static String SEPARATOR = "------------------------------------------------";

    // HELLO variable is used to store Hello greeting.
    private final static String HELLO = "Hello, ";

    // NTMY variable is followed by after the username.
    private final static String NTMY = "!, Nice to meet you! (*^_^*)";

    // INITIAL_ASK stores the text to be displayed at the start of the interaction.
    private final static String INITIAL_ASK = "So, tell me what would you like to do ";

    // ASK variable asks the user for tasks.
    private final static String ASK = "Please let me know if there's anything else you would like to add, ";

    // ADDED variable stores the message displayed after the task is added.
    private final static String ADDED = ", I have added ";

    // BYE variable stores bye, which recognises user exit command.
    private final static String BYE = "BYE";

    // LIST variable store the list command
    private final static String LIST = "LIST";

    // GOODBYE variable stores a farewell message.
    private final static String GOODBYE = "Goodbye, ";

    // FAREWELL variable store the remaining goodbye message.
    private final static String FAREWELL = "\nHope I helped you complete your tasks!\n"
            + "Have a great day ahead, enjoy ! (^-^)/\n"
            + "Hope to see you next time! ";

    // MARK stores the command mark.
    private final static String MARK = "mark";

    // DONE stores the message to be printed when a task is marked done.
    private final static String DONE = "Great job, on completing this task! \\(^_^)/";

    // UNDONE stores the meesage to be printed when a task is unmarked done.
    private final static String UNDONE = "Sure, I have unmarked this task :";

    // UNMARK stores the command unmark.
    private final static String UNMARK = "unmark";

    // starting index of a list or a char in string.
    private final static int START_INDEX = 0;

    // username is an instance variable that stores the name of the user.
    private final String username;

    // tasks store the list of all tasks given by the user.
    private final ArrayList<Task> tasks;

    /**
     * Constructor returns a new Conan object.
     * returns a new Conan instance.
     */
    Conan() {

        System.out.println(SEPARATOR);
        System.out.println(GREETING);

        System.out.println(SEPARATOR);
        Scanner sc = new Scanner(System.in);
        this.username = sc.nextLine();

        System.out.println(SEPARATOR);
        System.out.println(HELLO + this.username + NTMY);
        System.out.println(INITIAL_ASK + this.username);

        this.tasks = new ArrayList<>();

        System.out.println(SEPARATOR);
    }

    /**
     * echo function echoes the message given by user.
     * @param message the message given by user.
     * @return CarryOn.NEXT to indicate the user wants to give more tasks.
     */
    private CarryOn echo(String message) {
        System.out.println(message);
        System.out.println(ASK + this.username);
        System.out.println(SEPARATOR);
        return CarryOn.NEXT;
    }

    /**
     * bye function bids user farewell and returns a trigger to end the program.
     * @return CarryOn.STOP to end the program.
     */
    private CarryOn bye() {
        System.out.println(GOODBYE + this.username + FAREWELL);
        System.out.println(SEPARATOR);
        return CarryOn.STOP;
    }

    /**
     * tell function gets the message from user and passes on to other
     *  function for a suitable response.
     * @param message the message given by user.
     * @return CarryOn.NEXT if the user wants to continue; else CarryOn.STOP.
     */
    CarryOn tell(String message) {
        System.out.println(SEPARATOR);
        // Checks if the user wants to exit.
        if (message.equalsIgnoreCase(BYE)) {
            return bye();
        }

        // Checks if the user wants to display the tasks
        if (message.equalsIgnoreCase(LIST)) {
            return displayTasks();
        }

        // check if the user message contains mark
        if (message.contains(MARK)) {
            if (message.substring(START_INDEX, MARK.length()).equalsIgnoreCase(MARK)) {
                return mark(message);
            }
        }

        // check if the user message contains unmark
        if (message.contains(UNMARK)) {
            if (message.substring(START_INDEX, UNMARK.length()).equalsIgnoreCase(UNMARK)) {
                return unmark(message);
            }
        }

        return add(message);
    }

    /**
     * add function adds a task to the list of tasks to be performed.
     * @param text the task to be added.
     * @return CarryOn.NEXT to ask what else the user wants to do.
     */
    private CarryOn add(String text) {

        Task task = new Task(text);
        tasks.add(task);
        System.out.println(this.username + ADDED + task.getTask());
        System.out.println(ASK + this.username);
        System.out.println(SEPARATOR);
        return CarryOn.NEXT;
    }

    /**
     * mark function marks a task as done.
     * @param message the user command.
     * @return CarryOn.NEXT to ask what else the user wants to do.
     */
    private CarryOn mark(String message) {
        String[] arr = message.split(" ");
        int num = Integer.valueOf(arr[1]);
        tasks.get(num - 1).markDone();
        System.out.println(DONE);
        System.out.println(tasks.get(num - 1));
        System.out.println(ASK + this.username);
        System.out.println(SEPARATOR);
        return CarryOn.NEXT;
    }

    /**
     * unmark function marks a task as not done.
     * @param message the user command.
     * @return CarryOn.NEXT to ask what else the user wants to do.
     */
    private CarryOn unmark(String message) {
        String[] arr = message.split(" ");
        int num = Integer.valueOf(arr[1]);
        tasks.get(num - 1).unMarkDone();
        System.out.println(UNDONE);
        System.out.println(tasks.get(num - 1));
        System.out.println(ASK + this.username);
        System.out.println(SEPARATOR);
        return CarryOn.NEXT;
    }

    /**
     * displayTasks shows all the tasks that are currently in the list of tasks.
     * @return CarryOn.NEXT to ask what else the user wants to do.
     */
    CarryOn displayTasks() {
        int taskNum = 1;

        for (Task task: tasks) {
            System.out.println(taskNum + ". " + task);
            taskNum += 1;
        }

        System.out.println(ASK + this.username);
        System.out.println(SEPARATOR);

        return CarryOn.NEXT;
    }

}
