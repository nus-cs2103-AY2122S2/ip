package duke.parser;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.format.DateTimeFormatter;

/**
 * Parser type: Storage parser
 * Parse all current tasks in chatbot to store in a suitable format
 */
public class StorageParser extends Parser {
    protected Task task;

    public StorageParser(Task task) {
        this.task = task;
    }

    @Override
    public String toString() {
        String returnString = "";
        if (this.task instanceof Todo) {
            returnString = returnString + "T | " + (this.task.getIsDone() ? "1 | " : "0 | ") + this.task.getDescription();
        }

        if (this.task instanceof Deadline) {
            String dateTime = ((Deadline) this.task).getDate().format(DateTimeFormatter.ofPattern("d/M/yyyy")) + " " + ((Deadline) this.task).getTime().format(DateTimeFormatter.ofPattern("HHmm"));
            returnString = returnString + "D | " + (this.task.getIsDone() ? "1 | " : "0 | ") + this.task.getDescription() + "| " + dateTime;
        }

        if (this.task instanceof Event) {
            String dateTime = ((Event) this.task).getDate().format(DateTimeFormatter.ofPattern("d/M/yyyy"));
            returnString = returnString + "E | " + (this.task.getIsDone() ? "1 | " : "0 | ") + this.task.getDescription() + "| " + dateTime;
        }

        return returnString + "\n";
    }
}
