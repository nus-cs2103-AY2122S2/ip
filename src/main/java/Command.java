public abstract class Command<T> {
    public void execute() throws DukeException {}
    public boolean isExit(){
        return true;
    }
}
