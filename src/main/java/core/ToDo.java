package core;

import utilities.OutputFormatter;

public class ToDo extends Task {

    private ToDo(String description) {
        super(description);
    }

    public static ToDo getInstance(String description) {
        return new ToDo(description);
    }

    @Override
    public String toString() {
        OutputFormatter outputFormatter = OutputFormatter.getInstance();
        outputFormatter.appendAll("[T]", super.toString());
        return outputFormatter.getFormattedOutput();
    }
}
