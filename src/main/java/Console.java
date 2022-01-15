import java.sql.SQLOutput;
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

    public static void println(String msg) {
        //System.out.println("    ____________________________________________________________");

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
