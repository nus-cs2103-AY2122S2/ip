package myboss;

public class Parser {

    public static String[] splitUserCmd(String userCmd) {
        return userCmd.split(" ",2);
    }

    public static int getTaskIndex(String[] userCmd) {
        return Integer.parseInt(userCmd[1]) - 1;
    }

    public static String getRemainingUserCmd(String[] userCmd) {
        if (userCmd.length > 1) {
            return userCmd[1];
        } else {
            return "";
        }
    }

    public static String[] parseDeadlineUserCmd(String userCmd) {
        String taskName = userCmd.substring(0, userCmd.indexOf("/by") - 1);
        String timeBy = userCmd.substring(userCmd.indexOf("/by") + 4);
        return new String[] {taskName, timeBy};
    }

    public static String[] parseEventUserCmd(String userCmd) {
        String taskName = userCmd.substring(0, userCmd.indexOf("/at") - 1);
        String timeRange = userCmd.substring(userCmd.indexOf("/at") + 4);
        return new String[] {taskName, timeRange};
    }
}
