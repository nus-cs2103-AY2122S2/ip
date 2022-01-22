import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * KoroBot is a chatbot that tracks the list of tasks on hand.
 * @author Terng Yan Long
 * @version 4.0
 * @since 1.0
 */
public class Duke {
    private static final String BOT_NAME = "KoroBot";
    private static final String WELCOME_MESSAGE = "    ____________________________________________________________\n"
            + "     Hi! I'm " + BOT_NAME + "~\n"
            + "    ____________________________________________________________";
    private static final String EXIT_MESSAGE = "    ____________________________________________________________\n"
            + "     Bye! Hope to see you again soon :D\n"
            + "    ____________________________________________________________";
    private static final String DATA_FOLDER_PATH = "src/data";
    private static final String DATA_PATH = "src/data/data.txt";
    private static TaskList taskListOfTasks;

    /**
     * Greets the user by printing the default welcome message.
     */
    private static void greet() {
        System.out.println(WELCOME_MESSAGE);
    }

    /**
     * Exits the program when user inputs "bye".
     * Default exit message is printed as well.
     */
    private static void exit() {
        System.out.println(EXIT_MESSAGE);
        System.exit(0);
    }

    /**
     * Tests if the input string represents an integer value.
     *
     * @param input Target string.
     * @return true if the input string represents an integer value, and false otherwise.
     * @throws NumberFormatException thrown if the string does not represent an integer value.
     */
    private static boolean isInteger(String input) throws NumberFormatException  {
        boolean isInt = true;
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            isInt = false;
        }
        return isInt;
    }

    /**
     * Removes the last character of a string
     *
     * @param str Target String
     * @return New string after removing the last character of the original string
     */
    private static String removeLastChar(String str) {
        if (str == null || str.length() == 0) {
            return "";
        } else {
            return str.substring(0, str.length() - 1);
        }
    }

    /**
     * Calls different functions according to the user's input command.
     * Any command that is not in the correct form will be throw an "error" message.
     *
     * @param userInput Command entered by the user.
     */
    private static void parse(String userInput) throws DukeException {
        String[] wordArray = userInput.trim().split(" ");
        List<String> wordList = Arrays.asList(wordArray);
        String firstWord = wordList.get(0);

        switch (firstWord) {
        case ("list"):
            if (wordList.size() == 1) {
                taskListOfTasks.display();
            } else {
                throw new InvalidCommand("This command should not have any arguments :(");
            }
            break;
        case ("todo"):
            if (wordList.size() > 1) {
                taskListOfTasks.todo(userInput.substring(5));
            } else {
                throw new InvalidCommand("The description of a todo cannot be empty :(");
            }
            break;
        case ("deadline"):
            if (wordList.size() < 4) {
                throw new InvalidCommand("Insufficient arguments supplied :(");
            } else if (!wordList.contains("/by")) {
                throw new InvalidCommand("Formatting error detected. " +
                        "Please follow this format: deadline <description> /by <date/time>");
            } else {
                int separator = wordList.indexOf("/by");
                String desc = "";
                String dateTime = "";
                for (int i = 1; i < separator; i++) {
                    desc += wordList.get(i);
                    desc += " ";
                }
                for (int i = separator + 1; i < wordList.size(); i++) {
                    dateTime += wordList.get(i);
                    dateTime += " ";
                }
                taskListOfTasks.deadline(removeLastChar(desc), removeLastChar(dateTime));
            }
            break;
        case ("event"):
            if (wordList.size() < 4) {
                throw new InvalidCommand("Insufficient arguments supplied :(");
            } else if (!wordList.contains("/at")) {
                throw new InvalidCommand("Formatting error detected. " +
                        "Please follow this format: event <description> /at <date/time>");
            } else {
                int separator = wordList.indexOf("/at");
                String desc = "";
                String dateTime = "";
                for (int i = 1; i < separator; i++) {
                    desc += wordList.get(i);
                    desc += " ";
                }
                for (int i = separator + 1; i < wordList.size(); i++) {
                    dateTime += wordList.get(i);
                    dateTime += " ";
                }
                taskListOfTasks.event(removeLastChar(desc), removeLastChar(dateTime));
            }
            break;
        case("mark"):
            if (wordList.size() != 2) {
                throw new InvalidCommand("This command should have exactly 1 argument.");
            } else if (!isInteger(wordList.get(1))) {
                throw new InvalidCommand("The argument MUST contain a single integer.");
            } else {
                int currTaskId = Integer.parseInt(wordList.get(1));
                if (currTaskId > 0 & currTaskId <= taskListOfTasks.getNumberOfTasks()) {
                    taskListOfTasks.mark(currTaskId); // Valid taskID, proceed to mark task
                } else {
                    throw new InvalidIndex("The specified task ID is out of range. " +
                            "Please enter a number from 0 to " + taskListOfTasks.getNumberOfTasks() + ".");
                }
            }
            break;
        case("unmark"):
            if (wordList.size() != 2) {
                throw new InvalidCommand("This command should have exactly 1 argument");
            } else if (!isInteger(wordList.get(1))) {
                throw new InvalidCommand("The argument MUST contain a single integer.");
            } else {
                int currTaskId = Integer.parseInt(wordList.get(1));
                if (currTaskId > 0 & currTaskId <= taskListOfTasks.getNumberOfTasks()) {
                    taskListOfTasks.unmark(currTaskId); // Valid taskID, proceed to unmark task
                } else {
                    throw new InvalidIndex("The specified task ID is out of range. " +
                            "Please enter a number from 0 to " + taskListOfTasks.getNumberOfTasks() + ".");
                }
            }
            break;
        case("delete"):
            if (wordList.size() != 2) {
                throw new InvalidCommand("This command should have exactly 1 argument");
            } else if (!isInteger(wordList.get(1))) {
                throw new InvalidCommand("The argument MUST contain a single integer.");
            } else {
                int currTaskId = Integer.parseInt(wordList.get(1));
                if (currTaskId > 0 & currTaskId <= taskListOfTasks.getNumberOfTasks()) {
                    taskListOfTasks.delete(currTaskId); // Valid taskID, proceed to unmark task
                } else {
                    throw new InvalidIndex("The specified task ID is out of range. " +
                            "Please enter a number from 0 to " + taskListOfTasks.getNumberOfTasks() + ".");
                }
            }
            break;
        default:
            throw new InvalidCommand("I'm sorry, but I don't know what that means :(");
        }
    }

    /**
     * Creates new data file/folder if it does not exists.
     *
     * @throws IOException Failed to create data file/folder in the correct location
     */
    public static void initTaskList() throws IOException {
        // Check if folder exist
        File dataFolder = new File(DATA_FOLDER_PATH);
        // Create new folder if it does not exist
        if (!dataFolder.isDirectory()) {
            System.out.println("    Data folder not found... Creating folder now...");
            boolean isSuccess = dataFolder.mkdirs();
            if (isSuccess) {
                System.out.println("    Success in creating folder!");
            } else {
                throw new IOException("    Failed to create folder.\n"
                        + "    Please create a folder named 'data' in src manually"
                        + "    , before running this program!");
            }
        }

        // Check if file exists
        File dataFile = new File(DATA_PATH);
        // Create new data.txt file if it does not exist
        if (!dataFile.isFile()) {
            System.out.println("    Data file not found... Creating data file now...");
            boolean isSuccess = dataFile.createNewFile();
            if (isSuccess) {
                System.out.println("    Success in creating data file!");
            } else {
                throw new IOException("    Failed to create datafile.\n"
                        + "    Please create a data.txt in src/data manually"
                        + "    , before running this program!");
            }
        }
    }

    private static void loadData() throws FileNotFoundException {
        File dataFile = new File(DATA_PATH);
        Scanner s = new Scanner(dataFile);
        System.out.println("    Here are the records in the hard disk:");
        while (s.hasNext()) {
            System.out.println("    " + s.nextLine());
        }
    }
    public static void main(String[] args) {
        greet();
        try {
            initTaskList();
        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            loadData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("    ____________________________________________________________\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n");
        taskListOfTasks = new TaskList(100); // Assume there will be no more than 100 tasks
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            try {
                parse(userInput);
            } catch (DukeException e) {
                System.out.println(e);
            } finally {
                userInput = sc.nextLine();
            }
        }
        sc.close();
        exit();
    }
}
