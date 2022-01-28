package duke.uicomponents;

import duke.task.Task;
import duke.tasklist.TaskList;

public class NewTaskDisplay{
    public void run(Task task, TaskList taskList) {
        System.out.println("====================================");
        System.out.println("Alright, I've added this to the list");
        System.out.println(task.toString());
        taskList.printNoTasks();
        System.out.println("====================================");
    }
}
