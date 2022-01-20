package li.zhongfu.cs2103.chatbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
            String input = br.readLine();
            String[] parts = input.split("\\s", 2); // split into command and args
            String inCmd = parts[0];
            String inArgs = parts.length == 2 ? parts[1] : "";
            switch (inCmd) {
                case "mark":
                case "unmark":
                    try {
                        int idx = Integer.parseInt(inArgs) - 1; // list is 1-indexed
                        Task task = tasks.get(idx);
                        boolean done = inCmd.equals("mark"); // set as done if cmd == "mark", else (e.g. cmd == "unmark") set as undone
                        task.setDone(done); 
                        dialog(new String[] {
                            String.format("Task marked as %sdone:", done ? "" : "un"),
                            String.format(" %s", task)
                        });
                    } catch (NumberFormatException e) {
                        dialog(String.format("Hmm, that doesn't seem like a valid task number! Try '%s <number>'.", inCmd));
                    } catch (IndexOutOfBoundsException e) {
                        dialog("That task doesn't exist!");
                    }
                    break;

                case "list":
                    if (tasks.isEmpty()) {
                        dialog("You have no tasks on your to-do list!");
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

                // default action: add entire input as task
                default:
                    Task task = new Task(input);
                    tasks.add(task);
                    dialog(new String[] {
                        "New task added:",
                        String.format(" %s", task)
                    });
            }
        }
    }
}