package duke.commands;

import java.util.ArrayList;

import duke.tasks.Task;
import duke.tasks.TaskList;

public class Tag extends Commands {

    public Tag(ArrayList<Task> tasks) {
        super(tasks);
    }

    public String handleTag(TaskList tasks, int pos, String tagName) {
        String res = "";

        Task currTask = tasks.getTasks().get(pos);
        currTask.setTag(tagName);

        res += super.LINE_BREAK + "\n";
        res += super.TAG_OUTRO + (pos + 1) + "\n";
        res += super.LINE_BREAK + "\n";
        return res;
    }
}
