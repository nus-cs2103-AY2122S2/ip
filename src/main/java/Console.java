import java.util.ArrayList;
import java.util.Scanner;

public class Console {

    private static Scanner sc = new Scanner(System.in);

    public static void print(String msg) {

        String[] msgs = msg.split("\n");

        for (String line : msgs) {
            System.out.println("      " + line);
        }
    }

    public static void printList(String msg, String emptymsg, ArrayList<Task> arr) {

        if (arr.isEmpty()) {
            System.out.println("      " + emptymsg);

        } else {
            System.out.println("      " + msg);
            for (int i = 0; i < arr.size(); i++) {
                System.out.println(String.format("      %d. %s", i + 1, arr.get(i).toString()));
            }
        }
        System.out.println("    ____________________________________________________________");
    }
    public static void println(String msg) {

        print(msg);
        System.out.println("    ____________________________________________________________");
    }

    public static String read() {
        return sc.nextLine();
    }
}
