import java.util.Scanner;

public class Duke {
    public enum Dialogue {
        GREETING, FAREWELL
    }

    public enum Styling {
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
        while (true) {
            String input = sc.next();
            System.out.println(style(Styling.LINE));
            if (input.equals("bye")) {
                System.out.println(Duke.speak(Dialogue.FAREWELL));
                break;
            } else {
                System.out.println(input);
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
