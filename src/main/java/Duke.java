import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Chatbot that supports tracking tasks as added by the user. Created as part of CS2103T.
 *
 * @author Jet Tan
 */
public class Duke {
    private static final String LOGO =
            "   ___      _  ______       _   \n" +
                    "  |_  |    | | | ___ \\     | |  \n" +
                    "    | | ___| |_| |_/ / ___ | |_ \n" +
                    "    | |/ _ \\ __| ___ \\/ _ \\| __|\n" +
                    "/\\__/ /  __/ |_| |_/ / (_) | |_ \n" +
                    "\\____/ \\___|\\__\\____/ \\___/ \\__|\n";
    private static final String BORDER = "________________________________\n";
    private static final ArrayList<Task> TASKS = new ArrayList<>();
    public static final String DATA_FOLDER_PATH = "./data";
    public static final String TASKLIST_FILE_PATH = "./data/duke.txt";

    /**
     * Greets the user.
     */
    private static void greet() {
        System.out.println(BORDER + LOGO + BORDER);
        System.out.println("How may I help you today? Please enter your command:");
    }

    /**
     * Exits the program.
     */
    private static void exit() {
        System.out.println(BORDER + "Bye!\n" + BORDER);
        System.exit(0);
    }

    /**
     * Marks the specified task as done.
     */
    private static void mark(int tasknum) {
        Task t = TASKS.get(tasknum - 1);
        System.out.println(BORDER + "Well done! I've marked this task as done: \n");
        t.markAsDone();
        System.out.println(t + "\n" + BORDER);
    }

    /**
     * Unmarks the specified task.
     */
    private static void unmark(int tasknum) {
        Task t = TASKS.get(tasknum - 1);
        System.out.println(BORDER + "No problem, I've marked this task as undone: \n");
        t.unmarkAsDone();
        System.out.println(t + "\n" + BORDER);
    }

    /**
     * Returns the string to be printed on a successful add operation.
     */
    private static String successMessage(Task t) {
        return BORDER + "Got it. I've added this task:\n" + t + "\n" +
                "Now you have " + TASKS.size() + " task(s) in the list.\n" + BORDER;
    }

    /**
     * Deletes the specified task.
     */
    private static void delete(int tasknum) {
        Task t = TASKS.get(tasknum - 1);
        System.out.println(BORDER + "No problem, I've deleted the following task:\n" + t);
        TASKS.remove(tasknum - 1);
        System.out.println("There are now " + TASKS.size() + " task(s) remaining.\n" + BORDER);
    }

    /**
     * Processes the input.
     *
     * @throws DukeException InvalidInputException, EmptyDescDescription, UnknownCommandException
     */
    private static void process(String input) throws DukeException, IOException {
        String[] arr = input.split(" ");
        String command = arr[0]; // first word of the user input
        switch (command) {
        case "bye":
            exit();
            break;
        case "list":
            if (TASKS.size() == 0) {
                System.out.println(BORDER + "The list is empty. Why not add some tasks?\n" + BORDER);
            } else {
                StringBuilder listString = new StringBuilder();
                for (int i = 0; i < TASKS.size(); i++) {
                    Task t = TASKS.get(i);
                    listString.append(i + 1).append(".").append(t.toString()).append("\n");
                }
                System.out.println(BORDER + listString + BORDER);
            }
            break;
        case "mark": {
            int num = Integer.parseInt(arr[1]);
            if (num > TASKS.size()) {
                throw new InvalidInputException(BORDER + "The specified task does not exist.\n" + BORDER);
            } else if (num < 1) {
                throw new InvalidInputException(BORDER + "Selection must be positive.\n" + BORDER);
            }
            mark(num);
            saveToFile();
            break;
        }
        case "unmark": {
            int num = Integer.parseInt(arr[1]);
            if (num > TASKS.size()) {
                throw new InvalidInputException(BORDER + "The specified task does not exist.\n" + BORDER);
            } else if (num < 1) {
                throw new InvalidInputException(BORDER + "Selection must be positive.\n" + BORDER);
            }
            unmark(num);
            saveToFile();
            break;
        }
        case "todo": {
            String desc = input.replaceFirst("todo", "").trim();
            Todo newTodo = new Todo(desc);
            if (desc.equals("")) {
                throw new EmptyDescException(BORDER + "Todo description cannot be empty.\n" + BORDER);
            }
            TASKS.add(newTodo);
            System.out.println(successMessage(newTodo));
            saveToFile();
            break;
        }
        case "event": {
            if (!input.contains("/at")) {
                throw new InvalidInputException("Usage: event <description> /at <time>");
            }
            String[] descTimePair = input.replaceFirst("event", "").trim().split("/at");
            if (descTimePair.length < 2) {
                throw new InvalidInputException("Usage: event <description> /at <time>");
            }
            String desc = descTimePair[0];
            if (desc.equals("")) {
                throw new EmptyDescException(BORDER + "Event description cannot be empty.\n" + BORDER);
            }
            String time = descTimePair[1];
            Event newEvent = new Event(desc, time);
            TASKS.add(newEvent);
            System.out.println(successMessage(newEvent));
            saveToFile();
            break;
        }
        case "deadline": {
            if (!input.contains("/by")) {
                throw new InvalidInputException("Usage: deadline <description> /by <time>");
            }
            String[] descTimePair = input.replaceFirst("deadline", "").trim().split("/by");
            if (descTimePair.length < 2) {
                throw new InvalidInputException("Usage: deadline <description> /by <time>");
            }
            String desc = descTimePair[0];
            if (desc.equals("")) {
                throw new EmptyDescException(BORDER + "Deadline description cannot be empty.\n" + BORDER);
            }
            String time = descTimePair[1];
            Deadline newDeadline = new Deadline(desc, time);
            TASKS.add(newDeadline);
            System.out.println(successMessage(newDeadline));
            saveToFile();
            break;
        }
        case "delete": {
            int num = Integer.parseInt(arr[1]);
            if (num > TASKS.size()) {
                throw new InvalidInputException(BORDER + "The specified task does not exist.\n" + BORDER);
            } else if (num < 1) {
                throw new InvalidInputException(BORDER + "Selection must be positive.\n" + BORDER);
            }
            delete(num);
            saveToFile();
            break;
        }
        default:
            throw new UnknownCommandException(BORDER + "I'm sorry, but I don't know what that means.\n" + BORDER);
        }
    }

