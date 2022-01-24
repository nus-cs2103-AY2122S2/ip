public class ListCommand extends Command{
    @Override
    public boolean execute(TaskList tasks, TaskDataHandler storage, StevieUi ui) {
        ui.outputMessage(tasks.toString());
        return false;
    }
}
