import java.util.Scanner;

public class Duke {
    private static final String INDENTATION = "    ";
    private static final String DIVIDER = "____________________________________________________________";
    private static final String GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String GOODBYE = "Bye. Hope to see you again soon!";

    public static void main(String[] args) throws Exception {
        final Scanner input = new Scanner(System.in);
        final Task[] tasks = new Task[100];
        int nextItemIndex = 0;

        System.out.println(Duke.constructResponse(Duke.GREETING));
        try {
            while (true) {
                final String command = input.nextLine();
                switch (command) {
                    case "bye":
                        System.out.println(Duke.constructResponse(Duke.GOODBYE));
                        return;
                    case "list":
                        System.out.println(Duke.constructResponse(Duke.constructTextList(tasks)));
                        break;
                    default:
                        tasks[nextItemIndex++] = new Task(command);
                        System.out.println(Duke.constructResponse("added: " + command));
                }
            }
        } finally {
            input.close();
        }
    }

    private static String constructResponse(String content) {
        final String divider = Duke.INDENTATION + Duke.DIVIDER + "\n";
        final String response =
                Duke.INDENTATION + " " + content.replaceAll("\n", "\n " + Duke.INDENTATION) + "\n";
        return divider + response + divider;
    }

    private static <T> String constructTextList(T[] items) {
        String textList = "";
        int currentTextListItemIndex = 1;

        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) continue;
            if (currentTextListItemIndex > 1) textList += "\n";
            textList += currentTextListItemIndex + ". " + items[i];
            currentTextListItemIndex++;
        }

        return textList;
    }
}
