public enum Command {
    mark, delete, bye, list, deadline, event, todo;

    public static boolean isMark(String input) {
        return input.equalsIgnoreCase(mark.name());
    }
    public static boolean isDelete(String input) {
        return input.equalsIgnoreCase(delete.name());
    }
    public static boolean isBye(String input) {
        return input.equalsIgnoreCase(bye.name());
    }
    public static boolean isList(String input) {
        return input.equalsIgnoreCase(list.name());
    }
    public static boolean isDeadline(String input) {
        return input.equalsIgnoreCase(deadline.name());
    }
    public static boolean isEvent(String input) {
        return input.equalsIgnoreCase(event.name());
    }
    public static boolean isTodo(String input) {
        return input.equalsIgnoreCase(todo.name());
    }
}
