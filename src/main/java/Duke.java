import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private enum Dialogue {
        GREETING, FAREWELL, LIST, MARK, UNMARK
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
            String input = sc.next();
            System.out.println(style(Styling.LINE));
            if (input.equals("bye")) {
                System.out.println(Duke.speak(Dialogue.FAREWELL));
                break;
            } else if (input.equals("list")) {
                System.out.println(Duke.speak(Dialogue.LIST));
                Integer count = 1;
                for (int i = 0; i < todo.size(); i++) {
                    System.out.printf("%d. %s\n", count, todo.get(i));
                    count++;
                }
            } else if (input.equals("mark")) {
                System.out.println(Duke.speak(Dialogue.MARK));
                Integer index = sc.nextInt();
                Task t = todo.get(index-1);
                t.mark();
                System.out.println(t);
            } else if (input.equals("unmark")) {
                System.out.println(Duke.speak(Dialogue.UNMARK));
                Integer index = sc.nextInt();
                Task t = todo.get(index-1);
                t.unmark();
                System.out.println(t);
            } else {
                input += sc.nextLine();
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
            case MARK: reply = "Took you long enough.\n";
                break;
            case UNMARK: reply = "Huh. Must have messed up again didn't you.\n";
                break;
            case LIST: reply = "Here are some menial tasks you've decided to waste your life on.\n";
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
