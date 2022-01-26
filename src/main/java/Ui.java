import java.io.IOException;
import java.sql.Statement;
import java.util.Scanner;

public class Ui {
    static String lineDivider = "____________________________________________________________\n";
    static String greet = lineDivider
            + "Hello! I'm Mum!\nWhat can I do for you?\nType \"commands\" to get a list of all commands.\n"
            + lineDivider;
    static String goodBye = lineDivider + "Bye. Hope to see you again soon!\n" + lineDivider;
    static String addedTask = lineDivider + "Got it. I've added this task:\n" +
            "   %s\nNow you have %d tasks in the list.\n" + lineDivider;
    static String deleteTask = lineDivider + "Noted. I've removed this task:\n%s\nNow you have %d tasks in the list\n" +
            lineDivider;
    static String markedTask = lineDivider + "Nice! I've marked this task as done:\n[%s] %s\n" + lineDivider;
    static String unmarkedTask = lineDivider+ "Ok, I've marked this task as not done yet:\n[%s] %s\n" + lineDivider;

    public Ui() {

    }

    public String readCommand() {
        Scanner cmd = new Scanner(System.in);
        return cmd.nextLine();
    }

    public static void showLoadingError() {
        System.out.println("Not a valid file. It cannot be loaded.");
    }

    public static void showWelcome() {
        System.out.print(greet);
    }

    public static void showGoodBye() {
        System.out.print(goodBye);
    }

    public static void showAddResponse(String output, int n) {
        System.out.printf(addedTask, output, n);
    }

    public static void showDeleteResponse(String output, int n) {
        System.out.printf(deleteTask, output, n);
    }

    public static void showMarkRes(String status, String output) {
        System.out.printf(markedTask, status, output);
    }

    public static void showUnmarkRes(String status, String output) {
        System.out.printf(unmarkedTask, status, output);
    }
}
