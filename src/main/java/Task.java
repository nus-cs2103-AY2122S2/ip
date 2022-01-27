import java.util.List;

public class Task {
    private final String name;
    private boolean isDone;
    //can make isDone final for good practice

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void switchStatus() {
        this.isDone = !this.isDone;
    }

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

    public String getStatusIcon() {
        return (isDone? "X" : " ");
    }

    String getName() {
        return this.name;
    }

    Boolean getStatus() {
        return this.isDone;
    }

    public String toStorageString() {
        String status = isDone? "X" : ".";
        return String.format(status + " task " + name);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.name);
    }
}
