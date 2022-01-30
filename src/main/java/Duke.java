import java.util.Scanner;

public class Duke {
    static WordList wordList;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        wordList = new WordList();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        replyWelcomeMessage();
        String input;
        while (true) {
            input = sc.nextLine();
            if (input.isEmpty()) {
                warnEmpty();
                continue;
            }

            Object[] parseResult = InputParser.parseInput(input);
            InputType inputType = (InputType) parseResult[0];
            String[] value = (String[]) parseResult[1];

            processInput(inputType, value);
            if (inputType == InputType.BYE) {
                break;
            }
        }
    }

    public static void processInput(InputType inputType, String[] value) {
        switch(inputType) {
            case LIST:
                wordList.printList();
                break;
            case MARK:
                wordList.markItem(Integer.parseInt(value[0]));
                break;
            case UNMARK:
                wordList.unmarkItem(Integer.parseInt(value[0]));
                break;
            case TODO:
                wordList.storeTodo(value[0]);
                break;
            case DEADLINE:
                wordList.storeDeadline(value[0], value[1]);
                break;
            case EVENT:
                wordList.storeEvent(value[0], value[1]);
                break;
            case BYE:
                replyBye();
                break;
            case NONE:
                break;
        }
    }

    public static void replyWelcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void warnEmpty() {
        System.out.println("input is Empty!");
    }
    public static void replyBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
