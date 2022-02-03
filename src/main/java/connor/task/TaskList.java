package connor.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import connor.Connor;

/**
 * Stores and edits the task list.
 */
public class TaskList {
    private static final String INDENT = Connor.INDENT;
    private static final String LINE = Connor.LINE;
    private static final String EMPTY_TASK_LIST = "Your task list is empty.";
    private static final String SHOW_TASKS = "Here are your current tasks: ";
    private static final String ADD_NEW_TASK = "Alright, I've added a new task: ";
    private static final String DELETE_TASK = "Alright, I've deleted the task: ";
    private static final String CLEAR_TASKS_CONFIRM = "Are you REALLY sure you want to clear all your tasks?\n"
            + "Type 'yes' to confirm, type anything else (or nothing) to cancel.";
    private static final String CLEAR_TASKS_CONFIRMED = "Poof! All your tasks are cleared!";
    private static final String CLEAR_TASKS_CANCEL = "Cancelled clearing all tasks. Phew!";
    private static final String MARK_TASK = "Good job! I've marked the following task as completed: ";
    private static final String UNMARK_TASK = "Understood. I've unmarked the following task: ";
    private static final String NO_MATCHING_TASKS = "Sorry, there are no matching tasks in your list.";
    private static final String SHOW_MATCHING_TASKS = "Here are the matching tasks in your list: ";
    private static final String ERROR_EMPTY_INDEX = "Error! Index cannot be empty.";
    private static final String ERROR_EMPTY_TASK_DESC = "Error! Tasks cannot have an empty description.";
    private static final String ERROR_EMPTY_DL_DESC = "Error! Deadlines cannot have empty descriptions or dates.";
    private static final String ERROR_EMPTY_EVENT_DESC = "Error! Events cannot have empty descriptions or dates.";
    private static final String ERROR_INDEX_NOT_INTEGER = "Error! Index must be a valid integer.";
    private static final String ERROR_INDEX_OUT_OF_RANGE = "Error! Given index is out of range.";
    private static final String ERROR_INVALID_DL_FORMAT = "Error! Wrong format for deadlines.\n\n"
            + "Deadline tasks must include \"by\" in the description.\nExample: \n"
            + ">>> deadline Finish project /by Monday Morning";
    private static final String ERROR_INVALID_DL_FORMAT_GUI = "Error! Wrong format for deadlines.\n\n"
            + "Deadline tasks must include \"by\" in the description.\nExample: \n"
            + "'deadline Finish project /by Monday Morning'";
    private static final String ERROR_INVALID_EVENT_FORMAT = "Error! Wrong format for events.\n\n"
            + "Event tasks must include \"at\" in the description.\nExample: \n"
            + ">>> event Birthday Party at May 5th";
    private static final String ERROR_INVALID_EVENT_FORMAT_GUI = "Error! Wrong format for events.\n\n"
            + "Event tasks must include \"at\" in the description.\nExample: \n"
            + "'event Birthday Party at May 5th'";
    private static final String ERROR_INVALID_TASK_TYPE = "Oh no! Incorrect Task type!";
    private static final String ERROR_INVALID_TASK_STATUS = "Oh no! Invalid Task status!";
    private static final String ERROR_MARK_EMPTY = "Error! I can't mark an empty task list!";
    private static final String ERROR_UNMARK_EMPTY = "Error! I can't unmark an empty task list!";

    private static ArrayList<Task> tasks;

