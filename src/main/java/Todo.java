public class Todo extends Task {
    private char tag = 'T';

    public Todo(String name) {
        this(name, false);
    }

    public Todo(String name, Boolean done) {
        super(name, done);
    }
}
