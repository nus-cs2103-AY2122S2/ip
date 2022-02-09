package duke;

import java.io.IOException;

/**
 * This class runs the Duke program - a task manager for users.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class Duke {

    private TaskList list;

    public Duke() throws IOException, DukeException {
        list = Storage.readFromFile();
    }

    public String getResponse(String input) throws IOException, DukeException {
        String str = "";
        Parser parse = new InputParser(input);
        switch(parse.getCmd()) {
        case "mark":
            return Ui.markTask(list,
                    Integer.parseInt(parse.getDesc()) - 1);
        case "unmark":
            return Ui.unmarkTask(list,
                    Integer.parseInt(parse.getDesc()) - 1);
        case "list":
            return Ui.listTask(list);
        case "todo":
            return Ui.addToDo(list, parse.getDesc(), false);
        case "deadline":
            return Ui.addDeadline(list, parse.getDesc(),
                    parse.getDate(), false);
        case "event":
            return Ui.addEvent(list, parse.getDesc(),
                    parse.getDate(), false);
        case "delete":
            return Ui.deleteTask(list,
                    Integer.parseInt(parse.getDesc()) - 1);
        case "find":
            return Ui.findTask(list, parse.getDesc());
        case "bye":
            return Ui.endProgram(list);
        default:
            return Ui.getErrorMsg();
        }
    }

}
