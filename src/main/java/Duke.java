import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String GREET_MESSAGE
            = "Roarrr.... I'm Burning Charizard, tasked to burnnn down your tasks.\n" +
            "Which task shall we burn today?";
    private static final String BYE_MESSAGE = "Roarrr....Let's burn more tasks next time!";
    private static final int BORDER_LENGTH = 70;
    private static ArrayList<Task> tasks;

    public static void main(String[] args) {
        tasks = new ArrayList<Task>();
        greet();
        Scanner sc = new Scanner(System.in);
        System.out.println("What should Charizard do next?");
        String input = sc.nextLine();
        while (input.compareToIgnoreCase("bye") != 0) {
            processInput(input);
            System.out.println("What should Charizard do next?");
            input = sc.nextLine();
        }
        exit();
    }

    public static void show(String message) {
        drawBorder(BORDER_LENGTH);
        String[] lines = message.split("\n");
        for (String line: lines) {
            System.out.println("    " + line);
        }
        drawBorder(BORDER_LENGTH);
        System.out.println();
    }

    public static void processInput(String input) {
        String message = "";
        if (input.equalsIgnoreCase("list")) {
            message = "Charizard's burning wish list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                message += String.format("%d. %s", i + 1, tasks.get(i).toString());
                if (i < tasks.size() - 1) {
                    message += "\n";
                }
            }
        } else if (input.startsWith("mark")) {
            String inputWithoutCommand = input.replaceFirst("mark", "").trim();
            int taskId = Integer.parseInt(inputWithoutCommand) - 1;
            tasks.get(taskId).mark(true);
            message = String.format("Charizard breathe out fire and burned the task.");
            message += String.format("\n  %s", tasks.get(taskId).toString());
        } else if (input.startsWith("unmark")) {
            String inputWithoutCommand = input.replaceFirst("unmark", "").trim();
            int taskId = Integer.parseInt(inputWithoutCommand) - 1;
            tasks.get(taskId).mark(false);
            message = String.format("Oh no! The task was not burnt completely!");
            message += String.format("\n  %s", tasks.get(taskId).toString());
        } else if (input.startsWith("todo")) {
            String description = input.replaceFirst("todo", "").trim();
            ToDo newTodo = new ToDo(description);
            tasks.add(newTodo);
            message = String.format("Charizard is ready to burn task:\n  %s\nThere are %d tasks in the burning list",
                    newTodo.toString(), tasks.size());
        } else if (input.startsWith("deadline")) {
            String inputWithoutCommand = input.replaceFirst("todo", "").trim();
            String description = inputWithoutCommand.split("/by")[0].trim();
            String deadline = inputWithoutCommand.split("/by")[1].trim();
            Deadline newDeadline = new Deadline(description, deadline);
            tasks.add(newDeadline);
            message = String.format("Charizard is ready to burn task:\n  %s\nThere are %d tasks in the burning list",
                    newDeadline.toString(), tasks.size());
        } else if (input.startsWith("event")) {
            String inputWithoutCommand = input.replaceFirst("event", "").trim();
            String description = inputWithoutCommand.split("/at")[0].trim();
            String time = inputWithoutCommand.split("/at")[1].trim();
            Event newEvent = new Event(description, time);
            tasks.add(newEvent);
            message = String.format("Charizard is ready to burn task:\n  %s\nThere are %d tasks in the burning list",
                    newEvent.toString(), tasks.size());
        }
        show(message);
    }

    public static void exit() {
        drawBorder(BORDER_LENGTH);
        System.out.println(BYE_MESSAGE);
        drawBorder(BORDER_LENGTH);
    }

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        drawBorder(BORDER_LENGTH);
        System.out.println(GREET_MESSAGE);
        drawBorder(BORDER_LENGTH);
        System.out.println();
    }

    private static void drawBorder(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("*");
        };
        System.out.println("");
    }
}
