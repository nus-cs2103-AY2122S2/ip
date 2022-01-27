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
                CommandParser.parseCommand(command, storeList);
            }
        }
        sc.close();
    }

    public static void unknownCommand() {
        System.out.println("Master, I have all the knowledge in the world but I do not recognise that command," +
                " Please wish again");
    }

    public static void startGoodbye() {
        System.out.println("Rub my lamp to summon me again");
        System.out.println("Good bye for now master");
        System.out.println("*-**-**-**-**-**-**-**-**-**-****-**-****-**-****-**");
    }

    public static void startGreeting() {
        System.out.println("A very good day to you master, I'm Blue the Genie");
        System.out.println("What do you wish for today? Your wish is my command");
        System.out.println("****-**-****-**-****-**-****-**-****-**-****-**-****-**-****-**");
    }
}
