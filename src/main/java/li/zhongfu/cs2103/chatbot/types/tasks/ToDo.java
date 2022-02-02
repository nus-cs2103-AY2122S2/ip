package li.zhongfu.cs2103.chatbot.types.tasks;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.getDone() ? "X" : " ", this.getName());
    }

    // no equals() and hashCode() -- use Task implementation
}
