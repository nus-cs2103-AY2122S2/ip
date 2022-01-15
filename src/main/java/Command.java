public enum Command {
    LIST("list", 1),
    BYE("bye", 1),
    MARK("mark", 2),
    UNMARK("unmark", 2),
    DELETE("delete", 2),
    TODO("todo", 2),
    EVENT("event", 3),
    DEADLINE("deadline", 3);

    public String val;
    public int length;

    Command(String envUrl, int length) {
        this.val = envUrl;
        this.length = length;
    }

    }