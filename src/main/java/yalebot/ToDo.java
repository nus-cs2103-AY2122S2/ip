package yalebot;

import yalebot.Task;

public class ToDo extends Task {

    public ToDo(String name, boolean isMarked) {
        super(name, isMarked);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
