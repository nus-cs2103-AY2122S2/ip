public abstract class Command {

    /**
     * Executes command by printing exit message.
     *  @param taskList  TaskList of tasks
     * @param ui        Ui provided
     * @param storage   Saved history
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

}

