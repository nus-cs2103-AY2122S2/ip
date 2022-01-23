public class TaskStorage {
    private String description;
    private boolean isComplete;
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
        this.isComplete = false;
        this.type = type;
    }

    public void taskDone() {
        this.isComplete = true;
    }

    public void taskUndone() {
        this.isComplete = false;
    }

    public String toFileText() {
        String done;
        String timeDue;
        if (isComplete) {
            done = "1";
        } else {
            done = "0";
        }

        if (this.time.equals("")) {
            timeDue = " |";
        } else {
            timeDue = "| " + command + " " +  this.time + " |";
        }

        return "| " + type + " | " + done + " | " + description + timeDue;
    }

    @Override
    public String toString() {
        String temp = "[ ]";
        if (isComplete) {
            temp = "[X]";
        }
        String timeCommand = "";
        if (! time.equals("")) {
            timeCommand = "(" + command + ": " + time + ")";
        }
        return "[" + type + "]" + temp + " " + description + timeCommand;
    }
}
