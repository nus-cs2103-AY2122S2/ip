public class Task {
    private boolean completed;
    private String name;
    private String type;
    private String extension = "";

    public Task(String name, String type) {
        this.name = name;
        this.completed = false;
        this.type = type;
    }

    public Task(String name, String type, String extension) {
        this.name = name;
        this.completed = false;
        this.type = type;
        this.extension = extension;
    }


    public void setDone() {
        this.completed = true;
    }

    public void setUndone() {
        this.completed = false;
    }

    public String saveString() {
        int status = completed == true ? 1 : 0;
        if (extension.isEmpty()) {
            return this.type + "|" + status + "|" + name;
        } else {
            return this.type + "|" + status + "|" + name + "|" + extension;
        }
    }

    @Override
    public String toString() {
        if (completed) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }

}
