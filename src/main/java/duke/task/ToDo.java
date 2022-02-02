package duke.task;

/**
 * A concrete Task which contains
 * just a name description.
 */
public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String getDescription() {
        return String.format("[T]%s %s",
                super.getDoneStatusCheckbox(), super.getName());
    }

    @Override
    public String encodeTaskData() {
        String doneString = this.isDone() ? "Y" : "N";
        return String.format("T,%s,%s,%s", super.getName(), doneString, "NA");
    }
}
