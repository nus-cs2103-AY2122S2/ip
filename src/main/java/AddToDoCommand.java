import TaskList.TaskList;

public class AddToDoCommand {

    public AddToDoCommand(TaskList taskList, String userInput) {
        boolean taskAddedSuccess = taskList.addToDoTask(userInput);
        if (taskAddedSuccess) {
            Ui.printAddSuccess(taskList);
        }
    }
}
