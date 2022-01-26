package duke;

import java.io.IOException;

/**
 * This program is used to add, list & mark the status of your current tasks.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class Duke {

    public static void main(String[] args) throws IOException, DukeException {
        // Start Message
        Ui.startMessage();

        // Load file data into task list
        TaskList list = new TaskList();
        try {
            list = Storage.loadFile();
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        }

        // Deal with user inputs
        try {
            Ui.allowUserInput(list);
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
