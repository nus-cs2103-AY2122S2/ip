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
import li.zhongfu.cs2103.chatbot.types.tasks.Deadline;
import li.zhongfu.cs2103.chatbot.types.tasks.Event;
import li.zhongfu.cs2103.chatbot.types.tasks.Task;
import li.zhongfu.cs2103.chatbot.types.tasks.ToDo;

public class Duke {
    private static final String HLINE = "____________________________________________________________";
    private static final String BOT_NAME = "Duke";

    // should probably be using some friendly date parser library for this
    // but maybe we can avoid external libraries for now?
    private static final 

    private static void dialog(String[] lines) {
        System.out.println("    " + HLINE);
        for (String line : lines) {
            System.out.println("    " + line);
        }
        System.out.println("    " + HLINE + "\n");
    }

    private static void dialog(String line) {
        dialog(line.split("\n"));
    }

    public static void main(String[] args) throws IOException {
        Parser parser = new Parser(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n");

        Storage storage = new Storage("data/tasks.dat");
        TaskList tasks;
        try {
            tasks = TaskList.loadFromStorage(storage);
            System.out.println(String.format("Loaded %s tasks.", tasks.size()));
        } catch (FileNotFoundException e) {
            System.out.println("No saved tasks file found, starting with an empty task list.");
            tasks = new TaskList();
        } catch (StorageException e) {
            System.out.println("Error loading tasks! Starting with an empty task list.");
            tasks = new TaskList();
        }

        dialog(new String[] {
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
                                dialog(new String[] {
                                    "Task removed:",
                                    String.format(" %s", task)
                                });
                            } else {
                                Task task = tasks.get(idx);
                                boolean done = "mark".equals(cmd); // set as done if cmd == "mark", else (e.g. cmd == "unmark") set as undone
                                task.setDone(done); 
                                dialog(new String[] {
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
                            dialog("You have no tasks in your task list!");
                        } else {
                            List<String> output = new ArrayList<>();
                            output.add("Here are the tasks in your list:");
                            output.addAll(tasks.toEnumeratedList());
                            dialog(output.toArray(String[]::new));
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
                        dialog(new String[] {
                            "New task added:",
                            String.format(" %s", task),
                            String.format("You now have %d tasks in your task list.", tasks.size())
                        });
                        break;

                    case "reload":
                        try {
                            tasks = TaskList.loadFromStorage(storage);
                            dialog(String.format("Tasks reloaded; %d tasks in list now", tasks.size()));
                        } catch (FileNotFoundException e) {
                            dialog("Saved tasks file not found! Skipping reload.");
                        } catch (StorageException e) {
                            dialog("Error loading tasks! Skipping reload.");
                        }
                        break;
                    
                    case "save":
                        tasks.save(storage);
                        dialog("Tasks saved!");
                        break;

                    case "bye":
                        tasks.save(storage);
                        dialog("Bye. Hope to see you again soon!");
                        return;

                    default:
                        throw new DukeException("I don't know what that means! Try: todo, deadline, event, list, delete, mark, unmark, reload, save, bye");
                }
            } catch (DukeException e) {
                dialog(e.getMessage().split("\n"));
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