    public static void setTasks(ArrayList<Task> t) {
        tasks = t;
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Prints the {@code Task}s on the console, with one {@code Task} in each line.
     * If there are no {@code Task}s in the task list, the method will print that
     * there are no {@code Task}s on the console.
     *
     * @return A String listing all the tasks.
     */
    public static String viewTasks() {
        if (tasks.size() == 0) {
            print(EMPTY_TASK_LIST);
            return EMPTY_TASK_LIST;
        }
        print(SHOW_TASKS);
        StringBuilder tasksToString = new StringBuilder(SHOW_TASKS + "\n");
        for (int i = 1; i <= tasks.size(); i++) {
            print(INDENT + i + ". " + tasks.get(i - 1));
            tasksToString.append(INDENT + i + ". " + tasks.get(i - 1) + "\n");
        }
        return tasksToString.toString();
    }

    /**
     * Adds a {@code Task} into the task list with a description.
     * If the {@code Task} is a {@code Deadline} or an {@code Event}, the method splits the
     * description into two parts, the actual description and when the {@code Task} is due/occurs,
     * with the delimiter "/by" or "/at" respectively.
     * <p>
     * The method also checks if the given occasion is of the valid {@code LocalDate} format, defaulting
     * to a regular {@code String} otherwise.
     *
     * @param taskType Type of {@code Task}.
     * @param desc Description of the task and also the occasion if the {@code Task} is a
     *             {@code Deadline} or an {@code Event}.
     * @return Message containing confirmation that a task was added or an error.
     */
    public static String addTask(TaskType taskType, String desc) {
        if (desc.isEmpty()) {
            print(ERROR_EMPTY_TASK_DESC);
            return ERROR_EMPTY_TASK_DESC;
        }
        StringBuilder message = new StringBuilder();
        switch (taskType) {
        case TODO:
            ToDo todo = new ToDo(desc);
            tasks.add(todo);
            print(ADD_NEW_TASK);
            print(INDENT + todo);
            message.append(ADD_NEW_TASK + "\n" + INDENT + todo + "\n");
            break;
        case DEADLINE: {
            if (!desc.contains("/by")) {
                print(ERROR_INVALID_DL_FORMAT);
                return ERROR_INVALID_DL_FORMAT_GUI;
            }
            String[] phrases = desc.split("/by", 2);
            String thing = phrases[0].trim();
            String when = phrases[1].trim();
            if (thing.isBlank() || when.isBlank()) {
                print(ERROR_EMPTY_DL_DESC);
                return ERROR_EMPTY_TASK_DESC;
            }
            try {
                // Check if 'when' is a valid date
                LocalDate ld = LocalDate.parse(when);
                Deadline deadline = new Deadline(thing, ld);
                tasks.add(deadline);
                print(ADD_NEW_TASK);
                print(INDENT + deadline);
                message.append(ADD_NEW_TASK + "\n" + INDENT + deadline + "\n");
            } catch (DateTimeParseException e) {
                // Otherwise, treat it as a normal String
                Deadline deadline = new Deadline(thing, when);
                tasks.add(deadline);
                print(ADD_NEW_TASK);
                print(INDENT + deadline);
                message.append(ADD_NEW_TASK + "\n" + INDENT + deadline + "\n");
            }
            break;
        }
        case EVENT: {
            if (!desc.contains("/at")) {
                print(ERROR_INVALID_EVENT_FORMAT);
                return ERROR_INVALID_EVENT_FORMAT_GUI;
            }
            String[] phrases = desc.split("/at", 2);
            String thing = phrases[0].trim();
            String when = phrases[1].trim();
            if (thing.isBlank() || when.isBlank()) {
                print(ERROR_EMPTY_EVENT_DESC);
                return ERROR_EMPTY_EVENT_DESC;
            }
            try {
                // Check if 'when' is a valid date
                LocalDate ld = LocalDate.parse(when);
                Event event = new Event(thing, ld);
                tasks.add(event);
                print(ADD_NEW_TASK);
                print(INDENT + event);
                message.append(ADD_NEW_TASK + "\n" + INDENT + event + "\n");
            } catch (DateTimeParseException e) {
                // Otherwise, treat it as a normal String
                Event event = new Event(thing, when);
                tasks.add(event);
                print(ADD_NEW_TASK);
                print(INDENT + event);
                message.append(ADD_NEW_TASK + "\n" + INDENT + event + "\n");
            }
            break;
        }
        default: {
            // Something has gone wrong
            print(ERROR_INVALID_TASK_TYPE);
            return ERROR_INVALID_TASK_TYPE;
        }
        }
        // After task is added show current number of tasks
        print("");
        getNumberOfTasks();
        message.append(getNumberOfTasksString());
        return message.toString();
    }

    /**
     * Deletes a {@code Task} in the task list with the given index.
     *
     * @param index Index of the task to be deleted.
     * @return Message containing confirmation that a task was deleted or an error.
     */
    public static String deleteTask(int index) {
        try {
            Task t = tasks.get(index);
            tasks.remove(index);
            print(DELETE_TASK);
            print(INDENT + t);
            return DELETE_TASK + "\n" + INDENT + t;
        } catch (IndexOutOfBoundsException e) {
            print(ERROR_INDEX_OUT_OF_RANGE);
            return ERROR_INDEX_OUT_OF_RANGE;
        }
    }

    /**
     * Clears all {@code Task}s from the task list.
     * @return Message containing confirmation that all tasks were cleared.
     */
    public static String clearTasks() {
        tasks.clear();
        print(CLEAR_TASKS_CONFIRMED);
        return CLEAR_TASKS_CONFIRMED;
    }

    /**
     * Marks/Unmarks a {@code Task} in the task list with the given index.
     *
     * @param ts Status of the task to be applied.
     * @param index Index of the task to be marked/unmarked.
     * @return Message containing confirmation that a task was marked/unmarked or an error.
     */
    public static String markStatus(TaskStatus ts, int index) {
        switch (ts) {
        case MARK: {
            try {
                Task t = tasks.get(index);
                t.mark();
                print(MARK_TASK);
                print(INDENT + t);
                return MARK_TASK + "\n" + INDENT + t;
            } catch (IndexOutOfBoundsException e) {
                if (tasks.isEmpty()) {
                    print(ERROR_MARK_EMPTY);
                    return ERROR_MARK_EMPTY;
                }
                print(ERROR_INDEX_OUT_OF_RANGE);
                getNumberOfTasks();
                return ERROR_INDEX_OUT_OF_RANGE + "\n" + getNumberOfTasksString();
            }
        }
        case UNMARK: {
            try {
                Task t = tasks.get(index);
                t.unmark();
                print(UNMARK_TASK);
                print(INDENT + t);
                return UNMARK_TASK + "\n" + INDENT + t;
            } catch (IndexOutOfBoundsException e) {
                if (tasks.isEmpty()) {
                    print(ERROR_UNMARK_EMPTY);
                    return ERROR_MARK_EMPTY;
                }
                print(ERROR_INDEX_OUT_OF_RANGE);
                getNumberOfTasks();
                return ERROR_INDEX_OUT_OF_RANGE + "\n" + getNumberOfTasksString();
            }
        }
        default: {
            // Something has gone wrong
            print(ERROR_INVALID_TASK_STATUS);
            return ERROR_INVALID_TASK_STATUS;
        }
        }
    }

    /**
     * Finds {@code Task}s in the task list that have the keyword in their description,
     * prints out the matching tasks and returns an {@code ArrayList} with the matching tasks.
     *
     * @param keyword Keyword used to find matching {@code Task}s.
     * @return A String listing all matching tasks.
     */
    public static String findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        StringBuilder taskListString = new StringBuilder();
        for (Task t : TaskList.getTasks()) {
            // Search is not case-sensitive
            if (t.getDesc().toLowerCase().contains(keyword.trim().toLowerCase())) {
                matchingTasks.add(t);
            }
        }
        if (matchingTasks.size() == 0) {
            print(NO_MATCHING_TASKS);
            return NO_MATCHING_TASKS;
        } else {
            print(SHOW_MATCHING_TASKS);
            taskListString.append(SHOW_MATCHING_TASKS + "\n");
            for (int i = 1; i <= matchingTasks.size(); i++) {
                print(INDENT + i + ". " + matchingTasks.get(i - 1));
                taskListString.append(INDENT + i + ". " + matchingTasks.get(i - 1) + "\n");
            }
        }
        return taskListString.toString();
    }

    /**
     * Returns the number of tasks in the task list, and also prints out a message
     * saying the number of tasks in the task list.
     *
     * @return Number of tasks in the task list.
     */
    public static int getNumberOfTasks() {
        String plurality = tasks.size() == 1 ? "" : "s";
        print("You have " + tasks.size() + " task" + plurality + ".");
        return tasks.size();
    }

    public static String getNumberOfTasksString() {
        String plurality = tasks.size() == 1 ? "" : "s";
        return "You have " + tasks.size() + " task" + plurality + ".";
    }

    private static void print(String s) {
        System.out.println(s);
    }
}
