package DukeBot;

public class ToDo extends Task {

    public ToDo(String description) {
        super("T", description);
    }

    @Override
    public String dBText() {
        String complete = this.getCompleted() ? "1" : "0";
        return String.format("T|%s|%s", complete, this.getDescription());
    }
}
