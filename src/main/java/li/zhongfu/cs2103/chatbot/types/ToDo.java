package li.zhongfu.cs2103.chatbot.types;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public String toString() {
        return String.format("[T][%s] %s", this.getDone() ? "X" : " ", this.getName());
    }
}
