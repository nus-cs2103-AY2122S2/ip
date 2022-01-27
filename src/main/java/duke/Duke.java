package duke;
import duke.utils.*;


/**
 * Represents an instance of the task
 * manager
 */
public class Duke {


    private Ui ui;
    private TaskList tl;


    public Duke(){
        this.ui = new Ui();
        tl = new TaskList(Storage.getSavedList());
    }

    public void run() {

        Ui.printHello();
        boolean isExit = false;

        while (!isExit) {

            Ui.printLine();

            try {
                Parser.parse(ui.readCommand(), tl);
            } catch (DukeException e){
                Ui.showError(e);
            }

            isExit = ui.isExit();
            Ui.printLine();
        }

        tl.saveListToStorage();
        Ui.printBye();
    }


    public static void main(String[] args) {
        new Duke().run();
    }

}
