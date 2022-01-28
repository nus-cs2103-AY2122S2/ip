import TaskList.TaskList;

public class AddDeadlineCommand extends Command {

    public AddDeadlineCommand(TaskList taskList, String userInput) {
        boolean taskAddedSuccess = taskList.addDeadlineTask(userInput);
        if (taskAddedSuccess) {
            Ui.printAddSuccess(taskList);
        }
    }
}
