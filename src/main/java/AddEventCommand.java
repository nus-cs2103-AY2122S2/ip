import TaskList.TaskList;

public class AddEventCommand extends Command {

    public AddEventCommand(TaskList taskList, String userInput) {
        boolean taskAddedSuccess = taskList.addEventTask(userInput);
        if (taskAddedSuccess) {
            Ui.printAddSuccess(taskList);
        }
    }
}
