public abstract class Command {

    public abstract void execute(Ui ui, DukeList list);
    public abstract boolean isExit();

}
