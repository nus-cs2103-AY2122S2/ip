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
        for (String line: lines) {
            System.out.println("    " + line);
        }
        System.out.println("    " + HLINE + "\n");
    }
    
    /**
     * Takes a list of items and returns a list of strings representing the items in an (1-indexed) ordered list.
     * 
     * Each string in the list is of the form {@code n. foo}, where {@code n} is the index of the item (starting from 1)
     * and {@code foo} is the string representation of the item.
     * 
     * @param <T> the type of items in {@code items}
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

        dialog(new String[]{
            String.format("Hello! I'm %s", BOT_NAME),
            "How can I help you?"
        });

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Task> tasks = new ArrayList<>();
        while (true) {
           String input = br.readLine();
           switch (input) {
               case "list":
                   dialog(enumerateList(tasks).toArray(String[]::new));
                   break;

               case "bye":
                   dialog(new String[] {
                           "Bye. Hope to see you again soon!"
                   });
                   return; // to shut the linter up

               default:
                   Task task = new Task(input);
                   tasks.add(task);
                   dialog(new String[] {
                           String.format("added: %s", task)
                        });
           }
        }
    }
}