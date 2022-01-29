import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static final String BYE = "bye";
    static final String LIST = "list";
    static final String MARK = "mark";
    static final String UNMARK = "unmark";
    static final ArrayList<WordListItem> textHistory = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        replyWelcomeMessage();
        String input = "";
        while (true) {
            input = sc.nextLine();
            if (input.isEmpty()) {
                warnEmpty();
                continue;
            }
            if (isList(input)) {
                printHistory();
                continue;
            }

            if (isMark(input)) {
                int itemNumber = Integer.parseInt(input.substring(5));
                markItem(itemNumber);
                continue;
            }

            if (isUnmark(input)) {
                int itemNumber = Integer.parseInt(input.substring(7));
                unmarkItem(itemNumber);
                continue;
            }

            if (isBye(input)) {
                break;
            }

            echo(input);
            storeWord(input);
        }
        replyBye();
    }

    public static void storeWord(String word) {
        Duke.textHistory.add(new WordListItem(word));
    }

    public static void printHistory() {
        int i = 1;
        System.out.println("------------------------------------");
        for(WordListItem wordListItem: Duke.textHistory) {
            System.out.println(i + ". " + wordListItem);
            i++;
        }
        System.out.println("------------------------------------");
        System.out.println("");
    }

    public static void markItem(int itemNumber) {
        Duke.textHistory.get(itemNumber - 1).markItem();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + Duke.textHistory.get(itemNumber - 1));
    }

    public static void unmarkItem(int itemNumber) {
        Duke.textHistory.get(itemNumber - 1).unmarkItem();
        System.out.println("Nice! I've marked this task as not done: ");
        System.out.println("  " + Duke.textHistory.get(itemNumber - 1));
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
