public class Quest {
    private String description;
    private int status;
    private static final String done = "[X] ";
    private static final String undo = "[ ] ";
    private String type;
    private String period;

    public Quest(String description) {
        this.description = description;
        this.status = 0;
    }

    public Quest(String description, int status) {
        this.description = description;
        this.status = status;
    }

    public Quest(String description, int status, String type) {
        this.description = description;
        this.status = status;
        this.type = type;
    }

    public Quest(String description, int status, String type, String period) {
        this.description = description;
        this.status = status;
        this.type = type;
        this.period = period;
    }

    public Quest() {
        this.description = " ";
    }

    public void completeQuest() {
        this.status = 1;
    }

    public void incompleteQuest() {
        this.status = 0;
    }

    public String getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }

    public int getStatus() {
        return this.status;
    }

    public String getPeriod() {
        return this.period;
    }

    @Override
    public String toString() {
        return this.description;
    }

    public String statusToString() {
        if (this.status == 0) {
            return undo + this.description;
        }
        return done + this.description;
    }
}
