public interface Command {

    public boolean execute(TaskList taskList, Ui ui, Storage storage);

}
