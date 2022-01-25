import java.util.List;

public class DeleteCommand extends Command{
    private static final String MESSAGE_TASKDELETE = "Noted. I've removed this task:";

    private static final String ERROR_EMPTY_DELETE = "OOPS!!! Task to delete cannot be empty:(";
    private static final String ERROR_INVALID_DELETE = "OOPS!!! Invalid task number, please select a valid task to delete using the task's number";
    private int taskNumber;

    public DeleteCommand(String taskNumber) throws DukeException{
        if (taskNumber.equals("")){
            throw new DukeException(ERROR_EMPTY_DELETE);
        }
        try{
            this.taskNumber = Integer.parseInt(taskNumber);
        } catch (NumberFormatException e){
            throw new DukeException(ERROR_INVALID_DELETE);
        }
    }

    @Override
    public void execute(List<Task> tasks, UI ui) throws DukeException {
        if (this.taskNumber > tasks.size() || this.taskNumber <= 0){
            throw new DukeException(ERROR_INVALID_DELETE);
        }
        int index = taskNumber-1;
        Task thisTask = tasks.get(index);
        tasks.remove(index);
        Storage.saveToFile(tasks);
        ui.printAddDeleteTaskSuccess(tasks, thisTask, MESSAGE_TASKDELETE);
    }
}
