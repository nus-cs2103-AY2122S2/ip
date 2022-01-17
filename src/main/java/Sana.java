import java.util.*; // Import java utils

/**
 * Sana is a BIG program!
 *
 * @author  Jan Alfenson Tan
 * @version 1.2
 */
public class Sana {
    // The border for Sana's replies
    private static final String border = "_____________________________________________";

    /**
     * userCommands stores the commands given to Sana from the user
     */
    private LinkedList<Task> userCommands;

    /**
     * Constructor for the Sana class
     */
    public Sana() {
        this.userCommands = new LinkedList<>();
    }

    /**
     * This method prints to system IO Sana's greeting
     */
    public void greet() {
        border();
        System.out.println("Hi! I'm BEEEEEEEG\nWhats up?");
        border();
    }

    /**
     * This method takes in the user's command and calls the respective Sana commands
     *
     * @param userCommand   the user command
     */
    public void commandParser(String userCommand) {
        border();
        if (userCommand.equals("bye")) {
            bye();
        } else if (userCommand.equals("list")) {
            list();
        } else if (userCommand.startsWith("mark")) {
            int taskIndex = Integer.parseInt(userCommand.split(" ", 2)[1]) - 1;
            mark(taskIndex, true);
        } else if (userCommand.startsWith("unmark")) {
            int taskIndex = Integer.parseInt(userCommand.split(" ", 2)[1]) - 1;
            mark(taskIndex, false);
        } else {
            addTask(userCommand);
        }
        border();
    }

    /**
     * This method marks the task located at the index as done or not done
     *
     * @param taskIndex     the index of the task to be marked done
     * @param completion    the completion of the task
     */
    private void mark(int taskIndex, boolean completion) {
        userCommands.get(taskIndex).setDone(completion);
        if (completion) {
            System.out.println("You've done it! Well done!");
        } else {
            System.out.println("Oopsies! I'll change it back!");
        }
        System.out.println(userCommands.get(taskIndex));
    }

    /**
     * Adds given task to the list of tasks given to Sana.
     *
     * @param taskName  name of task
     */
    private void addTask(String taskName) {
        userCommands.add(new Task(taskName));
        System.out.println("added: " + taskName);
    }

    /**
     * This method lists the history of user inputs to Sana
     */
    private void list() {
        int index = 1;
        for (Task task : userCommands) {
            String header = Integer.valueOf(index).toString() + ".";
            System.out.println(header + task);
            index++;
        }
    }

    /**
     * This method prints to system IO Sana's bye
     */
    private void bye() {
        System.out.println("See you next time!");
    }

    /**
     * This method prints the border
     */
    private void border() {
        System.out.println(border);
    }

    public static void main(String[] args) {
        Sana sana = new Sana();
        sana.greet();

        Scanner userInput = new Scanner(System.in);
        while (true) {
            String input = userInput.nextLine();
            sana.commandParser(input);
            if (input.equals("bye")) {
                break;
            }
        }
    }
}
