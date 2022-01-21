import java.util.List;

/**
 * The enum Command.
 */
public enum Command {
    /**
     * Greet command.
     */
    GREET,
    /**
     * Exit command.
     */
    EXIT,
    /**
     * List command.
     */
    LIST,
    /**
     * Mark command.
     */
    MARK,
    /**
     * Unmark command.
     */
    UNMARK,
    /**
     * To-do command.
     */
    TODO,
    /**
     * Event command.
     */
    EVENT,
    /**
     * Deadline command.
     */
    DEADLINE,
    /**
     * Remove command.
     */
    REMOVE,
    /**
     * Delete command.
     */
    DELETE,
    /**
     * Clear command.
     */
    CLEAR;

    private final String divString = "    ---------------------------------------------";
    private final String strPadding = "      ";


    /**
     * Gets the enum with the corresponding name.
     *
     * @param input the input
     * @return the by name
     * @throws DukeException the duke exception
     */
    public static Command getByName(String input) throws DukeException {
        for (Command cmd : values()) {
            if (cmd.toString().equalsIgnoreCase(input)) return cmd;
        }
        throw new DukeException("Invalid command!");
    }

    /**
     * Generic response.
     *
     * @param resString the res string
     */
    public void genericResponse(String resString) {
        System.out.println(divString);
        System.out.println(strPadding + resString);
        System.out.println(divString);
    }

    /**
     * Outputs an exit response using {@link #genericResponse(String)} method
     * and returns a `true` flag to signal the app to gracefully close.
     *
     * @return the boolean
     */
    public boolean exitResponse() {
        genericResponse("Pleasure to be of service, see you soon!");
        return true;
    }

    /**
     * List response by displaying the contents of the list.
     *
     * @param taskList the task list
     */
    public void listResponse(List<Task> taskList) {
        if (taskList.isEmpty()) {
            genericResponse("Your list is empty!");
        } else {

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < taskList.size(); i++) {
                Task curr = taskList.get(i);
                sb.append(String.format(strPadding + "%02d." + curr.toString(), i + 1));
                if (i != taskList.size() - 1) {
                    sb.append("\n");
                }

            }
            System.out.println(divString);
            System.out.println(strPadding + "Here are the tasks in your list: \n");
            System.out.println(sb);
            System.out.println(divString);
        }
    }

    /**
     * Toggle mark response to indicate when a task has been un-/marked.
     *
     * @param taskList the task list
     * @param inputArr the input array
     * @throws DukeException invalid index exception from {@link #validIdx(List, String[])}
     */
    public void toggleMarkResponse(List<Task> taskList, String[] inputArr) throws DukeException {
        int toggleIdx = validIdx(taskList, inputArr);
        Task toggleTask = taskList.get(toggleIdx);
        toggleTask.isMarked = inputArr[0].equals("mark");

        String markedString = "Nice! I've marked this task as done: ";
        String unmarkedString = "OK, I've marked this task as not done yet: ";
        String outString = toggleTask.isMarked ? markedString : unmarkedString;

        System.out.println(divString);
        System.out.println(strPadding + outString);
        System.out.println(strPadding + "   " + toggleTask);
        System.out.println(divString);
    }

    /**
     * Subtask response to indicate whether a `TO-DO`, `EVENT`, or `DEADLINE` has been added to
     * the input `taskList`.
     *
     * @param taskList the task list
     * @param input    the command given after splitting it by the keyword
     * @throws DukeException large list exception
     */
    public void subtaskResponse(List<Task> taskList, String input) throws DukeException {
        if (taskList.size() >= 100) {
            throw new DukeException("Too many items in the list! Please consider deleting old items.");
        }
        switch (this) {
            case TODO:
                taskList.add(new ToDo(input));
                break;
            case EVENT:
                try {
                    String[] eventCons = input.split(" /at ", 2);
                    taskList.add(new Event(eventCons[0], eventCons[1]));
                } catch (Exception e) {
                    throw new DukeException("Event delimiter not found. \n" +
                            "      Please use the delimiter \"/at\" to indicate when the event is happening.");
                }
                break;
            case DEADLINE:
                try {
                    String[] deadlineCons = input.split(" /by ", 2);
                    taskList.add(new Deadline(deadlineCons[0], deadlineCons[1]));
                } catch (Exception e) {
                    throw new DukeException("Deadline delimiter not found. \n" +
                            "      Please use the delimiter \"/by\" to indicate when the deadline is due.");
                }
        }

        String ackString = "Got it. I've added this task: ";
        String sizeString = String.format("Now you have %d tasks in the list.", taskList.size());

        System.out.println(divString);
        System.out.println(strPadding + ackString);
        System.out.println(strPadding + "   " + taskList.get(taskList.size() - 1));
        System.out.println(strPadding + sizeString);
        System.out.println(divString);
    }

    /**
     * Delete response indicating when a task at the given index has been deleted.
     *
     * @param taskList the task list
     * @param inputArr the input arr
     * @throws DukeException invalid index exception from {@link #validIdx(List, String[])}
     */
    public void deleteResponse(List<Task> taskList, String[] inputArr) throws DukeException {
        int deleteIdx = validIdx(taskList, inputArr);
        String ackString = "Noted. I've removed this task: ";
        Task removedTask = taskList.remove(deleteIdx);
        String sizeString = String.format("Now you have %d tasks in the list.", taskList.size());

        System.out.println(divString);
        System.out.println(strPadding + ackString);
        System.out.println(strPadding + "   " + removedTask);
        System.out.println(strPadding + sizeString);
        System.out.println(divString);
    }

    /**
     * Clear response to indicate that the input list has been cleared.
     *
     * @param taskList the task list
     */
    public void clearResponse(List<Task> taskList) {
        genericResponse("The list has been cleared!");
        taskList.clear();
    }

    private int validIdx(List<Task> taskList, String[] inputArr) throws DukeException {
        int idx;
        if (inputArr.length != 2) {
            throw new DukeException("Number of arguments should only be 2.");
        } else {
            idx = Integer.parseInt(inputArr[1]) - 1;
            if (idx < 0 || idx > taskList.size()) {
                throw new DukeException("Please enter a valid index.");
            }
        }
        return idx;
    }
}
