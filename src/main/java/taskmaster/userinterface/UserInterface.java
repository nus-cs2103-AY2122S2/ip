package taskmaster.userinterface;

import java.util.Scanner;

import taskmaster.commands.AddCommands;
import taskmaster.commands.ByeCommands;
import taskmaster.commands.Commands;
import taskmaster.commands.DefaultCommands;
import taskmaster.commands.DeleteCommands;
import taskmaster.commands.FindCommands;
import taskmaster.commands.HelpCommands;
import taskmaster.commands.ListCommands;
import taskmaster.commands.MarkCommands;

import taskmaster.exception.DukeExceptions;
import taskmaster.util.Storage;
import taskmaster.util.TaskList;

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
    protected String logo = "  _____\n" + " /     \\\n" + "| () () |\n" + " \\  ^  /\n"
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
        System.out.println(logo);
        System.out.println("Greetings, I'm Taskmaster, I'm super grumpy 24/7");
        System.out.println("Okay, what do you want?\n");
    }

    /**
     * Prints the bye message displayed when the user exits.
     */

    public String displayByeMessage() {
        return "See you around kiddo, I'm an angsty dude but deep down i'm a lonely man";
    }

    /**
     * Loads the text file at the startup of the program.
     * Adds all the tasks in the text file into the Task List.
     */

    public void loadExistingFile() {
        System.out.println("Loading up saved task files ...\n");
        storage.loadFile(taskList);
    }

    /**
     * Updates the list of tasks in the text file when the
     * program exits.
     */

    public void updateList() {
        storage.updateList(taskList);
    }

    /**
     * Method that returns True if the exit command "bye"
     * is entered by the user.
     *
     * @param userInput The input entered by the user.
     * @return True if input is "bye" and false otherwise.
     */

    public boolean ifBye(String userInput) {
        return userInput.equals("bye");
    }

    /**
     * Display Message when the user enters an invalid command.
     * @param input The input entered by the user
     */

    public String displayInvalidCommand(String input) {
        return "\n" + input + "?"
                + "\nWhat are you on about? \n Type 'help' if "
                + "you want to know the commands, kid!\n";
    }

    /**
     * Prints the list of commands that are in this program.
     */

    public String displayListOfCommand() {
        String result = "";
        result += "____________________________________________________________\n";
        result += "\nCommands: ";
        result += "    List                                    -List out all your current tasks\n";
        result += "    todo <task name>                        -Add a todo task without any deadline"
                            + " specified\n";
        result += "    deadline <task name> /by <Date><Time>   -Adds a task that has to be done before "
                            + "the specified deadline\n";
        result += "    event <task name> /at <Date><Time>      -Adds a task that occurs at the specified "
                            + "time and date\n";
        result += "    mark <task number>                      -Marks task as completed\n";
        result += "    unmark <task number>                    -Marks a completed task as uncompleted\n";
        result += "    delete <task number>                    -Deletes the task at the specified index\n";
        result += "    bye                                     -Exits the program\n";
        result += "    find <keyword>                          -Retrieves all tasks that contains the "
                            + "specified keyword\n";
        result += "____________________________________________________________\n";
        return result;
    }


    /**
     * Method to help user perform the task that has been
     * entered based on the input.
     *
     * @param input Input the user entered.
     */

    public Commands performCommand(String input) throws DukeExceptions {
        String[] stringIntoParts = input.split(" ");
        String firstWord = stringIntoParts[0];
        switch (firstWord) {
        case "list":
            return new ListCommands();
        case "mark": case "unmark":
            return new MarkCommands(input);
        case "delete":
            return new DeleteCommands(input);
        case "todo": case "deadline": case "event":
            return new AddCommands(input);
        case "find":
            return new FindCommands(input);
        case "bye":
            storage.updateList(taskList);
            return new ByeCommands();
        case "help":
            return new HelpCommands();
        default:
            return new DefaultCommands(input);
        }

    }
    public String displayErrorMessage(String errorMsg) {
        return errorMsg;
    }
}
