package duke.task;

import duke.exception.DukeException;

public class Task {

    private boolean status;
    private String details;
    private int id;

    //ToDo
    public Task(String details) {
        this.details = details;
        this.status = false;
    }

    public String mark() {
        this.status = true;
        return "Task " + details + " has been marked completed.";
    }

    public String unmark() {
        this.status = false;
        return "Task " + details + " has been marked incomplete.";
    }

    @Override
    public String toString() {
        String out = "";
        if (status) {
            out = " | 1 | " + details;
        } else {
            out = " | 0 | " + details;
        }
        return out;
    }

    public static boolean is_valid_task(String details, String type) throws DukeException {
        if (details.length() == 0) {
            throw new DukeException("☹ OOPS!!! The description of a " + type + " cannot be empty.");
        } else if ((type.equals("deadline") || type.equals("event")) && !details.contains("/")) {
            throw new DukeException("☹ OOPS!!! The date of a " + type + " cannot be empty. Use / and type the date after it");
        } else if ((type.equals("deadline") || type.equals("event")) && details.endsWith("/")) {
            throw new DukeException("☹ OOPS!!! The date of a " + type + " cannot be empty. Type the date after your /");
        } else {
            return true;
        }
    }

}
