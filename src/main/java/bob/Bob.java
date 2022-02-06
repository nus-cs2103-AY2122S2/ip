package bob;

import java.util.List;
import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        // Greeting
        Parser.printLine();
        System.out.println("Hello! I'm Bob!\n" + "Loading saved entries...");

        // Initialization
        String folderPath = "./data";
        String filePath = "./data/bob.txt";
        Storage storage = Storage.createStorage(folderPath, filePath);
        List<Task> taskList = storage.load();

        System.out.println("What can I do for you?");
        Parser.printLine();
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine().strip();

        while (!cmd.split(" ")[0].equals("bye")) {
            Parser.printLine();
            Parser.parse(cmd, taskList, storage);
            Parser.printLine();
            cmd = sc.nextLine().strip();
        }

        Parser.printLine();
        System.out.println("Bye. Hope to see you again soon!");
        Parser.printLine();
    }
}

