package chatbot.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import chatbot.datetime.Timestamp;
import chatbot.exception.ChatBotException;
import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.ToDo;


/**
 * Represents a list of Tasks managed by ChatBot for the user.
 */
public class TaskList {

    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    private final List<Task> list;
    private final Set<String> set;
    private final Set<String> validTypes;

    /**
     * Instantiates a new Task list.
     */
    public TaskList() {
        this.list = new ArrayList<>();
        this.set = new HashSet<>();

        this.validTypes = new HashSet<>();
        this.validTypes.add(TODO);
        this.validTypes.add(DEADLINE);
        this.validTypes.add(EVENT);
    }

    /**
     * Gets the task at the specified index.
     *
     * @param index The index of the task
     * @return The task
     */
    public Task getTask(int index) {
        return list.get(index);
    }

    /**
     * Gets the number of tasks currently in this TaskList.
     *
     * @return The size of the TaskList.
     */
    public int getNumTasks() {
        return list.size();
    }

    /**
     * Determines whether this TaskList is empty or not.
     *
     * @return True if this TaskList is empty. Else, return false.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Determines whether the given index is valid or not.
     *
     * @param index The index input by the user.
     * @return True if 0 < index < size of TaskList. Else, return false.
     */
    public Boolean isValidIndex(int index) {
        return index >= 0 && index < list.size();
    }

    /**
     * Loads the tasks saved in the save file into this TaskList.
     *
     * @param saveFile The save file.
     * @throws ChatBotException If the save file cannot be found, or it has corrupted data.
     */
    public void load(File saveFile) throws ChatBotException {
        try (Scanner sc = new Scanner(saveFile)) {
            while (sc.hasNext()) {
                String[] data = sc.nextLine().split("&");
                String type = data[0];
                String done = data[1];
                String title = data[2];
                String datetime = data.length == 4 ? data[3] : "";
                switch (type) {
                case "T":
                    list.add(new ToDo(title, done));
                    set.add(title);
                    break;
                case "D":
                    list.add(
                            new Deadline(title, done, new Timestamp(datetime))
                    );
                    set.add(title);
                    break;
                case "E":
                    list.add(
                            new Event(title, done, new Timestamp(datetime))
                    );
                    set.add(title);
                    break;
                default:
                    throw new ChatBotException();
                }
            }
        } catch (FileNotFoundException e) {
            throw new ChatBotException(
                    "I couldn't find your save file traveller! "
                            + "You can create it in the data directory and name it data.txt."
            );
        } catch (ChatBotException e) {
            throw new ChatBotException(
                    "One of your saved tasks was formatted incorrectly traveller! I've removed it from your list."
            );
        }
    }

