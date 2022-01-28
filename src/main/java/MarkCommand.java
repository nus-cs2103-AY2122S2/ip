public class MarkCommand extends Command {

    private boolean mark;
    private int id;

    public MarkCommand(String id, boolean mark) {
        try {
            this.id = Integer.parseInt(id);
            this.mark = mark;
        } catch (NumberFormatException e) {
            System.out.println("That is no item in this list with that id");
        }

    }

    @Override
    public void execute(TaskMaster tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task taskToMark = tasks.get_tasks().get(id - 1);
            if (this.mark) {
                taskToMark.mark();
                ui.notifyMarkedTaskMessage(taskToMark, true);
            } else {
                taskToMark.unmark();
                ui.notifyMarkedTaskMessage(taskToMark, false);
            }
            storage.saveToFile(tasks.get_tasks());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No such task exists! Are you sure about that task number?");
        }

    }
}
