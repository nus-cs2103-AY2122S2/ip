
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String GREET_MESSAGE
            = "Roarrr.... I'm Burning Charizard, tasked to burnnn down your tasks.\n" +
            "Which task shall we burn today?";
    private static final String BYE_MESSAGE = "Roarrr....Let's burn more tasks next time!";
    private static final String ADD_MESSAGE = "Charizard is ready to burn task:";
    private static final String REMOVE_MESSAGE = "Charizard got tired of waiting and deleted this task";
    private static final String MARK_MESSAGE = "Charizard breathe out fire and burned the task.";
    private static final String UNMARK_MESSAGE = "Oh no! The task was not burnt completely!";
    private static final String QUESTION_MESSAGE = "What should Charizard do next?";
    private static final String ERROR_NO_COMMAND = "Charizard does not know this move. Try a different command.";
    private static final String ERROR_PARSE_INT = "Charizard's can only remember numbers. Try specifying a task number."
            + "\n(Use \"list\" command to see the tasks and their corresponding task number).";
    private static final String ERROR_INVALID_INDEX = "Task number does not exist. Charizard is confused..";
    private static final String ERROR_TODO_NO_NAME = "Please specify the name of new task to be burnt";
    private static final String ERROR_INVALID_NAME_OR_DATE
            = "Charizard insists of knowing both name and time of the new task.";
    private static final String ERROR_TOO_MANY_DATES = "Charizard can only remember one deadline per task.";
    private static final String FORMAT_DEADLINE = "Try using \"deadline <task_name> /by <deadline>\".";
    private static final String FORMAT_EVENT = "Try using \"event <task_name> /by <event_time>\".";
    private static final int BORDER_LENGTH = 80;
    private static ArrayList<Task> tasks;
    private static final String FILE_PATH = "data/tasks.txt";

    public static void main(String[] args) {
        try {
            tasks = loadTaskData();
            greet();
            Scanner sc = new Scanner(System.in);
            System.out.println(QUESTION_MESSAGE);
            String input = sc.nextLine();
            while (input.compareToIgnoreCase("bye") != 0) {
                processInput(input);
                System.out.println(QUESTION_MESSAGE);
                input = sc.nextLine();
            }
            exit();
        } catch (DukeException e) {
            System.out.println("Error loading or saving data\n" + e.getMessage());
        }
    }

    private static ArrayList<Task> loadTaskData() throws DukeException{
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<Task>();
        }
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(FILE_PATH));
            String taskInput;
            ArrayList<Task> readTasks = new ArrayList<Task>();
            while ((taskInput = fileReader.readLine()) != null) {
                String[] splitTaskInput = taskInput.split(" \\| ");
                Task newTask;
                if (splitTaskInput[0].equals("T")) {
                    newTask = new ToDo(splitTaskInput[2]);
                } else if (splitTaskInput[0].equals("D")) {
                    newTask = new Deadline(splitTaskInput[2], splitTaskInput[3]);
                } else if (splitTaskInput[0].equals("E")) {
                    newTask = new Event(splitTaskInput[2], splitTaskInput[3]);
                } else {
                    throw new DukeException("Error: Task type is not T,D or E in file\n");
                }
                int i = Integer.parseInt(splitTaskInput[1]);
                if (i == 1) {
                    newTask.mark(true);
                }
                readTasks.add(newTask);
            }
            return readTasks;
        } catch (IOException e) {
            throw new DukeException("Error: File input cannot be read\n" + e.getMessage());
        } catch (NumberFormatException e) {
            throw new DukeException("Error: isDone field is not indicated by 0 or 1 in file\n" + e.getMessage());
        }
    }

    private static void saveFileData() throws DukeException{
        try {
            if(Files.notExists(Paths.get(FILE_PATH))) {
                Files.createDirectories(Paths.get("data/"));
                Files.createFile(Paths.get(FILE_PATH));
            }

            BufferedWriter bw = Files.newBufferedWriter(Paths.get(FILE_PATH), Charset.forName("UTF-8"));
            for (Task t : tasks) {
                bw.append(t.getFileSaveFormat());
                bw.append("\n");
            }
            bw.close();
        } catch (IOException e) {
            throw new DukeException("Unable to write to file\n" + e.getMessage());
        }
    }

    public static void show(String message) {
        drawBorder(BORDER_LENGTH);
        String[] lines = message.split("\n");
        for (String line: lines) {
            System.out.println("    " + line);
        }
        drawBorder(BORDER_LENGTH);
        System.out.println();
    }

    public static void processInput(String input) {
        String message = "";
        try {
            if (input.equalsIgnoreCase("list")) {
                message = "Charizard's burning wish list:\n";
                for (int i = 0; i < tasks.size(); i++) {
                    message += String.format("%d. %s", i + 1, tasks.get(i).toString());
                    if (i < tasks.size() - 1) {
                        message += "\n";
                    }
                }
            } else if (input.startsWith("mark")) {
                String inputWithoutCommand = input.replaceFirst("mark", "").trim();
                message = processMark(inputWithoutCommand, true);
            } else if (input.startsWith("unmark")) {
                String inputWithoutCommand = input.replaceFirst("unmark", "").trim();
                message = processMark(inputWithoutCommand, false);
            } else if (input.startsWith("todo")) {
                String description = input.replaceFirst("todo", "").trim();
                message = processTodo(description);
            } else if (input.startsWith("deadline")) {
                String inputWithoutCommand = input.replaceFirst("deadline", "").trim();
                message = processDeadline(inputWithoutCommand);
            } else if (input.startsWith("event")) {
                String inputWithoutCommand = input.replaceFirst("event", "").trim();
                message = processEvent(inputWithoutCommand);
            } else if (input.startsWith("delete")) {
                String inputWithoutCommand = input.replaceFirst("delete", "").trim();
                message = processDelete(inputWithoutCommand);
            } else {
                throw new DukeException(ERROR_NO_COMMAND);
            }
        } catch (DukeException e) {
            message = "OOPS! " + e.getMessage();
        }
        show(message);
    }

    public static String processMark(String input, boolean isDone) throws DukeException {
        try {
            int taskId = Integer.parseInt(input) - 1;
            tasks.get(taskId).mark(isDone);
            String message = "";
            if (isDone) {
                message = MARK_MESSAGE;
            } else {
                message = String.format(UNMARK_MESSAGE);
            }
            message += String.format("\n  %s", tasks.get(taskId).toString());
            return message;
        } catch (NumberFormatException e) {
            throw new DukeException(ERROR_PARSE_INT);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ERROR_INVALID_INDEX);
        }
    }

    public static String processDelete(String input) throws DukeException {
        try {
            int taskId = Integer.parseInt(input) - 1;
            Task task = tasks.get(taskId);
            tasks.remove(taskId);
            String message = String.format("%s\n  %s\nThere are %d tasks in the burning list.",
                    REMOVE_MESSAGE, task.toString(), tasks.size());
            return message;
        } catch (NumberFormatException e) {
            throw new DukeException(ERROR_PARSE_INT);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ERROR_INVALID_INDEX);
        }
    }

    public static String processTodo(String input) throws DukeException {
        if (input.matches("\\s*")) {
            throw new DukeException(ERROR_TODO_NO_NAME);
        }
        ToDo newTodo = new ToDo(input);
        tasks.add(newTodo);
        String message = String.format("%s\n  %s\nThere are %d tasks in the burning list.",
                ADD_MESSAGE, newTodo.toString(), tasks.size());
        return message;
    }

    public static String processDeadline(String input) throws DukeException {
        String[] splitInput = input.split("/by");
        if (splitInput.length < 2 || splitInput[0].matches("\\s*") || splitInput[1].matches("\\s*")) {
            throw new DukeException(ERROR_INVALID_NAME_OR_DATE + "\n" + FORMAT_DEADLINE);
        } else if (splitInput.length > 2) {
            throw new DukeException(ERROR_TOO_MANY_DATES);
        }
        String description = splitInput[0].trim();
        String deadline = splitInput[1].trim();
        Deadline newDeadline = new Deadline(description, deadline);
        tasks.add(newDeadline);
        String message = String.format("%s\n  %s\nThere are %d tasks in the burning list.",
                ADD_MESSAGE, newDeadline.toString(), tasks.size());
        return message;
    }

    public static String processEvent(String input) throws DukeException {
        String[] splitInput = input.split("/at");
        if (splitInput.length < 2) {
            throw new DukeException(ERROR_INVALID_NAME_OR_DATE + "\n" + FORMAT_EVENT);
        } else if (splitInput.length > 2) {
            throw new DukeException(ERROR_TOO_MANY_DATES);
        }
        String description = splitInput[0].trim();
        String time = splitInput[1].trim();
        Event newEvent = new Event(description, time);
        tasks.add(newEvent);
        String message = String.format("%s\n  %s\nThere are %d tasks in the burning list.",
                ADD_MESSAGE, newEvent.toString(), tasks.size());
        return message;
    }

    public static void exit() {
        drawBorder(BORDER_LENGTH);
        System.out.println(BYE_MESSAGE);
        drawBorder(BORDER_LENGTH);
        try {
            saveFileData();
        } catch (DukeException e) {
            System.out.println("Error: Unable to save file\n" + e.getMessage());
        }
    }

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        drawBorder(BORDER_LENGTH);
        System.out.println(GREET_MESSAGE);
        drawBorder(BORDER_LENGTH);
        System.out.println();
    }

    private static void drawBorder(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("*");
        };
        System.out.println("");
    }
}
