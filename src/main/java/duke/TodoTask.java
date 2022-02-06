package duke;

public class TodoTask extends Task {
    public TodoTask(String input) {
        super(input);
        this.type = "todo";
        this.description = this.input; // Updates description of class
    }
}
