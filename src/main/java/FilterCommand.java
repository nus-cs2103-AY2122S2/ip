public class FilterCommand extends Command {
    String description;

    public FilterCommand(String description) {
        super(false);
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws FunBoxExceptions {
        taskList.filterTasks(this.description, taskList);
    }
}
