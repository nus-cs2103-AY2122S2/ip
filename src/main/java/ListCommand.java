import TaskList.TaskList;

public class ListCommand {

    public ListCommand(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        Ui.printTasks(taskList.getTasks());
    }
}
