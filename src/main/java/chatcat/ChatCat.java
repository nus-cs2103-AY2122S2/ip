package chatcat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import chatcat.tasklist.TaskList;
import chatcat.util.Parser;
import chatcat.util.Ui;

public class ChatCat {
    private static TaskList taskList = new TaskList();
    private static Parser parser = new Parser(taskList);
    private static Ui ui = new Ui();

    public String getResponse(String str) {
        return this.parser.parseTask(str);
    }

    public String showWelcomeSMS() {
        String welcome = "Hello! I'm ChatCat\n"
                + "What can I do for you?\n";
        System.out.println(welcome);
        return welcome;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ui.showWelcomeSMS();

        String input = " ";

        while (!parser.isBye(input)) {
            input = br.readLine();
            parser.parseTask(input);
        }
    }
}