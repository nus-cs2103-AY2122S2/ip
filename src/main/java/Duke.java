import java.util.Scanner;

public class Duke {
    static final String BYE = "bye";
    static final String LIST = "list";
    static final String MARK = "mark";
    static final String UNMARK = "unmark";
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
            if (isList(input)) {
                wordList.printList();
                continue;
            }

            if (isMark(input)) {
                int itemNumber = Integer.parseInt(input.substring(5));
                wordList.markItem(itemNumber);
                continue;
            }

            if (isUnmark(input)) {
                int itemNumber = Integer.parseInt(input.substring(7));
                wordList.unmarkItem(itemNumber);
                continue;
            }

            if (isBye(input)) {
                break;
            }

            echo(input);
            wordList.storeWord(input);
        }
        replyBye();
    }

    public static void echo(String words) {
        System.out.println("------------------------------------");
        System.out.println("added: " + words);
        System.out.println("------------------------------------");
        System.out.println("");
    }

    public static boolean isBye(String word) {
        return Duke.BYE.equals(word);
    }

    public static boolean isMark(String word) {
        return word.startsWith(Duke.MARK);
    }

    public static boolean isUnmark(String word) {
        return word.startsWith(Duke.UNMARK);
    }

    public static boolean isList(String word) {
        return Duke.LIST.equals(word);
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
