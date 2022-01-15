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
        if (input.equalsIgnoreCase("list")) {
            String message = "Charizard's burning wish list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                message += String.format("%d. %s", i, tasks.get(i).toString());
                if (i < tasks.size() - 1) {
                    message += "\n";
                }
            }
            show(message);
        } else {
            Task newTask = new Task(input);
            tasks.add(newTask);
            show(String.format("Charizard is ready to burn task: %s", newTask.toString()));
        }
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
