package duke;

import duke.exception.InvalidTaskNumberException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;


public class TaskList {
    private final List<Task> tasks = new ArrayList<>();
    private int index = 0;

    public String addTask(Task newTask) {
        tasks.add(newTask);
        index++;
        String output = "Got it. I've added this task:\n  " +
                newTask + "\nnow you have " + this.index + " tasks in the list";
        return output;
    }

    public int numTasks() {
        return this.index;
    }

    public String getTasks() {
        String output = "Here are the tasks in your list:";
        for (int i = 0; i < index; i++) {
            output += String.format("\n%d.%s", i + 1, tasks.get(i));
        }
        return output;
    }

    List<Task> listTasks() {
        return tasks;
    }

    public String mark(int id, String instr) throws InvalidTaskNumberException {
        if (id > index || id <= 0) {
            throw new InvalidTaskNumberException();
        }
        String out = tasks.get(id - 1).switchMark(instr);
        return out;
    }

    public String remove(int id) throws InvalidTaskNumberException {
        if (id > index || id <= 0) {
            throw new InvalidTaskNumberException();
        }
        Task removed = tasks.remove(id - 1);
        index--;
        String out = "Noted, I have removed this task:\n  "
                + removed + "\nnow you have " + index + " tasks in the list.";
        return out;
    }
}
