package bob;

import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        Ui.greet();

        // Initialization
        String folderPath = "./data";
        String filePath = "./data/bob.txt";
        Storage storage = Storage.createStorage(folderPath, filePath);
        TaskList taskList = storage.load();

        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine().strip();

        while (!cmd.split(" ")[0].equals("bye")) {
            Parser.parse(cmd, taskList, storage);
            cmd = sc.nextLine().strip();
        }
        Ui.bye();
    }
}

