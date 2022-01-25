import java.io.FileNotFoundException;
import java.io.IOException;
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
    public static void main(String[] args) throws DukeException, FileNotFoundException {
        String welcomeMsg = "____________________________________________________________\n" +
                "Hello! I'm Duke\nWhat can I do for you?\n" +
                "____________________________________________________________";
        boolean echo = true;
        Scanner sc = new Scanner(System.in);
        DukeList dL = new DukeList();
        FileAction fA = null;

        System.out.println(welcomeMsg);
        while (echo) {
            boolean isFileWrong = false;

            try {
                fA = new FileAction();
            } catch (IOException e) {
                isFileWrong = true;
                System.out.println("\nPlease insert a correct file path to your file!\n");
                String fileName = sc.nextLine();
                fA.changeFilePath(fileName);
                continue;
            }
            String input = sc.nextLine();
            String[] splitter = input.split(" ");
            Action a = new Action(splitter, dL, fA);
            try {
                a.makeAction();
            } catch (DukeException e) {
                System.out.println(e.message);
            }
        }
    }

}
