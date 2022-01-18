import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    // Attributes
    public static String LINE = "____________________________________________________________";
    public static ArrayList<Task> LIST = new ArrayList<>();

    public static void greet() {
        System.out.println(LINE + "\n Hello! I'm Duke\n What can I do for you?\n" + LINE);
    }

    public static void add(String s) {
        LIST.add(new Task(s));
        System.out.println(LINE + "\n added: " + s + "\n" + LINE);
    }

    public static void list() {
        System.out.println(LINE + "\n Here are the tasks in your list:");
        for (int i = 1; i <= LIST.size(); i++) {
            System.out.printf(" %d.[%s] %s%n", i, LIST.get(i - 1).getStatusIcon(), LIST.get(i - 1).getDescription());
        }
        System.out.println(LINE);
    }

    public static void mark(int i) {
        System.out.println(LINE + "\n Nice! I've marked this task as done: ");
        LIST.get(i - 1).setDone();
        System.out.printf("   [%s] %s%n", LIST.get(i - 1).getStatusIcon(), LIST.get(i - 1).getDescription());
        System.out.println(LINE);
    }

    public static void unmark(int i) {
        System.out.println(LINE + "\n OK, I've marked this task as not done yet:");
        LIST.get(i - 1).setNotDone();
        System.out.printf("   [%s] %s%n", LIST.get(i - 1).getStatusIcon(), LIST.get(i - 1).getDescription());
        System.out.println(LINE);
    }

    public static void exit() {
        System.out.println(LINE + "\n Bye. Hope to see you again soon!\n" + LINE);
    }

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        whileLoop:
        while (sc.hasNext()) {
            String usrInput = sc.next();
            switch (usrInput) {
                case "bye":
                    exit();
                    break whileLoop;
                case "list":
                    list();
                    break;
                case "mark": {
                    int taskNum = Integer.parseInt(sc.next());
                    mark(taskNum);
                    break;
                }
                case "unmark": {
                    int taskNum = Integer.parseInt(sc.next());
                    unmark(taskNum);
                    break;
                }
                default:
                    usrInput += sc.nextLine();
                    add(usrInput);
                    break;
            }
        }
    }
}
