package duke.task;

public class Todo extends Task {

    public Todo(String name) {
        this(name, false);
    }

    public Todo(String name, Boolean done) {
        super(name, 'T', done);
    }
}
