package myPackage;

public class ToDos extends Task {
    public boolean isDone;
    public ToDos(String description) {
        super(description);
    }

    public String markAsDone() {
        System.out.println(this.isDone);
        this.isDone = true;
        System.out.println(this.isDone);
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

}