    /**
     * Initializes the folder and file if they do not exist.
     */
    private static void initFiles() throws IOException {
        File folder = new File(DATA_FOLDER_PATH);
        if (!Files.exists(Path.of(DATA_FOLDER_PATH)) || !folder.isDirectory()) {
            System.out.println("Data folder not found, creating a new folder.");
            boolean isSuccess = folder.mkdirs();
            if (isSuccess) {
                System.out.println("Data folder has been created.");
            } else {
                throw new IOException("Failed to create folder, please try again");
            }
        }
        File file = new File(TASKLIST_FILE_PATH);
        if (!Files.exists(Path.of(TASKLIST_FILE_PATH)) || !file.isFile()) {
            System.out.println("Tasklist file not found, creating a new file.");
            boolean isSuccess = file.createNewFile();
            if (isSuccess) {
                System.out.println("Tasklist file has been created successfully.");
            } else {
                throw new IOException("Failed to create tasklist file, please try again.");
            }
        }
    }

    /**
     * Saves the tasks to the file.
     */
    private static void saveToFile() throws IOException {
        StringBuilder listString = new StringBuilder();
        for (int i = 0; i < TASKS.size(); i++) {
            Task t = TASKS.get(i);
            listString.append(i + 1).append(".").append(t.toString()).append("\n");
        }
        PrintWriter out = new PrintWriter(TASKLIST_FILE_PATH);
        out.println(listString);
        out.close();
    }

    /**
     * Reads the tasks in the save file and loads them into memory.
     */
    private static void loadFile() throws FileNotFoundException {
        File file = new File(TASKLIST_FILE_PATH);
        Scanner s = new Scanner(file);
        while (s.hasNextLine()) {
            String taskString = s.nextLine();
            if (taskString.equals("")) { // reached EoF
                break;
            }
            String descWithDate = taskString.substring(9);
            Task taskToAdd;
            char taskType = taskString.charAt(3);
            boolean isDone = (taskString.charAt(6) == 'X');
            if (taskType == 'E') { // task is an event
                int indexOfDate = descWithDate.indexOf("(at: ");
                String desc = descWithDate.substring(0, indexOfDate);
                taskToAdd = new Event(desc, descWithDate.substring(indexOfDate + 4, descWithDate.length() - 1));
            } else if (taskType == 'D') { // task is a deadline
                int indexOfDate = descWithDate.indexOf("(by: ");
                String desc = descWithDate.substring(0, indexOfDate);
                taskToAdd = new Deadline(desc, descWithDate.substring(indexOfDate + 4, descWithDate.length() - 1));
            } else { // task is a todo
                taskToAdd = new Todo(descWithDate);
            }
            TASKS.add(taskToAdd);
        }
    }

    /**
     * Driver method of the chatbot.
     */
    public static void main(String[] args) {
        try {
            initFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            loadFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        greet();
        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String input = s.nextLine().toLowerCase().trim();
            try {
                process(input);
            } catch (DukeException | IOException e) {
                System.out.println(e);
            }
        }
    }
}