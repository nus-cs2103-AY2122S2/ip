public class ToggleCommand extends Command {

    protected String cmd;
    protected String index;

    public ToggleCommand(String cmd, String index) {
        this.cmd = cmd;
        this.index = index;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (tasks.isEmpty()) {
                ui.printWithDivider("Your list is empty!");
                return;
            }

            int toggleIndex = Parser.parseInt(index) - 1;
            if (toggleIndex < 0 || toggleIndex >= tasks.size()) {
                ui.showError("Invalid entry number!");
            } else {
                Task toggleTask = tasks.get(toggleIndex);
                toggleTask.isMarked = cmd.equals("mark");
                ui.showToggleTask(toggleTask);
            }
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
