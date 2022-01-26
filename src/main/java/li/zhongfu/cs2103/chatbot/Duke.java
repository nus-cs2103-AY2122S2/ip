package li.zhongfu.cs2103.chatbot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        private static final String BOT_NAME = "Duke";

    public static void main(String[] args) throws IOException {
        UserInterface ui = new UserInterface(System.out);
        ui.printMotd();
        Parser parser = new Parser(System.in);

        Storage storage = new Storage("data/tasks.dat");
        TaskList tasks;
        try {
            tasks = TaskList.loadFromStorage(storage);
            ui.println(String.format("Loaded %s tasks.", tasks.size()));
        } catch (FileNotFoundException e) {
            ui.println("No saved tasks file found, starting with an empty task list.");
            tasks = new TaskList();
        } catch (StorageException e) {
            ui.println("Error loading tasks! Starting with an empty task list.");
            tasks = new TaskList();
        }

        ui.printDialog(new String[] {
                String.format("Hello! I'm %s", BOT_NAME),
                "How can I help you?"
        });

        
        while (true) {
            try {
                ParserResult result = parser.parseNext();
                String cmd = result.getCmd();
                switch (cmd) {
                    case "mark":
                    case "unmark":
                    case "delete":
                        try {
                            int idx = Integer.parseInt(result.getPosArg()) - 1; // list is 1-indexed

                            if ("delete".equals(cmd)) {
                                Task task = tasks.remove(idx);
                                ui.printDialog(new String[] {
                                    "Task removed:",
                                    String.format(" %s", task)
                                });
                            } else {
                                Task task = tasks.get(idx);
                                boolean done = "mark".equals(cmd); // set as done if cmd == "mark", else (e.g. cmd == "unmark") set as undone
                                task.setDone(done); 
                                ui.printDialog(new String[] {
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
                        if (tasks.isEmpty()) {
                            ui.printDialog("You have no tasks in your task list!");
                        } else {
                            List<String> output = new ArrayList<>();
                            output.add("Here are the tasks in your list:");
                            output.addAll(tasks.toEnumeratedList());
                            ui.printDialog(output.toArray(String[]::new));
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
                                task = new Deadline(result.getPosArg(), parser.parseDateTime(result.getArg("by")));
                            } catch (DateTimeParseException e) {
                                throw new DukeException(String.format("Unknown date format! Try: deadline %s /by 1 jan 2050 12:15", result.getPosArg()));
                            }
                        } else if ("event".equals(cmd)) {
                            if (!result.hasNonblankArg("at")) {
                                throw new DukeException(String.format("You need a time for your event! Try: event %s /at 1 jan 2050 12:15", result.getPosArg()));
                            }

                            try {
                                task = new Event(result.getPosArg(), parser.parseDateTime(result.getArg("at")));
                            } catch (DateTimeParseException e) {
                                throw new DukeException(String.format("Unknown date format! Try: event %s /at 1 jan 2050 12:15", result.getPosArg()));
                            }
                        } else {
                            task = new ToDo(result.getPosArg());
                        }
                        
                        tasks.add(task);
                        ui.printDialog(new String[] {
                            "New task added:",
                            String.format(" %s", task),
                            String.format("You now have %d tasks in your task list.", tasks.size())
                        });
                        break;

                    case "reload":
                        try {
                            tasks = TaskList.loadFromStorage(storage);
                            ui.printDialog(String.format("Tasks reloaded; %d tasks in list now", tasks.size()));
                        } catch (FileNotFoundException e) {
                            ui.printDialog("Saved tasks file not found! Skipping reload.");
                        } catch (StorageException e) {
                            ui.printDialog("Error loading tasks! Skipping reload.");
                        }
                        break;
                    
                    case "save":
                        tasks.save(storage);
                        ui.printDialog("Tasks saved!");
                        break;

                    case "bye":
                        tasks.save(storage);
                        ui.printDialog("Bye. Hope to see you again soon!");
                        return;

                    default:
                        throw new DukeException("I don't know what that means! Try: todo, deadline, event, list, delete, mark, unmark, reload, save, bye");
                }
            } catch (DukeException e) {
                ui.printDialog(e.getMessage().split("\n"));
            }
        }
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