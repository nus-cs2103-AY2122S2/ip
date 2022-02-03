package chatcat.util;

import chatcat.tasklist.TaskList;

import chatcat.chatcatexception.ChatCatException;

public class Parser {

    private final TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    public void parseTaskList(String input) {
        String[] splitInput = input.split(" ");

        try {
            switch(splitInput[0]) {
                case "list":
                    taskList.listTasks();
                    break;
                case "bye":
                    taskList.exitChatCat();
                    break;
                case "mark":
                    taskList.mark(input);
                    break;
                case "unmark":
                    taskList.unmark(input);
                    break;
                case "todo":
                    taskList.setTodo(input);
                    break;
                case "deadline":
                    taskList.setDeadline(input);
                    break;
                case "event":
                    taskList.setEvent(input);
                    break;
                case "delete":
                    taskList.delete(input);
                    break;
                case "find":
                    taskList.filter(input);
                    break;
                default:
                    throw new ChatCatException("OOPS!!! I'm sorry, but I don't know what that means");
            }
        } catch (ChatCatException wrf) {
            wrf.printStackTrace();
        }
    }

    public boolean isBye(String command) {
        return command.equals("bye");
    }
}
