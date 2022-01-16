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
    public void command(String userCommand) {
        border();
        if (userCommand.equals("bye")) {
            bye();
        } else if (userCommand.equals("list")) {
            list();
        } else {
            addTask(userCommand);
        }
        border();
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
            String header = Integer.valueOf(index).toString() + ". ";
            System.out.println(header + task.getTaskName());
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
            sana.command(input);
            if (input.equals("bye")) {
                break;
            }
        }
    }
}
