package duke;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 Class to represent the Ui interactions messages the user will have with the chatbot
 */
public class Ui {

    public static void run() throws IOException {
        startGreeting();
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> storeList = new ArrayList<>();
        Storage.restoreList(storeList);
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                startGoodbye();
                Storage.saveList(storeList);
                break;
            } else {
                CommandParser.parseCommand(command);
            }
        }
        sc.close();
    }

    public static String unknownCommand() {
        return "Master, I have all the knowledge in the world but I do not recognise that command," +
                " Please wish again";
    }

    public static String startGoodbye() {
        return "Rub my lamp to summon me again, Good bye for now master";
    }

    public static String startGreeting() {
        return "A very good day to you master, I'm Blue the Genie " +
                "What do you wish for today? Your wish is my command";
    }
}
