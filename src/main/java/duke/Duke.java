package duke;

import javafx.application.Platform;

import java.io.IOException;

/**
 * This class runs the Duke program - a task manager for users.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class Duke {

    private TaskList list = new TaskList();
    public Duke() throws IOException, DukeException {
        list = Storage.loadFile();
    }

    public String getResponse(String input) throws IOException {
        String str = "";
        Parser parse = new Parser(input);
        if (parse.getCmd().equals("?")) {
            str = Ui.getHelp();
        } else if (parse.getCmd().equals("mark")) {
            int index = Integer.parseInt(parse.getDesc()) - 1;
            str = list.markTask(index);
        } else if (parse.getCmd().equals("unmark")) {
            int index = Integer.parseInt(parse.getDesc()) - 1;
            str = list.unMarkTask(index);
        } else if (parse.getCmd().equals("list")) {
            str = list.listTask();
        } else if (parse.getCmd().equals("todo")) {
            str = list.addToDo(parse.getDesc(), false);
        } else if (parse.getCmd().equals("deadline")) {
            str = list.addDeadline(parse.getDesc(), parse.getDate(), false);
        } else if (parse.getCmd().equals("event")) {
            str = list.addEvent(parse.getDesc(), parse.getDate(), false);
        } else if (parse.getCmd().equals("delete")) {
            int index = Integer.parseInt(parse.getDesc()) - 1;
            str = list.deleteTask(index);
        } else if (parse.getCmd().equals("find")) {
            str = list.findTask(parse.getDesc());
        } else if (parse.getCmd().equals("bye")) {
            str = "Bye. See you again!";
            Storage.writeFile(list);
            Platform.exit();
        } else {
            str = "Opps!! The command doesn't exist. Please try again.";
        }
        return str;
    }

}
