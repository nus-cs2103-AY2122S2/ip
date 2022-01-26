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
import li.zhongfu.cs2103.chatbot.types.Deadline;
import li.zhongfu.cs2103.chatbot.types.Event;
import li.zhongfu.cs2103.chatbot.types.Storage;
import li.zhongfu.cs2103.chatbot.types.Task;
import li.zhongfu.cs2103.chatbot.types.TaskList;
import li.zhongfu.cs2103.chatbot.types.ToDo;

public class Duke {
    private static final String HLINE = "____________________________________________________________";
    private static final String BOT_NAME = "Duke";

    // should probably be using some friendly date parser library for this
    // but maybe we can avoid external libraries for now?
    private static final DateTimeFormatter DATE_TIME_PARSE_FORMAT = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            .appendPattern("[yyyy-MM-dd[ HH:mm[:ss]]]")
            .appendPattern("[MMM d yyyy[ HH:mm[:ss]]]")
            .appendPattern("[d MMM yyyy[ HH:mm[:ss]]]")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter();

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

    /**
     * Parses a string in the form {@code positional argument /arg1 foo bar /arg2 baz bax /arg3}
     * and returns a {@code Map<String, String>} containing the parsed arguments, e.g.:
     * 
     * <pre>{
     *      "": "positional argument",
     *      "arg1": "foo bar",
     *      "arg2": "baz bax",
     *      "arg3": ""
     * }</pre>
     * 
     * Empty argument names will be dropped; other duplicate argument names will have the value of
     * the last occurrence of the argument.
     * 
     * @param args a string containing unparsed arguments
     * @return a Map containing parsed arguments
     */
    private static Map<String, String> parseArgString(String taskString) {
        Map<String, String> args = new HashMap<>();
        String[] parts = taskString.split("(^|\\s+)/");
        args.put("", parts[0]);

        for (int i = 1; i < parts.length; i++) {
            String[] arg = parts[i].split("\\s+", 2);
            if (arg[0] == "") {
                continue;
            }
            args.put(arg[0], arg.length == 2 ? arg[1]: "");
        }

        return args;
    }

    public static void main(String[] args) throws IOException {
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

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                String input = br.readLine();
                String[] parts = input.split("\\s", 2); // split into command and args
                String inCmd = parts[0];
                String inArgs = parts.length == 2 ? parts[1] : "";
                switch (inCmd) {
                    case "mark":
                    case "unmark":
                    case "delete":
                        try {
                            int idx = Integer.parseInt(inArgs) - 1; // list is 1-indexed

                            if (inCmd.equals("delete")) {
                                Task task = tasks.remove(idx);
                                dialog(new String[] {
                                    "Task removed:",
                                    String.format(" %s", task)
                                });
                            } else {
                                Task task = tasks.get(idx);
                                boolean done = inCmd.equals("mark"); // set as done if cmd == "mark", else (e.g. cmd == "unmark") set as undone
                                task.setDone(done); 
                                dialog(new String[] {
                                    String.format("Task marked as %sdone:", done ? "" : "un"),
                                    String.format(" %s", task)
                                });
                            }
                        } catch (NumberFormatException e) {
                            throw new DukeException(String.format("Hmm, that doesn't seem like a valid task number! Try '%s <number>'.", inCmd), e);
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
                        Map<String, String> taskArgs = parseArgString(inArgs);
                        if (taskArgs.get("").isBlank()) { // includes strings with only whitespaces
                            throw new DukeException("Task name cannot be empty!");
                        }

                        if (inCmd.equals("deadline")) {
                            if (!taskArgs.containsKey("by") || taskArgs.get("by").isEmpty()) {
                                throw new DukeException(String.format("You need a time for your deadline! Try: deadline %s /by 1 jan 2050 12:15", taskArgs.get("")));
                            }

                            try {
                                LocalDateTime parsedDate = LocalDateTime.parse(taskArgs.get("by"), DATE_TIME_PARSE_FORMAT);
                                task = new Deadline(taskArgs.get(""), parsedDate);
                            } catch (DateTimeParseException e) {
                                throw new DukeException(String.format("Unknown date format! Try: deadline %s /by 1 jan 2050 12:15", taskArgs.get("")));
                            }
                        } else if (inCmd.equals("event")) {
                            if (!taskArgs.containsKey("at") || taskArgs.get("at").isEmpty()) {
                                throw new DukeException(String.format("You need a time for your event! Try: event %s /at 1 jan 2050 12:15", taskArgs.get("")));
                            }

                            try {
                                LocalDateTime parsedDate = LocalDateTime.parse(taskArgs.get("at"), DATE_TIME_PARSE_FORMAT);
                                task = new Event(taskArgs.get(""), parsedDate);
                            } catch (DateTimeParseException e) {
                                throw new DukeException(String.format("Unknown date format! Try: event %s /at 1 jan 2050 12:15", taskArgs.get("")));
                            }
                        } else {
                            task = new ToDo(taskArgs.get(""));
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