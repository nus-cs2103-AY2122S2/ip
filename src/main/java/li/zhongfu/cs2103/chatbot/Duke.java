package li.zhongfu.cs2103.chatbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Duke {
    private static final String HLINE = "____________________________________________________________";
    private static final String BOT_NAME = "Duke";

    private static void dialog(String[] lines) {
        System.out.println("    " + HLINE);
        for (String line : lines) {
            System.out.println("    " + line);
        }
        System.out.println("    " + HLINE + "\n");
    }

    private static void dialog(String line) {
        dialog(new String[]{line});
    }

    /**
     * Takes a list of items and returns a list of strings representing the items in
     * an (1-indexed) ordered list.
     * 
     * Each string in the list is of the form {@code n. foo}, where {@code n} is the
     * index of the item (starting from 1)
     * and {@code foo} is the string representation of the item.
     * 
     * @param <T>   the type of items in {@code items}
     * @param items the list of items to be enumerated
     * @return a list of strings representing the items in an 1-indexed ordered list
     */
    private static <T> List<String> enumerateList(List<T> items) {
        List<String> enumerated = new ArrayList<>();
        int idx = 0;
        for (T item : items) {
            enumerated.add(String.format("%d. %s", ++idx, item));
        }
        return enumerated;
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
        System.out.println("Hello from\n" + logo);

        dialog(new String[] {
                String.format("Hello! I'm %s", BOT_NAME),
                "How can I help you?"
        });

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Task> tasks = new ArrayList<>();
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
                            output.addAll(enumerateList(tasks));
                            dialog(output.toArray(String[]::new));
                        }
                        break;

                    case "bye":
                        dialog("Bye. Hope to see you again soon!");
                        return; // to shut the linter up

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
                                throw new DukeException(String.format("You need a time for your deadline! Try: deadline %s /by next Sunday", taskArgs.get("")));
                            }
                            task = new Deadline(taskArgs.get(""), taskArgs.get("by"));
                        } else if (inCmd.equals("event")) {
                            if (!taskArgs.containsKey("at") || taskArgs.get("at").isEmpty()) {
                                throw new DukeException(String.format("You need a time for your event! Try: event %s /at next Sunday", taskArgs.get("")));
                            }
                            task = new Event(taskArgs.get(""), taskArgs.get("at"));
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

                    default:
                        throw new DukeException("I don't know what that means! Try: todo, deadline, event, list, delete, mark, unmark, bye");
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