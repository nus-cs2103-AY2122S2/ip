package main.java.parser;

import main.java.Command;
import main.java.data.Storage;
import main.java.data.TaskList;
import main.java.dukeexceptions.DukeException;
import main.java.dukeexceptions.ToDoException;
import main.java.responses.*;
import main.java.task.Deadline;
import main.java.task.Event;
import main.java.task.Task;
import main.java.task.ToDo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    static private ArrayList<String> commandList = new ArrayList<>(
            Arrays.asList("bye", "delete", "list", "mark", "unmark", "todo", "deadline", "event"));
    
    public Response start() {
        return new StartResponse();
    }
    
    public Response getResponse(Command cmd , String Stringcmd, Storage store, TaskList runningTaskList) {

        String[] stringCmdArr = Stringcmd.split("");
        System.out.println("hi");
        System.out.println(cmd);
        try {
            int index;
            String[] ans;
            Task tempTask;
            switch (cmd) {

                case delete:
                    index = Integer.parseInt(stringCmdArr[1]);
                    return new DeleteResponse(runningTaskList.removeTask(index), runningTaskList.taskLength());

                case list:
                    return new ListResponse(runningTaskList.getTaskList());

                case mark:
                    index = Integer.parseInt(stringCmdArr[1]);
                    runningTaskList.markTask(index);
                    return new MarkResponse(runningTaskList.getTask(index));

                case unmark:
                    index = Integer.parseInt(stringCmdArr[1]);
                    runningTaskList.unMarkTask(index);
                    return new UnMarkResponse(runningTaskList.getTask(index));

                case todo:
                    ans = Stringcmd.split("todo ");
                    if (ans.length == 1) {
                        throw new ToDoException("");
                    }
                    tempTask = new ToDo(ans[1]);
                    runningTaskList.addTask(tempTask);
                    store.loadToDisk(runningTaskList);
                    return new AddTaskResponse(tempTask, runningTaskList.getTaskList());

                case deadline:
                    ans = Stringcmd.split(" /by ");
                    tempTask = new Deadline(ans[0].replace("deadline ", ""), ans[1]);
                    runningTaskList.addTask(tempTask);
                    store.loadToDisk(runningTaskList);
                    return new AddTaskResponse(tempTask, runningTaskList.getTaskList());

                case event:
                    
                    ans = Stringcmd.split(" /at ");
                    tempTask = new Event(ans[0].replace("deadline ", ""), ans[1]);
                    runningTaskList.addTask(tempTask);
                    store.loadToDisk(runningTaskList);
                    return new AddTaskResponse(tempTask, runningTaskList.getTaskList());

                case bye:
                    return  new StopResponse();

            }
        } catch (DukeException | IOException e) {
            e.getMessage();
        }
        return null;
    }
}
