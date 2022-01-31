package duke.uicomponents;

import duke.tasklist.TaskList;

public class DeleteTaskDisplay {
    public void run(String task, TaskList taskList) {
        System.out.println("============================");
        System.out.println("Deleted this tasks: ");
        System.out.println(task);
        taskList.printNoTasks();
        System.out.println("============================");
    }
}
