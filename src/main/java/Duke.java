import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static final String BYE = "bye";
    static final String LIST = "list";
    static final ArrayList<String> textHistory = new ArrayList<>();

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
            if (isList(input)) {
                printHistory();
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
        Duke.textHistory.add(word);
    }

    public static void printHistory() {
        int i = 1;
        System.out.println("------------------------------------");
        for(String word: Duke.textHistory) {
            System.out.println(i + ". " + word);
        }
        System.out.println("------------------------------------");
        System.out.println("");
    }

//    public static void displayResult()

    public static void echo(String words) {
        System.out.println("------------------------------------");
        System.out.println("added: " + words);
        System.out.println("------------------------------------");
        System.out.println("");
    }

    public static boolean isBye(String word) {
        return Duke.BYE.equals(word);
    }

    public static boolean isList(String word) {
        return Duke.LIST.equals(word);
    }

    public static void replyWelcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
    public static void replyBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
