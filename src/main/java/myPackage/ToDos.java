package myPackage;

public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
        System.out.printf("[T][ ] %s%n", this.description);
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("[T][%s] %s%n", this.getStatusIcon(), this.description);
    }

    public void unmarkAsDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.printf("[T][%s] %s%n", this.getStatusIcon(), this.description);
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
