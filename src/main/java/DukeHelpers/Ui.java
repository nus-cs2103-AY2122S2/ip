package DukeHelpers;
import java.util.Scanner;
public class Ui {

    private static Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void welcome() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    public static String readCommand() {
        return sc.nextLine();
    }

    public static void closeScanner() {
        sc.close();
    }

    public static void showError(String str) {
        System.out.println(str);
    }

    public static void showLoadingError() {
        System.out.println("Loading error!");
    }


}
