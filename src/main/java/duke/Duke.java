package duke;

import duke.command.Parser;
import java.util.Scanner;
import duke.io.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    public static void main(String[] args) {
        Ui ui = new Ui();
        Scanner in = new Scanner(System.in);
        Parser parser = new Parser();
        TaskList taskList = new TaskList();

        Storage.loadFile(System.getProperty("user.dir") + "\\data\\duke.txt", taskList);

        ui.printLogo();
        String userInput = in.nextLine();
        int runResult = parser.run(userInput, ui, taskList);
        while(runResult != -1) {
            if(runResult == 1) {
                Storage.saveFile("data", "duke.txt", taskList.getList());
            }
            userInput = in.nextLine();
            runResult = parser.run(userInput, ui, taskList);
        }
    }

}



