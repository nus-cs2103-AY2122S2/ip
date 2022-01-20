public class TaskStorage {
    private String description;
    private boolean complete;
    private String type;
    private String time;
    private String command;

    public TaskStorage(String description, String type) {
        int slashIndex;
        int command;
        if (! type.equals("T")) {
            slashIndex = description.lastIndexOf("/");
            command = description.indexOf(" ", slashIndex);
            this.time = description.substring(command + 1);
            this.command = description.substring(slashIndex + 1, command);
        } else {
            slashIndex = description.length();
            this.time = "";
            this.command = "";
        }
        this.description = description.substring(0, slashIndex);
        this.complete = false;
        this.type = type;
    }

    public void taskDone() {
        this.complete = true;
    }

    public void taskUndone() {
        this.complete = false;
    }

    @Override
    public String toString() {
        String temp = "[ ]";
        if (complete) {
            temp = "[X]";
        }
        String timeCommand = "";
        if (! time.equals("")) {
            timeCommand = "(" + command + ": " + time + ")";
        }
        return "[" + type + "]" + temp + " " + description + timeCommand;
    }
}
