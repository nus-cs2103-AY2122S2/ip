package duke.uicomponents;

import duke.tasklist.TaskList;

public class TaskListDisplay{
    public void run(TaskList taskList) {

        System.out.println("==============================");
        System.out.println("Here You go: ");
        taskList.printTasks();
        System.out.println("==============================");

    }
}
