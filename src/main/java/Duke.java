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
    public static void main(String[] args) throws DukeException, IOException {
        String welcomeMsg = "____________________________________________________________\n" +
                "Hello! I'm Duke\nWhat can I do for you?\n" +
                "____________________________________________________________";
        String startingFilePath = "C:\\NUS\\CS2103\\iP\\data\\duke.txt";
        boolean echo = true;
        Scanner sc = new Scanner(System.in);
        DukeList dL = new DukeList();

        System.out.println(welcomeMsg);

        FileAction fA = null;
        boolean isFileWrong = true;
        while (isFileWrong) {
            try {
                fA = new FileAction(startingFilePath);
                dL.setTasks(fA.readTasksFromFile());
                fA.closeReadFile();
                isFileWrong = false;
            } catch (IOException e) {
                System.out.println("\nPlease insert a correct file path to your file: \n");
                String newFileName = sc.nextLine();
                fA.requestChangeFile(newFileName);
            }
        }

        while (echo) {
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
