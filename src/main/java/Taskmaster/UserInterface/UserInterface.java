package Taskmaster.UserInterface;
import Taskmaster.util.TaskList;
import Taskmaster.util.Storage;
import Taskmaster.Commands.AddCommands;
import Taskmaster.Commands.DeleteCommands;
import Taskmaster.Commands.MarkCommands;
import Taskmaster.Exception.DukeExceptions;


import java.util.Scanner;
public class UserInterface {
    protected TaskList tasklist;
    protected Storage storage;
    protected final String LOGO = "  _____\n" + " /     \\\n" + "| () () |\n" + " \\  ^  /\n" + "  |||||\n" + "  |||||\n";

    public UserInterface() {
        this.tasklist = new TaskList();
        this.storage = new Storage();
    }

    /**
     * The opening message we see when the program runs
     *
     */
    private void openingMessage() {
        System.out.println(LOGO);
        System.out.println("Greetings, I'm Taskmaster, I'm super grumpy 24/7");
        System.out.println("Okay, what do you want?\n");
    }

    /**
     * The bye message displayed when the user exits
     *
     */

    private void byeMessage() {
        System.out.println("See you around kiddo, I'm an angsty dude but deep down i'm a lonely man");
    }

    private void loadingExistingFile() {
        System.out.println("Loading up saved task files ...\n");
        storage.loadFile(tasklist);
    }

    private void updateList() {
        storage.updateList(tasklist);
    }

    private boolean ifBye(String userInput) {
        return userInput.equals("bye");
    }

    //Default case when user enters wrong command
    public void invalidCommand(String input) {
        System.out.println("\n" + input + "?");
        System.out.println("What are you on about?");
        System.out.println("Type list if you want to know the commands, kid!\n");
    }

    //Contains the list of commands when user enters help
    public void listOfCommand() {
        System.out.println("____________________________________________________________");
        System.out.println("\nCommands: ");
        System.out.println("    List                                    -list out all your current tasks");
        System.out.println("    todo <task name>                        -Add a todo task without any deadline specified");
        System.out.println("    deadline <task name> /by <deadline>     -Adds a task that has to be done before the specified deadline");
        System.out.println("    event <task name> /at <deadline>        -Adds a task that occurs at the specified deadline");
        System.out.println("    mark <task number>                      -marks task as completed");
        System.out.println("    unmark <task number>                    -marks a completed task as uncompleted");
        System.out.println("    bye                                     -exits the program :'( ");
        System.out.println("____________________________________________________________\n");
    }


    /**
     * Function that works as a switch for user's input
     *
     * @param tasklist - Tasklist that is created by collating all the tasks
     */

    public void performCommand(String input) {

        //Splits up the string to identify the main command
        String splitString[] = input.split(" ");
        String firstWord = splitString[0];

        //Case "List"
        switch (firstWord) {
            case "list":
                tasklist.list();
                break;

            case "mark": case "unmark":
                MarkCommands markCommand = new MarkCommands(input,tasklist);
                markCommand.execute();
                break;

            case "delete":
                DeleteCommands deleteCommand = new DeleteCommands(input,tasklist);
                deleteCommand.execute();
                break;

            case "todo":  case "deadline": case "event":
                AddCommands addCommand = new AddCommands(input, tasklist);
                addCommand.execute();
                break;

            case "find":
                try {
                    if (splitString.length == 1)
                        throw new DukeExceptions("ERROR: find command requires a parameter to specify what keyword to find");
                    String toFind = input.substring(input.indexOf(" ") + 1);
                    tasklist.find(toFind);
                } catch (DukeExceptions e) {
                    System.out.println(e.getMessage());
                }
                break;

            case "bye":
                return;

            case "help":
                listOfCommand();
                break;

            //Invalid command case
            default:
                invalidCommand(input);
                break;
        }

    }

    private void menu() {
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String userInput = sc.nextLine();
            performCommand(userInput);
            isExit = ifBye(userInput);
        }
        sc.close();
    }

    public void runChatBot() {
            openingMessage();
            loadingExistingFile();
            menu();
            byeMessage();
            updateList();
    }


}
