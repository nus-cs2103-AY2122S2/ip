package duke2;// package duke1;

public class Todo extends Task {
    Todo(String name) {
        super(name);
    }

    public void createTodoFromCommand(String[] input) {
        for (int i = 1; i < input.length; i++) {
            this.name += input[i] + " ";
        }
    }

    /**
     * toString() method for todo
     * 
     * @return string
     */
    @Override
    public String toString() {
        if (done) {
            return "[T][X] " + this.name;
        }
        return "[T][] " + this.name;
    }
}
