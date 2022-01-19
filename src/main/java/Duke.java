import java.util.ArrayList;
import java.util.Scanner;
/**
 * This Duke program is a Personal Assistant Chatbot
 * to keep track of various things.
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-1-15
 */

public class Duke {
    public static void main(String[] args) throws DukeException {
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
            try {
                a.makeAction();
            } catch (DukeException e) {
                System.out.println(e.message);
            }
        }
    }

}
