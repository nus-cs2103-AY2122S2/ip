package duke;
public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Getter return name of Event task
     * @return String name of Event task
     */
    public String getName() {
        return name;
    }
}
