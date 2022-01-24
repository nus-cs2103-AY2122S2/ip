import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Connor {
    private static final String CURRENT_VERSION = "Version 1.61";
    private static final String LINE = "┄".repeat(66);
    private static final String INDENT = " ".repeat(4);

    private static final String GOODBYE = "Farewell. See you next time!";
    private static final String EMPTY_TASK_LIST = "Your task list is empty.";
    private static final String SHOW_TASKS = "Here are your current tasks: ";
    private static final String ADD_NEW_TASK = "Alright, I've added a new task: ";
    private static final String DELETE_TASK = "Alright, I've deleted the task: ";
    private static final String MARK_TASK = "Good job! I've marked the following task as completed: ";
    private static final String UNMARK_TASK = "Understood. I've unmarked the following task: ";
    private static final String ERROR_EMPTY_INDEX = "Error! Index cannot be empty.";
    private static final String ERROR_EMPTY_TASK_DESC = "Error! Tasks cannot have an empty description.";
    private static final String ERROR_EMPTY_DL_DESC = "Error! Deadlines cannot have empty descriptions or dates.";
    private static final String ERROR_EMPTY_EVENT_DESC = "Error! Events cannot have empty descriptions or dates.";
    private static final String ERROR_INDEX_NOT_INTEGER = "Error! Index must be a valid integer.";
    private static final String ERROR_INDEX_OUT_OF_RANGE = "Error! Given index is out of range.";
    private static final String ERROR_INVALID_COMMAND_START = "My apologies, I don't understand what '";
    private static final String ERROR_INVALID_COMMAND_END = "' means.";
    private static final String ERROR_INVALID_DL_FORMAT = "Error! Wrong format for deadlines.\n\n"
            + "Deadline tasks must include \"by\" in the description.\nExample: \n"
            + ">>> deadline Finish project /by Monday Morning";
    private static final String ERROR_INVALID_EVENT_FORMAT = "Error! Wrong format for events.\n\n"
            + "Event tasks must include \"at\" in the description.\nExample: \n"
            + ">>> event Birthday Party at May 5th";
    private static final String ERROR_INVALID_TASK_TYPE = "Oh no! Incorrect Task type!";
    private static final String ERROR_MARK_EMPTY = "Error! I can't mark an empty task list!";
    private static final String ERROR_UNMARK_EMPTY = "Error! I can't unmark an empty task list!";

    private static boolean isActive = true;
    private static ArrayList<Task> taskList = new ArrayList<>();

    private static void interpret(String s) {
        // Split between command and description
        // trim then concat ensures statement[1] won't be null
        String[] statement = s.trim().concat(" ").split(" ", 2);
        // Standardise command format
        String x = statement[0].toLowerCase();
        String desc = statement[1].trim();
        switch (x) {
        case "exit":
        case "bye": {
            isActive = false;
            print(GOODBYE);
            break;
        }
        case "list": {
            viewTasks();
            break;
        }
        case "todo": {
            addTask(TaskType.TODO, desc);
            break;
        }
        case "deadline": {
            addTask(TaskType.DEADLINE, desc);
            break;
        }
        case "event": {
            addTask(TaskType.EVENT, desc);
            break;
        }
        case "delete": {
            try {
                int taskNo = Integer.parseInt(desc) - 1;
                deleteTask(taskNo);
                print("");
                viewTasks();
            } catch (NumberFormatException e) {
                print(ERROR_INDEX_NOT_INTEGER);
            }
            break;
        }
        case "mark":
        case "unmark": {
            try {
                int taskNo = Integer.parseInt(desc) - 1;
                markStatus(x, taskNo);
            } catch (NumberFormatException e) {
                print(ERROR_INDEX_NOT_INTEGER);
            }
            break;
        }
        default: {
            print(ERROR_INVALID_COMMAND_START + statement[0] + ERROR_INVALID_COMMAND_END);
        }
        }
    }

    private static void viewTasks() {
        if (taskList.size() == 0) {
            print(EMPTY_TASK_LIST);
            return;
        }
        print(SHOW_TASKS);
        for (int i = 1; i <= taskList.size(); i++) {
            print(INDENT + i + ". " + taskList.get(i - 1));
        }
    }

    private static void addTask(TaskType taskType, String desc) {
        if (desc.isEmpty()) {
            print(ERROR_EMPTY_TASK_DESC);
            return;
        }
        switch (taskType) {
        case TODO:
            ToDo todo = new ToDo(desc);
            taskList.add(todo);
            print(ADD_NEW_TASK);
            print(INDENT + todo);
            break;
        case DEADLINE: {
            if (!desc.contains("/by")) {
                print(ERROR_INVALID_DL_FORMAT);
                return;
            }
            String[] phrase = desc.split("/by", 2);
            String thing = phrase[0].trim();
            String when  = phrase[1].trim();
            if (thing.isBlank() || when.isBlank()) {
                print(ERROR_EMPTY_DL_DESC);
                return;
            }
            try {
                // Check if 'when' is a valid date
                LocalDate ld = LocalDate.parse(when);
                Deadline deadline = new Deadline(thing, ld);
                taskList.add(deadline);
                print(ADD_NEW_TASK);
                print(INDENT + deadline);
            } catch (DateTimeParseException e) {
                // Otherwise, treat it as a normal String
                Deadline deadline = new Deadline(thing, when);
                taskList.add(deadline);
                print(ADD_NEW_TASK);
                print(INDENT + deadline);
            }
            break;
        }
        case EVENT: {
            if (!desc.contains("/at")) {
                print(ERROR_INVALID_EVENT_FORMAT);
                return;
            }
            String[] phrase = desc.split("/at", 2);
            String thing = phrase[0].trim();
            String when  = phrase[1].trim();
            if (thing.isBlank() || when.isBlank()) {
                print(ERROR_EMPTY_EVENT_DESC);
                return;
            }
            try {
                // Check if 'when' is a valid date
                LocalDate ld = LocalDate.parse(when);
                Event event = new Event(thing, ld);
                taskList.add(event);
                print(ADD_NEW_TASK);
                print(INDENT + event);
            } catch (DateTimeParseException e) {
                // Otherwise, treat it as a normal String
                Event event = new Event(thing, when);
                taskList.add(event);
                print(ADD_NEW_TASK);
                print(INDENT + event);
            }
            break;
        }
        default:
            // Something has gone wrong
            print(ERROR_INVALID_TASK_TYPE);
            return;
        }
        // After task is added show current no. of tasks
        print("");
        getNoOfTasks();
    }

    private static void deleteTask(int index) {
        try {
            Task t = taskList.get(index);
            taskList.remove(index);
            print(DELETE_TASK);
            print(INDENT + t);
        } catch (IndexOutOfBoundsException e) {
            print(ERROR_INDEX_OUT_OF_RANGE);
        }
    }

    private static void markStatus(String status, int index) {
        if (status.equals("mark")) {
            try {
                Task t = taskList.get(index);
                t.mark();
                print(MARK_TASK);
                print(INDENT + t);
            } catch (IndexOutOfBoundsException e) {
                if (taskList.isEmpty()) {
                    print(ERROR_MARK_EMPTY);
                    return;
                }
                print(ERROR_INDEX_OUT_OF_RANGE);
                getNoOfTasks();
            }
        } else {
            // Unmark
            try {
                Task t = taskList.get(index);
                t.unmark();
                print(UNMARK_TASK);
                print(INDENT + t);
            } catch (IndexOutOfBoundsException e) {
                if (taskList.isEmpty()) {
                    print(ERROR_UNMARK_EMPTY);
                    return;
                }
                print(ERROR_INDEX_OUT_OF_RANGE);
                getNoOfTasks();
            }
        }
    }

    private static void getNoOfTasks() {
        String plurality = taskList.size() == 1 ? "" : "s";
        print("You have " + taskList.size() + " task" + plurality + ".");
    }

    private static void print(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        String logo = " .d8888b.\n"
                + "d88P  Y88b\n"
                + "888    888\n"
                + "888         .d88b.  88888b.  88888b.   .d88b.  888d888\n"
                + "888        d88\"\"88b 888 \"88b 888 \"88b d88\"\"88b 888P\"\n"
                + "888    888 888  888 888  888 888  888 888  888 888\n"
                + "Y88b  d88P Y88..88P 888  888 888  888 Y88..88P 88\n"
                + " \"Y8888P\"   \"Y88P\"  888  888 888  888  \"Y88P\"  888\n";
        print("\n" + logo + "\n" + CURRENT_VERSION + "\n");
        print(LINE);
        print("Hi, my name is Connor! I'm your personalised android assistant.\n"
                + "How may I help?");
        print(LINE);
        // Greeting ends, User can input now
        Scanner sc = new Scanner(System.in);
        while (isActive) {
            System.out.print(">>> ");
            String input = sc.nextLine();
            print(input);
            print(LINE);
            interpret(input);
            print(LINE);
        }
        sc.close();
    }

}
