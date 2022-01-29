import java.util.Scanner;

public class Duke {
    static final String BYE = "bye";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String input = sc.nextLine();
        while (!isBye(input)) {
            echo(input);
            input = sc.nextLine();
        }
        replyBye();
    }

    public static void echo(String words) {
        System.out.println("------------------------------------");
        System.out.println(words);
        System.out.println("------------------------------------");
        System.out.println("");
    }

    public static boolean isBye(String word) {
        return Duke.BYE.equals(word);
    }

    public static void replyBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
