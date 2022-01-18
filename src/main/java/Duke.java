import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    // Attributes
    public static String LINE = "____________________________________________________________";
    public static ArrayList<String> LIST = new ArrayList<>();

    public static void greet() {
        System.out.println(LINE + "\n Hello! I'm Duke\n What can I do for you?\n" + LINE);
    }

    public static void add(String s) {
        LIST.add(s);
        System.out.println(LINE + "\n added: " + s + "\n" + LINE);
    }

    public static void list() {
        System.out.println(LINE);
        for (int i = 1; i <= LIST.size(); i++) {
            System.out.println(String.format(" %d. %s", i, LIST.get(i - 1)));
        }
        System.out.println(LINE);
    }

    public static void exit() {
        System.out.println(LINE + "\n Bye. Hope to see you again soon!\n" + LINE);
    }

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String usrInput = sc.nextLine();
            if (usrInput.equals("bye")) {
                exit();
                break;
            } else if (usrInput.equals("list")) {
                list();
            } else {
                add(usrInput);
            }
        }
    }
}
