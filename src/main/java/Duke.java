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
            message += String.format("\n    %s", tasks.get(taskId).toString());
        } else if (input.startsWith("unmark")) {
            String inputWithoutCommand = input.replaceFirst("unmark", "").trim();
            int taskId = Integer.parseInt(inputWithoutCommand) - 1;
            tasks.get(taskId).mark(false);
            message = String.format("Oh no! The task was not burnt completely!");
            message += String.format("\n    %s", tasks.get(taskId).toString());
        } else {
            Task newTask = new Task(input);
            tasks.add(newTask);
            message = String.format("Charizard is ready to burn task: %s", newTask.getDescription());
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
    }

    private static void drawBorder(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("*");
        };
        System.out.println("");
    }
}
