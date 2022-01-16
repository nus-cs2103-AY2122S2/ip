/**
 * Echo chatbot.
 */
public class Echo {

    /** TaskList. */
    private final TaskList taskList;

    /**
     * Constructor for TaskList class.
     */
    public Echo() {
        this.taskList = new TaskList();
    }

    /**
     * Reads input string and determines action to perform.
     *
     * @param s Input string from user.
     */
    public void read(String s) throws EchoException {
        String[] split = s.split(" ");
        String command = split[0];
        if (command.equals("list")) {
            this.getTask();
        } else if (command.equals("mark")) {
            if (split.length == 1 || !isInteger(split[1])) {
                throw new EchoException("Please specify the task number(Integer).");
            }
            this.mark(Integer.parseInt(split[1]));
        } else if (command.equals("unmark")) {
            if (split.length == 1 || !isInteger(split[1])) {
                throw new EchoException("Please specify the task number(Integer).");
            }
            this.unmark(Integer.parseInt(split[1]));
        } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
            if (split.length == 1) {
                throw new EchoException(String.format("The description of a %s cannot be empty.", s));
            }
            if (command.equals("deadline") && !s.contains("/by")) {
                throw new EchoException("Please specify the deadline of the task. E.g. return book /by Sunday");
            }
            if (command.equals("event") && !s.contains("/at")) {
                throw new EchoException("Please specify the time of the event. E.g. meeting /at Mon");
            }
            this.addTask(command, s.substring(command.length() + 1));
        } else {
            System.out.println(
                    "       ☹ OOPS!!! I'm sorry, but I don't know what that means :-( \n" +
                            "       Please use the following commands: \n" +
                            "       todo | deadline | event | list | mark | unmark"
            );
        }
    }

    /**
     * Add taskList and replies that it has been added.
     *
     * @param type Type of task.
     * @param s Description of task.
     */
    public void addTask(String type, String s) {
        taskList.add(type, s);
    }

    /**
     * Returns taskList.
     */
    public void getTask() {
        System.out.println(taskList.toString());
    }

    /**
     * Marks taskList.
     *
     * @param i TaskList.
     */
    public void mark(int i) {
        taskList.mark(i);
    }

    /**
     * Unmark taskList.
     *
     * @param i TaskList.
     */
    public void unmark(int i) {
        taskList.unmark(i);
    }

    /**
     * Prints goodbye.
     */
    public void exit() {
        System.out.println("        Goodbye!");
    }

    /**
     * Checks if a string is an integer. Referenced from:
     * https://stackoverflow.com/questions/237159/whats-the-best-way-to-check-if-a-string-represents-an-integer-in-java.
     *
     * @param str String
     * @return True if str is an integer; otherwise false.
     */
    private static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}
