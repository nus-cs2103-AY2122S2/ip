import java.util.Scanner;

public class Duke {
    public static String LINE = "____________________________________________________________";

    public static void greet() {
        System.out.println(LINE + "\n Hello! I'm Duke\n What can I do for you?\n" + LINE);
    }

    public static void echo(String s) {
        System.out.println(LINE + "\n " + s + "\n" + LINE);
    }

    public static void exit() {
        System.out.println(LINE + "\n Bye. Hope to see you again soon!\n" + LINE);
    }

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String usrInput = sc.next();
            if (usrInput.equals("bye")) {
                exit();
                break;
            } else {
                echo(usrInput);
            }
        }
    }
}
