package chatbot.list;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import chatbot.datetime.Timestamp;
import chatbot.exception.ChatBotException;
import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.ToDo;


/**
 * Represents a list of Tasks managed by ChatBot for the user.
 */
public class TaskList extends ChatBotList<Task> {
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String DEADLINE_KEYWORD = "by";
    private static final String EVENT_KEYWORD = "at";

    private final Set<String> validTypes =
            Stream.of(TODO, DEADLINE, EVENT).collect(Collectors.toCollection(HashSet::new));

    /**
     * Instantiates a new Task list.
     */
    public TaskList() {
        super("task");
    }

    /**
     * Instantiates a new Task list with a given list of tasks.
     */
    public TaskList(List<Task> list) {
        super("task", list);
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
        handleAddErrorMessages(type, titleArgs, timestampArgs);

        String title = combineArgs(titleArgs);
        String other = combineArgs(timestampArgs);
        Task task;
        Timestamp timestamp;

        switch (type) {
        case DEADLINE:
            if (!timestampArgs[0].equals(DEADLINE_KEYWORD)) {
                throw new ChatBotException(
                        "The correct format for adding a deadline is "
                                + "deadline <name of task> /by <date or timestamp of task>"
                );
            }
            timestamp = new Timestamp(other);
            task = new Deadline(title, timestamp);
            break;
        case EVENT:
            if (!timestampArgs[0].equals(EVENT_KEYWORD)) {
                throw new ChatBotException(
                        "The correct format for adding an event is "
                                + "event <name of task> /at <date or timestamp of task>"
                );
            }
            timestamp = new Timestamp(other);
            task = new Event(title, timestamp);
            break;
        default:
            throw new ChatBotException();
        }
        insert(task);
        return String.format(
                "This %s has been added to your task list!%n             %d. %s",
                type, getNumItems(), task
        );
    }

    private void handleAddErrorMessages(String type, String[] titleArgs, String[] timestampArgs)
            throws ChatBotException {
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
            String keyword = type.equals(DEADLINE) ? DEADLINE_KEYWORD : EVENT_KEYWORD;
            if (timestampArgs.length == 1 && !timestampArgs[0].equals(keyword)) {
                throw new ChatBotException(String.format("You need to include /%s in your command to add a %s "
                        + "traveller!", keyword, type));
            }
            throw new ChatBotException(
                    String.format(
                            "You need to key in %s traveller!",
                            type.equals(DEADLINE)
                                    ? "the due date and time of your deadline"
                                    : "the timestamp of your event"
                    )
            );
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
        if (args.length <= 1) {
            throw new ChatBotException(
                    "You need to key in the title of your todo traveller!"
            );
        }

        String title = combineArgs(args);
        ToDo todo = new ToDo(title);
        insert(todo);
        return String.format(
                "This todo has been added to your task list!%n             %d. %s",
                getNumItems(),
                todo
        );
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
        return new TaskList(filtered).toString();
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
        return new TaskList(filtered).toString();
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
     * Marks the specified Task in the TaskList as complete.
     *
     * @param index The index of the task to be marked.
     * @return The response to be outputted by the UI.
     */
    public String mark(int index) {
        Task task = get(index);
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
        Task task = get(index);
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

    @Override
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
                    insert(new ToDo(title, done));
                    break;
                case "D":
                    insert(
                            new Deadline(title, done, new Timestamp(datetime))
                    );
                    break;
                case "E":
                    insert(
                            new Event(title, done, new Timestamp(datetime))
                    );
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

    @Override
    public void save(File saveFile) throws ChatBotException {
        List<Task> list = getList();
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
}

