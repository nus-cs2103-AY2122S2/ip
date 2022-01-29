package duke;

public class Parser {

    public static String getCommand(String nextInput) {
        String[] words = nextInput.split(" ", 2);
        return words[0];
    }

    public static int getLength(String nextInput) {
        return nextInput.length();
    }

    public static String[] getDeadlineDetails(String nextInput) {
        return nextInput.split(" ", 2)[1].split("/by ");
    }

    public static String getTodoDetails(String nextInput) {
        return nextInput.split(" ", 2)[1];
    }

    public static String[] getEventDetails(String nextInput) {
        return nextInput.split(" ", 2)[1].split("/at ");
    }

    public static int getTaskId(String nextInput) {
        return Integer.valueOf(nextInput.split(" ", 2)[1]);
    }
}
