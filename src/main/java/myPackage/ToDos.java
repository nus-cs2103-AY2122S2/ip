package myPackage;

public class ToDos extends Task {
    public boolean isDone;
    public ToDos(String description) {
        super(description);
        System.out.printf("[T][ ] %s%n", this.description);
        this.isDone = false;
    }

    public String markAsDone() {
        this.isDone = true;
        return "Nice! I've marked this task as done:\n" + String.format("[T][%s] %s%n", this.getStatusIcon(), this.description);
    }

    public String unmarkAsDone() {
        this.isDone = false;
        return "OK, I've marked this task as not done yet:\n" + String.format("[T][%s] %s%n", this.getStatusIcon(), this.description);
    }

    public String getTaskType() {
        return "T";
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public String getFullDescription() {
        return String.format("[T][%s] %s%n", this.getStatusIcon(), this.description);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
}
