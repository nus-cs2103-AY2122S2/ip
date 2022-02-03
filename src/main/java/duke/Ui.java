package duke;

/**
 * This class deals with interactions from the user.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class Ui {

    /**
     *  Returns a list of helpful commands
     */
    public static String getHelp() {
        String str = "";
        str = str + "List of Available Commands\n"
                + "list\n"
                + "todo {task description}\n"
                + "deadline {task description} /by {DATE}\n"
                + "event {task description} /at {DATE}\n"
                + "mark {Task ID}\n"
                + "find {keyword}\n"
                + "delete {Task ID}\n"
                + "bye";
        return str;
    }


}
