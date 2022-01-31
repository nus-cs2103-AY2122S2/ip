package kenobi.util;

import kenobi.task.Task;
import java.util.ArrayList;


public class TaskList extends ArrayList<Task> {
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < super.size(); i++) {
            str.append(String.format("%d. %s\n", i + 1, super.get(i)));
        }

        return str.toString();
    }
}

