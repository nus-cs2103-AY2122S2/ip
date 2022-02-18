package duke;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Duke {


    public static void main() {
        // Init UI
        Ui ui = new Ui();

        // Init file if it is not there
        File f = new File("./tasklist.txt");
        if (!f.isFile()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        assert(f.isFile()) : "File should exist";

        // Init saved tasks
        try {
            Storage.loadTasks();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reading and processing inputs
        Parser Parser = new Parser();
        String nextLine = ui.getNextLine();
        Command nextCommand = Parser.makeCommand(nextLine);
        nextCommand.execute();


        try {
            Storage.saveTaskList();
            TaskList.taskList = new ArrayList<Task>(); // Reset task list
            assert (TaskList.taskList.size() == 0) : "New tasklist should be empty";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Formats a single line with a new line
     *
     * @param input Input string
     * @return String with newline
     */
    public static String newLine(String input) {
        String output = input + "\n";
        return output;
    }

}
