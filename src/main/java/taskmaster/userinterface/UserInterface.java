package taskmaster.userinterface;

import taskmaster.util.TaskList;
import taskmaster.util.Storage;

import taskmaster.commands.AddCommands;
import taskmaster.commands.DeleteCommands;
import taskmaster.commands.MarkCommands;

import taskmaster.exception.DukeExceptions;

import java.util.Scanner;

/**
 * This class encapsulates the UserInterface which interacts with
 * the user. The user will communicate with Taskmaster through
 * this class.
 */

public class UserInterface {
    /** Task list containing all the tasks. **/
    protected TaskList taskList;

    /** Storage which handles reading and writing of files. **/
    protected Storage storage;

    /** Taskmaster's logo. **/
    protected final String LOGO = "  _____\n" + " /     \\\n" + "| () () |\n" + " \\  ^  /\n"
                                    + "  |||||\n" + "  |||||\n";

    /**
     * Constructor for UserInterface.
     */

    public UserInterface() {
        this.taskList = new TaskList();
        this.storage = new Storage();
    }

    /**
     * Prints the opening message when the program runs.
     */

    private void displayOpeningMessage() {
        System.out.println(LOGO);
        System.out.println("Greetings, I'm Taskmaster, I'm super grumpy 24/7");
        System.out.println("Okay, what do you want?\n");
    }

    /**
     * Prints the bye message displayed when the user exits.
     */

    private void displayByeMessage() {
        System.out.println("See you around kiddo, I'm an angsty dude but deep down i'm a lonely man");
    }

    /**
     * Loads the text file at the startup of the program.
     * Adds all the tasks in the text file into the Task List.
     */

    private void loadExistingFile() {
        System.out.println("Loading up saved task files ...\n");
        storage.loadFile(taskList);
    }

    /**
     * Updates the list of tasks in the text file when the
     * program exits.
     */

    private void updateList() {
        storage.updateList(taskList);
    }

    /**
     * Method that returns True if the exit command "bye"
     * is entered by the user.
     *
     * @param userInput The input entered by the user.
     * @return True if input is "bye" and false otherwise.
     */

    private boolean ifBye(String userInput) {
        return userInput.equals("bye");
    }

    /**
     * Display Message when the user enters an invalid command.
     * @param input The input entered by the user
     */

    public void displayInvalidCommand(String input) {
        System.out.println("\n" + input + "?");
        System.out.println("What are you on about?");
        System.out.println("Type 'help' if you want to know the commands, kid!\n");
    }

    /**
     * Prints the list of commands that are in this program.
     */

    public void displayListOfCommand() {
        System.out.println("____________________________________________________________");
        System.out.println("\nCommands: ");
        System.out.println("    List                                    -List out all your current tasks");
        System.out.println("    todo <task name>                        -Add a todo task without any deadline"
                            + " specified");
        System.out.println("    deadline <task name> /by <Date><Time>   -Adds a task that has to be done before "
                            + "the specified deadline");
        System.out.println("    event <task name> /at <Date><Time>      -Adds a task that occurs at the specified "
                            + "time and date");
        System.out.println("    mark <task number>                      -Marks task as completed");
        System.out.println("    unmark <task number>                    -Marks a completed task as uncompleted");
        System.out.println("    delete <task number>                    -Deletes the task at the specified index");
        System.out.println("    bye                                     -Exits the program");
        System.out.println("    find <keyword>                          -Retrieves all tasks that contains the "
                            + "specified keyword");
        System.out.println("____________________________________________________________\n");
    }


    /**
     * Method to help user perform the task that has been
     * entered based on the input.
     *
     * @param input Input the user entered.
     */

    public void performCommand(String input) {
        String[] stringIntoParts = input.split(" ");
        String firstWord = stringIntoParts[0];

        switch (firstWord) {
            case "list":
                taskList.list();
                break;

            case "mark": case "unmark":
                MarkCommands markCommand = new MarkCommands(input,taskList);
                markCommand.execute();
                break;

            case "delete":
                DeleteCommands deleteCommand = new DeleteCommands(input,taskList);
                deleteCommand.execute();
                break;

            case "todo":  case "deadline": case "event":
                AddCommands addCommand = new AddCommands(input, taskList);
                addCommand.execute();
                break;

            case "find":
                try {
                    if (stringIntoParts.length == 1) {
                        throw new DukeExceptions("ERROR: find command requires a parameter to specify"
                                                    + " what keyword to find");
                    }

                    String toFind = input.substring(input.indexOf(" ") + 1);
                    taskList.find(toFind);

                } catch (DukeExceptions e) {
                    System.out.println(e.getMessage());
                }
                break;

            case "bye":
                return;

            case "help":
                displayListOfCommand();
                break;

            default:
                displayInvalidCommand(input);
                break;
        }

    }

    /**
     * Serves as the interface between the user and the program.
     */

    private void displayMenu() {
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String userInput = sc.nextLine();
            performCommand(userInput);
            isExit = ifBye(userInput);
        }
        sc.close();
    }

    /**
     * Method that starts the Taskmaster program.
     */

    public void runChatBot() {
            displayOpeningMessage();
            loadExistingFile();
            displayMenu();
            displayByeMessage();
            updateList();
    }


}
