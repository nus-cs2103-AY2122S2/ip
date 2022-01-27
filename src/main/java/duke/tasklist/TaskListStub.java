package duke.tasklist;

import java.util.ArrayList;
import java.util.List;

public class TaskListStub extends TaskList {

    List<String> test_tasks = new ArrayList<>();

    public TaskListStub() {
        this.test_tasks.add("list item 1");
        this.test_tasks.add("list item 2");
    }

    @Override
    public String getTasksMsg() {
        return test_tasks.get(0) + "\n" + test_tasks.get(1);
    }
}
