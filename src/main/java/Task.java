public abstract class Task {
    protected String objective;
    protected boolean done;

    public Task(String objective) {
        this.objective = objective;
        this.done = false;
    }
    public Task(String objective, Boolean done) {
        this.objective = objective;
        this.done = done;
    }

    public void mark() {
        this.done = true;
    }

    public void unmarked() {
        this.done = false;
    }

    public abstract String serialize();

    @Override
    public String toString() {
        return (done ? "[X]" : "[ ]") + " " + this.objective;
    }

    public static Task deserialize(String encoded) {
        String[] arr = encoded.split("\\|");
        boolean completed = arr[1].equals("1");
        if (arr[0].equals("T")) {
            return new ToDos(arr[2], completed);
        } else if (arr[0].equals("D")) {
            return new DeadLine(arr[2], completed, arr[3]);
        } else {
            return new DeadLine(arr[2], completed, arr[3]);
        }
    }
}


