package duke.task;

import duke.exception.InvalidArgumentException;

import java.util.List;

public class Task {
    private final String name;
    private boolean isDone;
    //can make isDone final for good practice

    /**
     * Constructs Task class.
     * The constructor takes in the description of the task.
     * The default isDone status of the object is false.
     *
     * @param name description of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Switches task isDone status.
     */
    public void switchStatus() {
        this.isDone = !this.isDone;
    }

    /**
     * Switches task isDone status to true.
     *
     * @return Response text to indicate whether the action is successful.
     */
    public String markAsDone() {
        String output;
        if (this.isDone) {
            output = "Sorry, the task is actually done!";
        } else {
            output = String.format("Nice!, I have marked this task as done: \n      %s", this);
        }
        this.isDone = true;
        return output;
    }

    /**
     * Switches task isDone status to false.
     *
     * @return Response text to indicate whether the action is successful.
     */
    public String markAsNotDone() {
        String output;
        if (!this.isDone) {
            output = "Sorry, the task has not been done!";
        } else {
            output = String.format("Ok, I have marked this task as not done: \n      %s", this);
        }
        this.isDone = false;
        return output;
    }

    /**
     * Creates a Task object.
     * This is a factory constructor that calls the factor constructor of its appropriate child classes.
     *
     * @param description Task description from user input.
     * @return Response Text to be printed.
     * @throws InvalidArgumentException If the user input format is invalid/unknown.
     */
    public static Task createTask(List<String> description) throws InvalidArgumentException {
        if (description.get(0).equals("todo")) {
            return Todo.of(description);
        } else if (description.get(0).equals("deadline")) {
            return Deadline.of(description);
        } else if (description.get(0).equals("event")) {
            return Event.of(description);
        }
        throw new InvalidArgumentException();
    }

    /**
     * Gets the isDone status in text format.
     *
     * @return isDone status in text.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Gets the name variable.
     * The name is the description of the Task.
     *
     * @return name variable.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the isDone status.
     *
     * @return isDone status.
     */
    public Boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns the tasks in text format for storage.
     * The text format follows the initial user input.
     */
    public String toStorageString() {
        String status = isDone ? "X" : ".";
        return String.format(status + " task " + name);
    }

    /**
     * Returns text representing the task for User.
     *
     * @return Task in text format.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.name);
    }
}
