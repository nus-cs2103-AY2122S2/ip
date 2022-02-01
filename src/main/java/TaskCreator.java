public class TaskCreator {
    private final char prefix;
    private final boolean isCompleted;
    private final String name;
    private final String date;

    TaskCreator(char prefix, boolean isCompleted, String name, String date) {
        this.prefix = prefix;
        this.isCompleted = isCompleted;
        this.name = name;
        this.date = date;
    }

    protected Task createTask() {
        Task task;
        if (prefix == 'D') {
            task = new Deadline(this.name, date);
        } else if (prefix == 'E') {
            task = new Event(this.name, date);
        } else if (prefix == 'T') {
            task = new ToDo(this.name);
        } else {
            System.out.println("Help!\n"
                    + "Weird looking task found in Duke's memory!\n"
                    + "Please go to Duke's memory to check!\n"
                    + "(Found a task that is not a Deadline, Event or ToDo\n"
                    + "in data/duke.txt)\n");
            task = null;
        }

        if (this.isCompleted && task != null) {
            task.mark();
        }
        return  task;
    }
}
