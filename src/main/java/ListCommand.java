public class ListCommand extends Command {
    ListCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.size() == 0) {
            System.out.println("You currently do not have any tasks *quack*, please add some more");
        } else {
            ui.print("These are your tasks *quack*:");
            for (int i = 1; i <= tasks.size(); i++) {
                Task task = tasks.getByNumber(i);
                ui.print(String.format("%d. %s", i, task.toString()));
            }
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}
