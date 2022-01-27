public class StorageParser extends Parser {
    protected Task task;

    StorageParser(Task task) {
        this.task = task;
    }

    @Override
    public String toString() {
        String returnString = "";
        if (this.task instanceof Todo) {
            returnString = returnString + "T | " + (this.task.getIsDone() ? "1 | " : "0 | ") + this.task.getDescription();
        }

        if (this.task instanceof Deadline) {
            returnString = returnString + "D | " + (this.task.getIsDone() ? "1 | " : "0 | ") + this.task.getDescription() + "| " + ((Deadline) this.task).getTime();
        }

        if (this.task instanceof Event) {
            returnString = returnString + "E | " + (this.task.getIsDone() ? "1 | " : "0 | ") + this.task.getDescription() + "| " + ((Event) this.task).getTime();
        }

        return returnString + "\n";
    }
}
