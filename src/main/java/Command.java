public abstract class Command {
    public abstract boolean isExit();
    public abstract void execute(TaskList t, Ui u, Storage s) throws TsundereException;
}
