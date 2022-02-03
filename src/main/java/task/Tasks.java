package task;

//Abstract class Task that Todos, Events, and deadlines inherit from.
public class Tasks {
    String name; // Name of task
    boolean completed; // Completion of task

    // Constructor for Task
    public Tasks(String taskName) {
        this.name = taskName;
        this.completed = false;
    }

    public Tasks(String taskName, boolean completion) {
        this.name = taskName;
        this.completed = completion;
    }

    // Completion of task
    public Tasks completeTask() {
        return new Tasks(name, true);
    }

    // Uncomplete the task
    public Tasks uncompleteTask() {
        return new Tasks(name, false);
    }

    // Get timing of task - overriden in todos and events
    String getTiming() {
        return "";
    }

    // Get timing of task - overriden in todos and events
    String getDeadline() {
        return "";
    }

    // Get name of task
    String getName() {
        return this.name;
    }

    // Get completion status of the task
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
