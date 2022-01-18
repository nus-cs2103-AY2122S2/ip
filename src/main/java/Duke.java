import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String welcomeMsg = "____________________________________________________________\n" +
                "Hello! I'm Duke\nWhat can I do for you?\n" +
                "____________________________________________________________";
        DukeList dL = new DukeList();

        boolean echo = true;
        Scanner sc = new Scanner(System.in);

        System.out.println(welcomeMsg);
        while (echo) {
            String input = sc.nextLine();
            String[] splitter = input.split(" ");
            Action a = new Action(splitter, dL);
            a.makeAction();
        }
    }

    public static String message(String inp) {
        String line = "____________________________________________________________";
        String res = line + "\n" + inp + "\n" + line;
        return res;
    }

}
