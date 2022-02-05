package duke;

class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        if (super.getStatus() == 1) {
            return "[T][X] " + super.getName();
        } else {
            return "[T][ ] " + super.getName();
        }
    }
}
