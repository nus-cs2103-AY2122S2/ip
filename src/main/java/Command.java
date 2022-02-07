import java.io.IOException;

public abstract class Command {
    private boolean exit = false;

    /**
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws IOException
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IOException;

    /**
     *
     */
    public void toggleExit() {
        this.exit = !this.exit;
    }

    /**
     *
     * @return
     */
    public boolean isExit() {
        return this.exit;
    }
}