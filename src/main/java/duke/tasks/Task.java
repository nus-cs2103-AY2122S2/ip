package duke.tasks;

import duke.exception.DukeException;

public abstract class Task {

    protected String description;

    protected Boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " + this.description : "[ ] " + this.description);
    }

    public void done() {
        isDone = true;
    }

    public void notDone() {
        isDone = false;
    }

    /**
     * Determines which kind of task is to created based on the Initial given
     * @param description The parameters of the task
     * @return The type of Task to be created
     * @throws DukeException
     */
    public static Task createTask(String[] description) throws DukeException {
        switch (description[0]) {
            case "T":
                return new Todo(description[1], description[2]);
            case "E":
                return new Event(description[1], description[2], description[3]);
            case "D":
                return new Deadline(description[1], description[2], description[3]);
            default:
                throw new DukeException("Invalid Task");
        }
    }

    abstract public String getTime();

    public Boolean getIsDone() {return this.isDone;}

    public String getDescription() {return this.description;}

}