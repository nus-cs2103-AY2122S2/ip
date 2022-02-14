package li.zhongfu.cs2103.chatbot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import li.zhongfu.cs2103.chatbot.exceptions.StorageException;
import li.zhongfu.cs2103.chatbot.types.Storage;
import li.zhongfu.cs2103.chatbot.types.TaskList;
import li.zhongfu.cs2103.chatbot.types.message.ChatMessage;
import li.zhongfu.cs2103.chatbot.types.message.Message;
import li.zhongfu.cs2103.chatbot.types.message.Participant;
import li.zhongfu.cs2103.chatbot.types.message.QuitMessage;
import li.zhongfu.cs2103.chatbot.types.message.SystemMessage;
import li.zhongfu.cs2103.chatbot.types.parser.Parser;
import li.zhongfu.cs2103.chatbot.types.parser.ParserResult;
import li.zhongfu.cs2103.chatbot.types.tasks.Deadline;
import li.zhongfu.cs2103.chatbot.types.tasks.Event;
import li.zhongfu.cs2103.chatbot.types.tasks.Task;
import li.zhongfu.cs2103.chatbot.types.tasks.ToDo;

/**
 * A chat bot.
 */
public class Duke {
    private final String botName;

    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    private Participant dukePartipant;
    private Participant userParticipant;

    private boolean init = false;

    /**
     * Creates a new Duke instance with the given chatbot name and Task storage path.
     *
     * @param botName the name of the chatbot
     * @param filePath the path for Task persistent storage
     */
    public Duke(String botName, String filePath) {
        this.botName = botName;
        storage = new Storage(filePath);
        parser = new Parser();
        dukePartipant = Participant.getDukeParticipant();
        userParticipant = Participant.getUserParticipant();
    }

    /**
     * Creates a new Duke instance with the given chatbot name.
     *
     * Uses the default Task storage path.
     *
     * @param botName the name of the chatbot
     */
    public Duke(String botName) {
        this(botName, "data/tasks.dat");
    }

    public List<Message> init() {
        assert !init : "Attempting to init Duke more than once";

        List<Message> messages = new ArrayList<>();
        try {
            this.tasks = TaskList.loadFromStorage(this.storage);
            messages.add(new SystemMessage(String.format("Loaded %s tasks.", this.tasks.size())));
        } catch (FileNotFoundException e) {
            messages.add(new SystemMessage("No saved tasks file found, starting with an empty task list."));
            this.tasks = new TaskList();
        } catch (StorageException e) {
            messages.add(new SystemMessage("Error loading tasks! Starting with an empty task list."));
            this.tasks = new TaskList();
        }

        messages.add(new ChatMessage(
                String.format("Hello! I'm %s%n", this.botName) + "How can I help you?",
                dukePartipant,
                userParticipant));

        init = true;

        return messages;
    }

