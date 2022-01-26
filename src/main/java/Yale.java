import yale.task.TaskList;

import java.util.Scanner;

public class Yale {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.welcomePrompt();
        Storage storage = new Storage("data/yale.txt");
        Parser parser = new Parser();
        Scanner scanner = new Scanner(System.in);
        TaskList list = new TaskList();
        String fileData = storage.loadFileContents();
        list.importIn(fileData);

        while (true) {
            String command = ui.receiveInput(scanner);
            parser.performAction(command, list);
            storage.writeTextTo(list);
            if (ui.checkExit(command)) {
                break;
            }
        }
    }




}

