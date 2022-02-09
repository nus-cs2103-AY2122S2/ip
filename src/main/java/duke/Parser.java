package duke;

/**
 * This class deals with making sense of the user command.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class Parser {
    protected String command;
    protected String desc = null;
    protected String date = null;

    public String getCmd() {
        return command;
    }

    public String getDate() {
        return date;
    }

    public String getDesc() {
        return desc;
    }
}
