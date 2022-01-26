import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task {
    protected String taskType;
    protected boolean isDone;
    protected String description;
    protected String date;
    protected LocalDate dateFormat;

    public Task(String taskType, String description, String date) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
        this.date = date;
        toLocalDate();
    }

    public Task(String taskType, boolean isDone, String description, String date) {
        this.description = description;
        this.isDone = isDone;
        this.taskType = taskType;
        this.date = date;
        toLocalDate();
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTaskType() { return this.taskType;}

    public boolean getIsDone() { return this.isDone;}

    public String getDescription() { return this.description; }

    public String getDate() {
        if (!this.taskType.equals(TaskType.TODO.getTaskType())) {
            return this.date;
        }
        return "";
    }

    public String getDateFormat() {
        try {
            if (!this.taskType.equals(TaskType.TODO.getTaskType()) && this.dateFormat != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd MMM yyyy]");
                return this.dateFormat.format(formatter);
            }
            return "Not a valid date";
        } catch (DateTimeException e) {
            return this.getDate();
        }
    }

    public boolean matchDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd-MM-yyyy]" + "[dd/MM/yyyy]");
            return this.dateFormat.toString().equals(LocalDate.parse(date, formatter).toString());
        } catch (DateTimeParseException e) {
            System.out.println("Not a valid date");
            return false;
        }
    }

    private void toLocalDate() {
        try {
            if (!this.taskType.equals(TaskType.TODO.getTaskType()) && !this.date.equals(this.getDateFormat())) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd-MM-yyyy]" + "[dd/MM/yyyy]");
                this.dateFormat = LocalDate.parse(this.date, formatter);
                this.date = this.getDateFormat();
            }
        } catch (DateTimeParseException e) {
            this.dateFormat = null;
        }
    }

    private boolean hasAlreadyMark() {
        return this.isDone;
    }

    private boolean hasAlreadyUnmark() {
        return !this.isDone;
    }

    public void setDone() {
        if (hasAlreadyMark()) {
            System.out.println("\tThis task is already marked!");
        } else {
            this.isDone = true;
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t  " + this);
        }
    }

    public void setUndone() {
        if (hasAlreadyUnmark()) {
            System.out.println("\tThis task is already unmarked!");
        } else {
            this.isDone = false;
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t  " + this);
        }
    }

    @Override
    public String toString() {
        return "[" + this.getTaskType() + "][" + this.getStatusIcon() + "] " + this.description;
    }
}