    public List<Message> handleInput(String input) throws IOException {
        if (!init) {
            throw new RuntimeException("Duke not initialized yet");
        }

        List<Message> messages = new ArrayList<>();

        try {
            ParserResult result = parser.parseInput(input);
            String cmd = result.getCmd();
            switch (cmd) {
            case "mark":
            case "unmark":
            case "delete":
                try {
                    int idx = Integer.parseInt(result.getPosArg()) - 1; // list is 1-indexed

                    if ("delete".equals(cmd)) {
                        Task task = this.tasks.remove(idx);
                        messages.add(new ChatMessage(String.format("Task removed:%n %s", task)));
                    } else {
                        // set as done if cmd == "mark", else (e.g. cmd == "unmark") set as undone
                        boolean done = "mark".equals(cmd);
                        Task task = this.tasks.get(idx);
                        task.setDone(done);
                        messages.add(new ChatMessage(String.format(
                                "Task marked as %sdone:%n %s",
                                done ? "" : "un",
                                task)));
                    }
                } catch (NumberFormatException e) {
                    throw new DukeException(
                            String.format(
                                    "Hmm, that doesn't seem like a valid task number! Try '%s <number>'.",
                                    cmd),
                            e);
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("That task doesn't exist!", e);
                }
                break;

            case "list":
                if (this.tasks.isEmpty()) {
                    messages.add(new ChatMessage("You have no tasks in your task list!"));
                } else {
                    List<String> output = new ArrayList<>();
                    output.add("Here are the tasks in your list:");
                    output.addAll(this.tasks.toEnumeratedList());
                    messages.add(new ChatMessage(output.stream().collect(Collectors.joining("\n"))));
                }
                break;

            case "find":
                if (!result.hasNonblankPosArg()) {
                    throw new DukeException("No search query provided! Try 'find meeting'.");
                } else {
                    TaskList matchingTasks = this.tasks.find(result.getPosArg());
                    List<String> output = new ArrayList<>();
                    if (matchingTasks.isEmpty()) {
                        output.add("No tasks with that query found!");
                    } else {
                        output.add(String.format("Tasks containing '%s':", result.getPosArg()));
                        output.addAll(matchingTasks.toEnumeratedList());
                    }
                    messages.add(new ChatMessage(output.stream().collect(Collectors.joining(System.lineSeparator()))));
                }
                break;

            case "todo":
            case "deadline":
            case "event":
                Task task;
                if (!result.hasNonblankPosArg()) { // including strings with only whitespaces
                    throw new DukeException("Task name cannot be empty!");
                }

                if ("deadline".equals(cmd)) {
                    if (!result.hasNonblankArg("by")) {
                        throw new DukeException(String.format(
                                "You need a time for your deadline! Try: deadline %s /by 1 jan 2050 12:15",
                                result.getPosArg()));
                    }

                    try {
                        task = new Deadline(result.getPosArg(), this.parser.parseDateTime(result.getArg("by")));
                    } catch (DateTimeParseException e) {
                        throw new DukeException(String.format(
                                "Unknown date format! Try: deadline %s /by 1 jan 2050 12:15",
                                result.getPosArg()));
                    }
                } else if ("event".equals(cmd)) {
                    if (!result.hasNonblankArg("at")) {
                        throw new DukeException(String.format(
                                "You need a time for your event! Try: event %s /at 1 jan 2050 12:15",
                                result.getPosArg()));
                    }

                    try {
                        task = new Event(result.getPosArg(), this.parser.parseDateTime(result.getArg("at")));
                    } catch (DateTimeParseException e) {
                        throw new DukeException(String.format(
                                "Unknown date format! Try: event %s /at 1 jan 2050 12:15",
                                result.getPosArg()));
                    }
                } else {
                    task = new ToDo(result.getPosArg());
                }

                this.tasks.add(task);
                messages.add(new ChatMessage(String.format(
                        "New task added:%n %s%nYou now have %d tasks in your task list.",
                        task,
                        this.tasks.size())));
                break;

            case "reload":
                try {
                    this.tasks = TaskList.loadFromStorage(this.storage);
                    messages.add(new ChatMessage(String.format("Tasks reloaded; %d tasks in list now", this.tasks.size())));
                } catch (FileNotFoundException e) {
                    messages.add(new ChatMessage("Saved tasks file not found! Skipping reload."));
                } catch (StorageException e) {
                    messages.add(new ChatMessage("Error loading tasks! Skipping reload."));
                }
                break;

            case "save":
                this.tasks.save(this.storage);
                messages.add(new ChatMessage("Tasks saved!"));
                break;

            case "bye":
                this.tasks.save(this.storage);
                messages.add(new ChatMessage("Bye. Hope to see you again soon!"));
                messages.add(new QuitMessage());
                break;

            default:
                throw new DukeException("I don't know what that means! "
                        + "Try: todo, deadline, event, list, find, delete, mark, unmark, reload, save, bye");
            }
        } catch (DukeException e) {
            messages.add(new ChatMessage(e.getMessage()));
        }

        return messages;
    }
}

class DukeException extends Exception {
    DukeException() {

    }

    DukeException(String message) {
        super(message);
    }

    DukeException(String message, Throwable cause) {
        super(message, cause);
    }
}
