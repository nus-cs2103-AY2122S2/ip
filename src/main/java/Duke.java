import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private enum Dialogue {
        GREETING, FAREWELL
    }
    private enum Styling {
        LINE
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(Duke.speak(Dialogue.GREETING));
        Scanner sc = new Scanner(System.in);
        List<Task> todo = new ArrayList<>();
        while (true) {
            String input = sc.nextLine();
            System.out.println(style(Styling.LINE));
            if (input.equals("bye")) {
                System.out.println(Duke.speak(Dialogue.FAREWELL));
                break;
            } else if (input.equals("list")) {
                Integer count = 1;
                for (int i = 0; i < todo.size(); i++) {
                    System.out.printf("%d. %s\n", count, todo.get(i));
                    count++;
                }
            } else {
                todo.add(new Task(input));
                System.out.println("added: " + input);
            }

            System.out.println(style(Styling.LINE));
        }
    }

    public static String speak(Dialogue option) {
        String reply;
        switch (option) {
            case GREETING: reply =  "You again.\nWhat do you want this time?";
                break;
            case FAREWELL: reply = "Thank god. I thought it'll never end.\nI'm going to pretend I don't recognize you next time.";
                break;
            default: reply = "Are you finally done?";
                break;
        }
        return reply;
    }

    public static String style(Styling option) {
        String style;
        switch (option) {
            case LINE: style =  "_____________________________________";
                break;
            default: style = "";
                break;
        }
        return style;
    }
}
