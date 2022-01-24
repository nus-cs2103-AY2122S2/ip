public class ExitCommand extends Command {
    @Override
    public boolean execute(TaskList tasks, TaskDataHandler storage, StevieUi ui) {
        ui.outputMessage("Bye! Hope to see you again!");
        return true;
    }
}
