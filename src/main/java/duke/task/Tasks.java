package duke.task;

//Abstract class Task that Todos, Events, and deadlines inherit from.
public class Tasks {
    private final String name; // Name of duke.task
    boolean completed; // Completion of duke.task

    // Constructor for Task
    public Tasks(String taskName) {
        this.name = taskName;
        this.completed = false;
    }

    public Tasks(String taskName, boolean completion) {
        this.name = taskName;
        this.completed = completion;
    }

    // Completion of duke.task
    public Tasks completeTask() {
        return new Tasks(name, true);
    }

    // Uncomplete the duke.task
    public Tasks uncompleteTask() {
        return new Tasks(name, false);
    }

    // Get timing of duke.task - overriden in todos and events
    public String getTiming() {
        return "";
    }

    // Get timing of duke.task - overriden in todos and events
    public String getDeadline() {
        return "";
    }

    // Get name of duke.task
    public String getName() {
        return this.name;
    }

    // Get completion status of the duke.task
    Boolean getCompletion() {
        return this.completed;
    }

    public String toDatabaseString() {
        return "";
    }

    // toString return tasks
    public String toString() {
        return "";
    }
}
