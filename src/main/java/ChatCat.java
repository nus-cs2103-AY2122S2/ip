import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import chatcat.tasklist.TaskList;
import chatcat.util.Parser;
import chatcat.util.UI;

public class ChatCat {
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
