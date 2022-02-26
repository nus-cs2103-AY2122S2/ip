package Duke;

import java.util.Scanner;

/**
 * @author Pragya Jha
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui uiPrinter;
    private Parser commandParser = new Parser();
    public Duke(String filePath) {
        uiPrinter = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTaskListFromFile());
        } catch (DukeException e) {
            uiPrinter.printMessage(e.getMessage());
            taskList = new TaskList();
        }
    }

    public void run(){
        Scanner input = new Scanner(System.in);
        String userinput = "";
        uiPrinter.printMessage(Ui.welcomeMsg);
        boolean isRunning = true;
        while(isRunning) {
            userinput = input.nextLine();
            try {
                isRunning = commandParser.parseCommand(userinput, taskList, uiPrinter, storage);
            }
            catch(DukeException e){
                uiPrinter.printMessage(e.getMessage());
            }
        }

        input.close();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
