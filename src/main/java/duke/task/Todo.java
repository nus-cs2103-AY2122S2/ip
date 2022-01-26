package duke.task;

public class Todo extends Task {

    /**
     *
     * @param description description of todo task
     */
    public Todo(String description) {
        super(description, "T");
    }

    /**
     * returns Todo in save data format (format to be saved in data.txt file for task storage)
     *
     * @return string representing the format it is saved in
     */
    @Override
    public String toSaveDataFormat() {
        String isDone = (super.checkIsDone() == true) ? "1" : "0";
        return String.format("%s|%s|%s\n", super.getTag(), isDone, super.getTaskDescription());
    }
}