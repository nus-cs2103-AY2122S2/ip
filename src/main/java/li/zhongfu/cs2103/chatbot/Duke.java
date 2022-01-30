package li.zhongfu.cs2103.chatbot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import li.zhongfu.cs2103.chatbot.exceptions.StorageException;
import li.zhongfu.cs2103.chatbot.types.Parser;
import li.zhongfu.cs2103.chatbot.types.ParserResult;
import li.zhongfu.cs2103.chatbot.types.Storage;
import li.zhongfu.cs2103.chatbot.types.TaskList;
import li.zhongfu.cs2103.chatbot.types.UserInterface;
import li.zhongfu.cs2103.chatbot.types.tasks.Deadline;
import li.zhongfu.cs2103.chatbot.types.tasks.Event;
import li.zhongfu.cs2103.chatbot.types.tasks.Task;
import li.zhongfu.cs2103.chatbot.types.tasks.ToDo;

public class Duke {
    private final String botName;

    private UserInterface ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String botName, String filePath) {
        this.botName = botName;
        this.ui = new UserInterface(System.in, System.out);
        this.storage = new Storage(filePath);
        this.init();
    }

    public Duke(String botName) {
        this(botName, "data/tasks.dat");
    }

    private void init() {
        this.ui.printMotd();
        try {
            this.tasks = TaskList.loadFromStorage(this.storage);
            this.ui.println(String.format("Loaded %s tasks.", this.tasks.size()));
        } catch (FileNotFoundException e) {
            this.ui.println("No saved tasks file found, starting with an empty task list.");
            this.tasks = new TaskList();
        } catch (StorageException e) {
            this.ui.println("Error loading tasks! Starting with an empty task list.");
            this.tasks = new TaskList();
        }

        this.ui.printDialog(new String[] {
                String.format("Hello! I'm %s", this.botName),
                "How can I help you?"
        });
    }

    public void loop() throws IOException {
        while (true) {
            try {
                ParserResult result = this.ui.getInput();
                String cmd = result.getCmd();
                switch (cmd) {
                    case "mark":
                    case "unmark":
                    case "delete":
                        try {
                            int idx = Integer.parseInt(result.getPosArg()) - 1; // list is 1-indexed

                            if ("delete".equals(cmd)) {
                                Task task = this.tasks.remove(idx);
                                this.ui.printDialog(new String[] {
                                    "Task removed:",
                                    String.format(" %s", task)
                                });
                            } else {
                                boolean done = "mark".equals(cmd); // set as done if cmd == "mark", else (e.g. cmd == "unmark") set as undone
                                Task task = this.tasks.get(idx);
                                task.setDone(done); 
                                this.ui.printDialog(new String[] {
                                    String.format("Task marked as %sdone:", done ? "" : "un"),
                                    String.format(" %s", task)
                                });
                            }
                        } catch (NumberFormatException e) {
                            throw new DukeException(String.format("Hmm, that doesn't seem like a valid task number! Try '%s <number>'.", cmd), e);
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("That task doesn't exist!", e);
                        }
                        break;

                    case "list":
                        if (this.tasks.isEmpty()) {
                            this.ui.printDialog("You have no tasks in your task list!");
                        } else {
                            List<String> output = new ArrayList<>();
                            output.add("Here are the tasks in your list:");
                            output.addAll(this.tasks.toEnumeratedList());
                            this.ui.printDialog(output.toArray(String[]::new));
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
                                throw new DukeException(String.format("You need a time for your deadline! Try: deadline %s /by 1 jan 2050 12:15", result.getPosArg()));
                            }

                            try {
                                task = new Deadline(result.getPosArg(), this.ui.parseDateTime(result.getArg("by")));
                            } catch (DateTimeParseException e) {
                                throw new DukeException(String.format("Unknown date format! Try: deadline %s /by 1 jan 2050 12:15", result.getPosArg()));
                            }
                        } else if ("event".equals(cmd)) {
                            if (!result.hasNonblankArg("at")) {
                                throw new DukeException(String.format("You need a time for your event! Try: event %s /at 1 jan 2050 12:15", result.getPosArg()));
                            }

                            try {
                                task = new Event(result.getPosArg(), this.ui.parseDateTime(result.getArg("at")));
                            } catch (DateTimeParseException e) {
                                throw new DukeException(String.format("Unknown date format! Try: event %s /at 1 jan 2050 12:15", result.getPosArg()));
                            }
                        } else {
                            task = new ToDo(result.getPosArg());
                        }
                        
                        this.tasks.add(task);
                        this.ui.printDialog(new String[] {
                            "New task added:",
                            String.format(" %s", task),
                            String.format("You now have %d tasks in your task list.", this.tasks.size())
                        });
                        break;

                    case "reload":
                        try {
                            this.tasks = TaskList.loadFromStorage(this.storage);
                            this.ui.printDialog(String.format("Tasks reloaded; %d tasks in list now", this.tasks.size()));
                        } catch (FileNotFoundException e) {
                            this.ui.printDialog("Saved tasks file not found! Skipping reload.");
                        } catch (StorageException e) {
                            this.ui.printDialog("Error loading tasks! Skipping reload.");
                        }
                        break;
                    
                    case "save":
                        this.tasks.save(this.storage);
                        this.ui.printDialog("Tasks saved!");
                        break;

                    case "bye":
                        this.tasks.save(this.storage);
                        this.ui.printDialog("Bye. Hope to see you again soon!");
                        return;

                    default:
                        throw new DukeException("I don't know what that means! Try: todo, deadline, event, list, delete, mark, unmark, reload, save, bye");
                }
            } catch (DukeException e) {
                this.ui.printDialog(e.getMessage().split("\n"));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Duke duke = new Duke("Duke");
        duke.loop();
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