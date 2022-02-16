package chatcat.util;

import chatcat.chatcatexception.InvalidDateException;
import chatcat.chatcatexception.TaskEditException;
import chatcat.chatcatexception.TaskException;
import chatcat.chatcatexception.ChatCatException;
import chatcat.tasklist.TaskList;

public class Parser {

    private final TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    public String parseTask(String input) {
        String[] splitInput = input.split(" ");
        String output = "";

        try {
            switch(splitInput[0]) {
            case "list":
                output = taskList.listTasks();
                break;
            case "bye":
                output = taskList.exitChatCat();
                break;
            case "mark":
                output = taskList.mark(input);
                break;
            case "unmark":
                output = taskList.unmark(input);
                break;
            case "todo":
                output = taskList.setTodo(input);
                break;
            case "deadline":
                output = taskList.setDeadline(input);
                break;
            case "event":
                output = taskList.setEvent(input);
                break;
            case "delete":
                output = taskList.delete(input);
                break;
            case "find":
                output = taskList.filter(input);
                break;
            default:
                throw new ChatCatException();
            }
        } catch (ChatCatException wrf) {
            output = wrf.toString();
        } catch (TaskException t) {
            output = t.toString();
        } catch (TaskEditException te) {
            output = te.toString();
        } catch (InvalidDateException id) {
            output = id.toString();
        }

        return output;
    }
}
