public abstract class Task {
    String activity;
    int status;
    String type = " ";

    public Task(String activity, String type) {
        this.activity = activity;
        this.type = type;
    }
    public void addedTask() {
        System.out.println("Got it. I've added this task");
    }

    public abstract String getStatus();

}