    /**
     * Saves tasks in this TaskList in the save file.
     *
     * @param saveFile The save file.
     * @throws ChatBotException If I/O error occurs while writing to the save file.
     */
    public void save(File saveFile) throws ChatBotException {
        try (FileWriter fw = new FileWriter(saveFile)) {
            for (Task t : list) {
                String type = t.getType();
                String title = t.getTitle();
                String done = t.getDone();
                Timestamp datetime = t.getTimestamp();

                String data = String.format("%s&%s&%s", type, done, title);
                if (datetime != null) {
                    data = data.concat(
                            String.format("&%s", datetime.toSaveString())
                        );
                }
                fw.write(data);
                fw.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new ChatBotException(
                    "Oops! Something went wrong while writing to your save file traveller!"
            );
        }
    }

    /**
     * Adds a Deadline or an Event to this TaskList.
     *
     * @param titleArgs The title arguments parsed by the Parser.
     * @param timestampArgs The timestamp arguments parsed by the Parser.
     * @return The response to be outputted by the UI.
     * @throws ChatBotException If one or both of the arguments are formatted incorrectly.
     */
    public String add(String[] titleArgs, String[] timestampArgs)
        throws ChatBotException {
        String type = titleArgs[0];
        if (!validTypes.contains(type)) {
            throw new ChatBotException();
        }
        if (titleArgs.length <= 1) {
            throw new ChatBotException(
                    String.format(
                            "You need to key in the title of your %s traveller!",
                            type
                    )
            );
        }
        if (timestampArgs.length <= 1) {
            throw new ChatBotException(
                    String.format(
                            "You need to key in %s traveller!",
                            type.equals(DEADLINE)
                                    ? "the due date and time of your deadline"
                                    : "the timestamp of your event"
                    )
            );
        }

        String title = combineArgs(titleArgs);
        String other = combineArgs(timestampArgs);

        if (set.contains(title)) {
            throw new ChatBotException(
                    String.format(
                            "This %s is already in your task list traveller!",
                            type
                    )
            );
        } else {
            switch (type) {
            case DEADLINE:
                if (!timestampArgs[0].equals("by")) {
                    throw new ChatBotException(
                            "The correct format for adding a deadline is "
                                    + "deadline <name of task> /by <date or timestamp of task>"
                        );
                } else {
                    Timestamp by = new Timestamp(other);
                    Deadline deadline = new Deadline(title, by);
                    list.add(deadline);
                    set.add(title);
                    return String.format(
                        "This deadline has been added to your task list!%n             %d. %s",
                        getNumTasks(),
                        deadline
                    );
                }
            case EVENT:
                if (!timestampArgs[0].equals("at")) {
                    throw new ChatBotException(
                            "The correct format for adding an event is "
                                    + "event <name of task> /at <date or timestamp of task>"
                    );
                } else {
                    Timestamp at = new Timestamp(other);
                    Event event = new Event(title, at);
                    list.add(event);
                    set.add(title);
                    return String.format(
                        "This event has been added to your task list!%n             %d. %s",
                        getNumTasks(),
                        event
                    );
                }
            default:
                throw new ChatBotException();
            }
        }
    }

    /**
     * Adda a ToDo to this TaskList.
     *
     * @param args the arguments parsed by the Parser.
     * @return The response to be outputted by the UI.
     * @throws ChatBotException If the arguments are formatted incorrectly.
     */
    public String addToDo(String[] args) throws ChatBotException {
        if (!validTypes.contains(args[0])) {
            throw new ChatBotException();
        }
        if (args.length <= 1) {
            throw new ChatBotException(
                    "You need to key in the title of your todo traveller!"
            );
        }

        String title = combineArgs(args);
        if (set.contains(title)) {
            throw new ChatBotException(
                    "This todo is already in your task list traveller!"
            );
        }
        ToDo todo = new ToDo(title);
        list.add(todo);
        set.add(title);
        return String.format(
                "This todo has been added to your task list!%n             %d. %s",
                getNumTasks(),
                todo
        );
    }

    /**
     * Delete a task from this TaskList.
     *
     * @param index The index of the task to be deleted.
     * @return The response to be outputted by the UI.
     * @throws ChatBotException If the task index is invalid.
     */
    public String delete(int index) throws ChatBotException {
        if (!isValidIndex(index).equals(true)) {
            throw new ChatBotException(
                    "This is an invalid task index traveller! You can type list to check all task indexes!"
            );
        }

        set.remove(getTask(index).getTitle());
        Task removedTask = list.remove(index);
        return String.format(
                "This task has successfully been removed from your task list!%n             %d. %s",
                index + 1,
                removedTask
        );
    }

    private String combineArgs(String[] args) {
        String title = "";
        for (int i = 1; i < args.length; i++) {
            if (i != args.length - 1) {
                title = title.concat(args[i]).concat(" ");
            } else {
                title = title.concat(args[i]);
            }
        }
        return title;
    }

    /**
     * Prints all Tasks in the TaskList.
     *
     * @return The string format of the TaskList to be outputted by the UI.
     */
    public String summary() {
        if (isEmpty()) {
            return "Your task list is empty traveller! Add some tasks first!";
        }
        return listAsString(list);
    }

    /**
     * Prints all Tasks in the TaskList which occur or are due on the specified date.
     *
     * @param date The date.
     * @return The string format of the filtered TaskList to be outputted by the UI.
     */
    public String getTasksOnDate(Timestamp date) {
        List<Task> filtered = filter(t -> date.equals(t.getTimestamp()));
        if (filtered.isEmpty()) {
            return "You have no tasks on this date traveller!";
        }
        return listAsString(filtered);
    }

    /**
     * Gets all Tasks which match the given keyword.
     *
     * @param input The input given by the user.
     * @return The filtered task list.
     */
    public String getTasksByKeyword(String[] input) {
        String keyword = combineArgs(input);
        if (keyword.isBlank()) {
            return "You need to key in a keyword traveller!";
        }
        List<Task> filtered = filter(t -> keyword.equals(t.getTitle())
                || keyword.contains(t.getTitle())
                || t.getTitle().contains(keyword));
        if (filtered.isEmpty()) {
            return "I couldn't find any tasks matching your keyword traveller!";
        }
        return listAsString(filtered);
    }

    private List<Task> filter(Predicate<Task> condition) {
        return list
                .stream()
                .filter(condition)
                .collect(Collectors.toList());
    }

    private String listAsString(List<Task> tasks) {
        String res = "";
        for (int i = 0; i < tasks.size(); i++) {
            String taskString = String.format(
                    "             %d. %s%n",
                    i + 1,
                    tasks.get(i)
            );
            res = res.concat(taskString);
        }
        return res;
    }

    /**
     * Marks the specified Task in the TaskList as complete.
     *
     * @param index The index of the task to be marked.
     * @return The response to be outputted by the UI.
     */
    public String mark(int index) {
        Task task = list.get(index);
        if (task.getDone().equals("X")) {
            return "This task was already completed! No need to mark it again.";
        }
        task.mark();
        return String.format(
                "This task has been marked as completed in your task list!%n             %d. %s",
                index + 1,
                task
        );
    }

    /**
     * Unmarks the specified Task in the TaskList, indicating that it is incomplete.
     *
     * @param index The index of the task to be unmarked.
     * @return The response to be outputted by the UI.
     */
    public String unmark(int index) {
        Task task = list.get(index);
        if (!task.getDone().equals("X")) {
            return "This task has not been completed yet! No need to unmark it.";
        }
        task.unmark();
        return String.format(
                "This task has been unmarked in your task list!%n             %d. %s",
                index + 1,
                task
        );
    }
}

