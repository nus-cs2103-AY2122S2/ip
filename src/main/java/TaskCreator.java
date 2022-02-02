public class TaskCreator {
    private final char prefix;
    private final boolean isCompleted;
    private final String name;
    private final String date;
    private final String time;

    TaskCreator(char prefix, boolean isCompleted, String name, String date, String time) {
        this.prefix = prefix;
        this.isCompleted = isCompleted;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    protected Task createTask() {
        Task task;
        if (prefix == 'D') {
            task = new TaskDeadline(this.name, this.date, this.time);
        } else if (prefix == 'E') {
            task = new TaskEvent(this.name, this.date, this.time);
        } else if (prefix == 'T') {
            task = new TaskToDo(this.name);
        } else {
            System.out.println("Help!\n"
                    + "Weird looking task found in Duke's memory!\n"
                    + "Please go to Duke's memory to check!\n"
                    + "(Found a task that is not a Deadline, Event or ToDo\n"
                    + "in data/duke.txt)\n");
            task = new TaskEmpty();
        }

        if (this.isCompleted && !task.isEmptyTask()) {
            task.mark();
        }
        return  task;
    }
}
