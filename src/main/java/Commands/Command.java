package Commands;

public enum Command {
    BYE("bye"), LIST("list"), DELETE("delete"),
    TOGGLEMARK("toggleMark"), TODO("todo"), DEADLINE("deadline"),
    EVENT("event"), FIND("find");

    private String str;

    Command(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return this.str;
    }
}

