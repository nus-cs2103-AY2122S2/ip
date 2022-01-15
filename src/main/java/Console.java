import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Console {

    private static Scanner sc = new Scanner(System.in);

    public static void printLogo() {
        String logo = "       ___   _________  ___  \n" +
                      "      |_  | |_  | ___ \\/ _ \\ \n" +
                      "        | |   | | |_/ / /_\\ \\\n" +
                      "        | |   | | ___ \\  _  |\n" +
                      "    /\\__/ /\\__/ / |_/ / | | |\n" +
                      "    \\____/\\____/\\____/\\_| |_/";
        System.out.println(logo);
    }

    public static void printList(ArrayList<String> arr) {

        for (int i = 0; i < arr.size(); i++) {
            System.out.println(String.format("    %d. %s", i + 1, arr.get(i)));
        }
        System.out.println("    ____________________________________________________________");
    }
    public static void println(String msg) {

        String[] msgs = msg.split("\n");

        for (String line : msgs) {
            System.out.println("      " + line);
        }
        System.out.println("    ____________________________________________________________");
    }

    public static String read() {
        String arg = sc.nextLine();

        return arg;
    }
}
