import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * KoroBot is a chatbot that tracks the list of tasks on hand.
 * @author Terng Yan Long
 * @version 8.0
 * @since 1.0
 */
public class Duke {
    public static final String DATA_FOLDER_PATH = "./data";
    public static final String DATA_PATH = "./data/data.txt";
    private static TaskList taskListOfTasks;

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
                    throw new InvalidCommand("Incorrect number of arguments supplied :(");
                } else if (!wordList.contains("/by")) {
                    throw new InvalidCommand("Please follow this format: deadline <task> " +
                            "/by <date in yyyy-MM-dd> <time in 24hrs format> ");
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
                    dateTime = removeLastChar(dateTime);
                    String[] dateTimeArray = dateTime.split(" ");

                    if (dateTimeArray.length > 2 || dateTimeArray.length < 1) {
                        throw new InvalidCommand("Incorrect number of arguments supplied :(");
                    }

                    // Parse user input into LocalDate/LocalTime if it is in the correct format.
                    LocalDate newDate = convertDate(dateTimeArray[0]);
                    LocalTime newTime = null;
                    if (dateTimeArray.length > 1) { // Optional time input
                       newTime = convertTime(dateTimeArray[1]);
                    }

                    // Check if date/time specified is in the present.
                    Clock cl = Clock.systemUTC();
                    LocalDate nowDate = LocalDate.now(cl);
                    LocalTime nowTime = LocalTime.now(cl);
                    if (newDate.isBefore(nowDate)) {
                        throw new InvalidDateTime("You cannot travel back in time!");
                    }
                    if (newTime != null) {
                        if (newDate.isEqual(nowDate) & newTime.isBefore(nowTime)) {
                            throw new InvalidDateTime("You cannot travel back in time!");
                        }
                    }
                    taskListOfTasks.deadline(removeLastChar(desc), newDate, newTime);
                }
                break;
            case ("event"):
                if (wordList.size() < 4) {
                    throw new InvalidCommand("Incorrect number of arguments supplied :(");
                } else if (!wordList.contains("/at")) {
                    throw new InvalidCommand("Please follow this format: event <task> " +
                            "/at <date in yyyy-MM-dd> <time in 24hrs format> ");
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
                    dateTime = removeLastChar(dateTime);
                    String[] dateTimeArray = dateTime.split(" ");

                    if (dateTimeArray.length > 3 || dateTimeArray.length < 1) {
                        throw new InvalidCommand("Incorrect number of arguments supplied :(");
                    }

                    // Parse user input into LocalDate/LocalTime if it is in the correct format.
                    LocalDate newDate = convertDate(dateTimeArray[0]);
                    LocalTime newStartTime = null;
                    LocalTime newEndTime = null;
                    if (dateTimeArray.length > 1) { // Optional start time input
                        newStartTime = convertTime(dateTimeArray[1]);
                    }
                    if (dateTimeArray.length > 2) { // Optional end time input
                        newEndTime = convertTime(dateTimeArray[2]);
                    }

                    // Check if date/time specified is in the present.
                    Clock cl = Clock.systemUTC();
                    LocalDate nowDate = LocalDate.now(cl);
                    LocalTime nowTime = LocalTime.now(cl);
                    if (newDate.isBefore(nowDate)) {
                        throw new InvalidDateTime("You cannot travel back in time!");
                    }
                    if (newStartTime != null) {
                        if (newDate.isEqual(nowDate) & newStartTime.isBefore(nowTime)) {
                            throw new InvalidDateTime("You cannot travel back in time!");
                        } else if (newEndTime != null) {
                            if (newDate.isEqual(nowDate) & newEndTime.isBefore(nowTime)) {
                                throw new InvalidDateTime("You cannot travel back in time!");
                            } else if (!newEndTime.isAfter(newStartTime)) {
                                throw new InvalidDateTime("The end time must be after the start time");
                            }
                        }
                    }
                    taskListOfTasks.event(removeLastChar(desc), newDate, newStartTime, newEndTime);
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
    private static void initTaskList() throws IOException {
        // Check if folder exist
        File dataFolder = new File(DATA_FOLDER_PATH);
        // Create new folder if it does not exist
        if (!dataFolder.isDirectory()) {
            System.out.println("     Data folder not found... Creating folder now...");
            boolean isSuccess = dataFolder.mkdirs();
            if (isSuccess) {
                System.out.println("     Success in creating folder!");
            } else {
                throw new IOException("     Failed to create folder.\n"
                        + "     Please create a folder named 'data' in src manually"
                        + "     , before running this program!");
            }
        }

        // Check if file exists
        File dataFile = new File(DATA_PATH);
        // Create new data.txt file if it does not exist
        if (!dataFile.isFile()) {
            System.out.println("     Data file not found... Creating data file now...");
            boolean isSuccess = dataFile.createNewFile();
            if (isSuccess) {
                System.out.println("     Success in creating data file!" + "\n");
            } else {
                throw new IOException("     Failed to create datafile.\n"
                        + "     Please create a data.txt in src/data manually"
                        + "     , before running this program!");
            }
        }
    }

    /**
     * Prints each line in the data file.
     *
     * @throws FileNotFoundException If file is not found.
     */
    private static void readData() throws FileNotFoundException {
        File dataFile = new File(DATA_PATH);
        Scanner s = new Scanner(dataFile);
        System.out.println("     Here are the records in the hard disk:\n");
        while (s.hasNext()) {
            System.out.println("     " + s.nextLine());
        }
    }

    /**
     * Loads data from the data.txt file in the TaskList.
     *
     * @param f File that contains the data.
     * @param listOfTasks Target TaskList to store the data.
     */
    private static void loadData(File f, TaskList listOfTasks) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String currCommand = sc.nextLine();
                if (currCommand.startsWith("[T]") & currCommand.length() > 7) { // Valid To-do task
                    boolean isMarked = currCommand.startsWith("[X]", 3);
                    String currDesc = currCommand.substring(7);
                    Task currTask = new Todo(currDesc);
                    currTask.setStatus(isMarked);
                    listOfTasks.getListOfTasks().add(currTask);
                    listOfTasks.incrementTasks();
                } else if (currCommand.startsWith("[D]")
                        & currCommand.contains("(by:")
                        & currCommand.length() >= 16) { // Valid Deadline task
                    boolean isMarked = currCommand.startsWith("[X]", 3);
                    int indexBy = currCommand.indexOf("(by:");
                    String currDesc = currCommand.substring(7, indexBy - 1);
                    String currBy = currCommand.substring(indexBy + 5, currCommand.length() - 1);

                    String[] dateTimeArray = currBy.split(" ");

                    if (dateTimeArray.length > 4 || dateTimeArray.length < 3) {
                        throw new InvalidCommand("Incorrect number of arguments supplied :(");
                    }

                    // Parse user input into LocalDate/LocalTime if it is in the correct format.
                    String currDate = dateTimeArray[0] + " " + dateTimeArray[1] + " " + dateTimeArray[2];
                    LocalDate newDate = LocalDate.parse(currDate, dateFormatter);
                    LocalTime newTime = null;
                    if (dateTimeArray.length > 3) { // Optional time input
                        newTime = LocalTime.parse(dateTimeArray[3], timeFormatter);
                    }

                    // Check if date/time specified is in the present.
                    Clock cl = Clock.systemUTC();
                    LocalDate nowDate = LocalDate.now(cl);
                    LocalTime nowTime = LocalTime.now(cl);
                    if (newDate.isBefore(nowDate)) {
                        throw new InvalidDateTime("You cannot travel back in time!");
                    }
                    if (newTime != null) {
                        if (newDate.isEqual(nowDate) & newTime.isBefore(nowTime)) {
                            throw new InvalidDateTime("You cannot travel back in time!");
                        }
                    }
                    Task currTask = new Deadline(currDesc, newDate, newTime);
                    currTask.setStatus(isMarked);
                    listOfTasks.getListOfTasks().add(currTask);
                    listOfTasks.incrementTasks();
                } else if (currCommand.startsWith("[E]")
                        & currCommand.contains("(at:")
                        & currCommand.length() >= 16) { // Valid Event task
                    boolean isMarked = currCommand.startsWith("[X]", 3);
                    int indexAt = currCommand.indexOf("(at:");
                    String currDesc = currCommand.substring(7, indexAt - 1);
                    String currAt = currCommand.substring(indexAt + 5, currCommand.length() - 1);

                    String[] dateTimeArray = currAt.split(" ");

                    if (dateTimeArray.length > 4 || dateTimeArray.length < 3) {
                        throw new InvalidCommand("Incorrect number of arguments supplied :(");
                    }

                    // Parse user input into LocalDate/LocalTime if it is in the correct format.
                    String currDate = dateTimeArray[0] + " " + dateTimeArray[1] + " " + dateTimeArray[2];
                    LocalDate newDate = LocalDate.parse(currDate, dateFormatter);
                    LocalTime newStartTime = null;
                    LocalTime newEndTime = null;
                    if (dateTimeArray.length > 3) { // Optional start time input
                        String[] startEndTime = dateTimeArray[3].split("-");
                        newStartTime = LocalTime.parse(startEndTime[0], timeFormatter);
                        if (startEndTime.length == 2) {
                            newEndTime = LocalTime.parse(startEndTime[1], timeFormatter);
                        }
                    }

                    // Check if date/time specified is in the present.
                    Clock cl = Clock.systemUTC();
                    LocalDate nowDate = LocalDate.now(cl);
                    LocalTime nowTime = LocalTime.now(cl);
                    if (newDate.isBefore(nowDate)) {
                        throw new InvalidDateTime("You cannot travel back in time!");
                    }
                    if (newStartTime != null) {
                        if (newDate.isEqual(nowDate) & newStartTime.isBefore(nowTime)) {
                            throw new InvalidDateTime("You cannot travel back in time!");
                        } else if (newEndTime != null) {
                            if (newDate.isEqual(nowDate) & newEndTime.isBefore(nowTime)) {
                                throw new InvalidDateTime("You cannot travel back in time!");
                            } else if (!newEndTime.isAfter(newStartTime)) {
                                throw new InvalidDateTime("The end time must be after the start time");
                            }
                        }
                    }
                    Task currTask = new Event(currDesc, newDate, newStartTime, newEndTime);
                    currTask.setStatus(isMarked);
                    listOfTasks.getListOfTasks().add(currTask);
                    listOfTasks.incrementTasks();
                } // else invalid command => do nothing
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if the input date is in the correct format.
     *
     * @param date Date to be tested.
     */
    private static LocalDate convertDate(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate convertedDate;
        try {
            convertedDate = LocalDate.parse(date, format);
        } catch (DateTimeParseException | NullPointerException e) {
            throw new InvalidDateTime("Please enter date in this format: <yyyy-MM-dd>");
        }
        return convertedDate;
    }

    /**
     * Checks if the input date is in the correct format.
     *
     * @param time Time to be tested.
     */
    private static LocalTime convertTime(String time) {
        if (time == null) { // Time is optional
            return null;
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm");
        LocalTime convertedTime;
        try {
            convertedTime = LocalTime.parse(time, format);
        } catch (DateTimeParseException | NullPointerException e) {
            throw new InvalidDateTime("Please enter time in this 24hrs-format: <HHmm>");
        }
        return convertedTime;
    }

    public static void main(String[] args) {
        Ui.greet();
        try {
            initTaskList();
        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            readData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Ui.welcome();
        File dataFile = new File(DATA_PATH);
        taskListOfTasks = new TaskList(100); // Assume there will be no more than 100 tasks

        if (dataFile.length() != 0) {
            loadData(dataFile, taskListOfTasks);
        }

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
        Ui.exit();
    }
}
