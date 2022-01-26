package duke.task;

public class Task {
    private final String title;
    private boolean isDone;

    public Task(String title)  {
        this.title = title;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getSaveFormat() {
        String isDoneSymbol = isDone ? "1" : "0";
        return String.format("%s | %s | %s", getClassSymbol(), isDoneSymbol, title);
    }

    public String getClassSymbol() {
        if (Todo.class.equals(this.getClass())) {
            return "T";
        } else if (Event.class.equals(this.getClass())) {
            return "E";
        } else if (Deadline.class.equals(this.getClass())) {
            return "D";
        } else {
            return " ";
        }

    }

    public boolean contains(String keywords) {
        return this.title.contains(keywords);
    }

    @Override
    public String toString() {
        String stateIcon = this.isDone ? "X" : " ";
        return String.format("[%s] %s",stateIcon, this.title);
    }
}
