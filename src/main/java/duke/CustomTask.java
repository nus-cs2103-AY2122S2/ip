package duke;

public class CustomTask extends Task {
    public CustomTask(String type, boolean isComplete, String input) {
        super(input);
        this.type = type;
        this.description = this.input; // Updates description of class
        this.isComplete = isComplete;
    }
}
