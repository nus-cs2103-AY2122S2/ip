package duke;

import tasklist.TaskList;
import util.Parser;
import util.UI;
import java.io.*;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList);
        UI ui = new UI();

        ui.showWelcomeSMS();

        String input = " ";
        while (!parser.isBye(input)) {
            input = br.readLine();
            parser.parseTaskList(input);
        }
    }
}
