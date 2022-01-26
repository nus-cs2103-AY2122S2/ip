import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;


public class Duke {
    public static void main(String[] args) throws IOException {
        run();
    }

    public static void run() throws IOException {
        Ui.startGreeting();
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> storeList = new ArrayList<>();
        Storage.restoreList(storeList);
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            CommandParser.parseCommand(command, storeList);
        }
        sc.close();
    }

}
