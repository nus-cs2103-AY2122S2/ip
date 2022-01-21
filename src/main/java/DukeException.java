public class DukeException extends Exception {
    protected TaskList tasks;
    public DukeException(String message, TaskList tasks) {
        super(message);
        this.tasks = tasks;
    }
}
