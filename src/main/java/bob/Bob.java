package bob;

import javafx.fxml.FXML;

/**
 * Driver class containing the main method for entry to program.
 */
public class Bob {
    private Storage storage;
    private TaskList taskList;

    Bob() {
        String folderPath = "./data";
        String filePath = "./data/bob.txt";
        storage = Storage.createStorage(folderPath, filePath);
        taskList = storage.load();
    }
//    /**
//     * Main method as entry point to program.
//     *
//     * @param args Command line arguments.
//     */
//    public static void main(String[] args) {
//        Ui.greet();
//
//        // Initialization
//        String folderPath = "./data";
//        String filePath = "./data/bob.txt";
//        Storage storage = Storage.createStorage(folderPath, filePath);
//        TaskList taskList = storage.load();
//
//        Scanner sc = new Scanner(System.in);
//        String cmd = sc.nextLine().strip();
//
//        while (!cmd.split(" ")[0].equals("bye")) {
//            String response = Parser.parse(cmd, taskList, storage);
//            cmd = sc.nextLine().strip();
//        }
//        Ui.bye();
//    }

    @FXML
    public String getResponse(String input) {
        return Parser.parse(input, taskList, storage);
    }
}

