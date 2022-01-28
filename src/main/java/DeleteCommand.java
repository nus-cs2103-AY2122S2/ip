public class DeleteCommand extends Command{


    int index;

    DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    void runCommand(TaskList taskList, Ui ui, Storage storage) {
        String indentation = "    ";
        Task t = taskList.getTask(index);
        taskList.deleteTask(index);
        String message = indentation + "Noted. I've removed this task: \n" + indentation + "  " + t.toString() + t.getStatus() +  " " + t.getDescription();
        ui.outputMessage(message);
    }
}
