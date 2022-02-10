package stevie.command;

public abstract class ChangeCommand<T> extends Command {
    protected final T parameter;
    public ChangeCommand(T param) {
        parameter = param;
    }
}
