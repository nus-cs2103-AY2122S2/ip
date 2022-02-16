package duke;

import java.io.IOException;

/**
 * This class runs the Duke program - a task manager for users.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class Duke {

    private TaskList taskList;
    private TagList tagList;

    public Duke() throws IOException, DukeException {
        tagList = TagStorage.readFromFile();
        taskList = TaskStorage.readFromFile(tagList);
    }

    public String getResponse(String input) throws IOException, DukeException {
        String str = "";
        Parser parse = new InputParser(input);
        switch(parse.getCmd()) {
        case "mark":
            return Ui.markTask(taskList, tagList,
                    Integer.parseInt(parse.getDesc()) - 1);
        case "unmark":
            return Ui.unmarkTask(taskList, tagList,
                    Integer.parseInt(parse.getDesc()) - 1);
        case "list-task":
            return Ui.listTask(taskList);
        case "list-tag":
            return Ui.listTag(tagList);
        case "todo":
            return Ui.addToDo(taskList, tagList, parse.getDesc(), false);
        case "deadline":
            return Ui.addDeadline(taskList, tagList, parse.getDesc(),
                    parse.getDate(), false);
        case "event":
            return Ui.addEvent(taskList, tagList, parse.getDesc(),
                    parse.getDate(), false);
        case "delete":
            return Ui.deleteTask(taskList, tagList,
                    Integer.parseInt(parse.getDesc()) - 1);
        case "find":
            return Ui.findTask(taskList, parse.getDesc());
        case "add-tag":
            return Ui.addTag(tagList, parse.getDesc());
        case "tag":
            String[] indexArray = parse.getDesc().split(" ");
            return Ui.tagTask(taskList, tagList, Integer.parseInt(indexArray[0]) - 1,
                    Integer.parseInt(indexArray[1]) - 1);
        case "?":
            return Ui.getHelp();
        case "bye":
            return Ui.endProgram(taskList, tagList);
        default:
            return Ui.getErrorMsg();
        }
    }

}
