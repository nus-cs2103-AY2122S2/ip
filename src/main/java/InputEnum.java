public enum InputEnum {
    QUIT("quit"),
    EXIT("exit"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    REMOVE("remove");

    private String str;
    InputEnum(String string) {
        str = string;
    }

    @Override
    public String toString() {
        return str;
    }
}
