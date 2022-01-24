import java.util.List;

public enum Command {
    GREET,
    EXIT, LIST,
    MARK, UNMARK,
    TODO, EVENT, DEADLINE,
    REMOVE, DELETE, CLEAR;

    private final String divString = "    ---------------------------------------------";
    private final String strPadding = "      ";


    public static Command getByName(String input) throws DukeException {
        for (Command cmd : values()) {
            if (cmd.toString().equalsIgnoreCase(input)) return cmd;
        }
        throw new DukeException("Invalid command!");
    }

    public void genericResponse(String resString) {
        System.out.println(divString);
        System.out.println(strPadding + resString);
        System.out.println(divString);
    }

    public boolean exitResponse() {
        genericResponse("Pleasure to be of service, see you soon!");
        return true;
    }

    public void listResponse(List<Task> taskList) {
        if (taskList.isEmpty()) {
            genericResponse("Your list is empty!");
        } else {

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < taskList.size(); i++) {
                Task curr = taskList.get(i);
                sb.append(String.format(strPadding + "%d." + curr.toString(), i + 1));
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

    public void toggleMarkResponse(List<Task> taskList, String cmd, String toggleIdx) throws DukeException {
        if (taskList.isEmpty()) { throw new DukeException("The list is empty!"); }
        if (!argumentsCheck(toggleIdx)) { throw new DukeException("Invalid number of arguments!"); }

        int index = isValidInt(toggleIdx) - 1;
        if (!isValidIndex(index, taskList.size())) { throw new DukeException("Entry out of range!"); }

        Task toggleTask = taskList.get(index);
        toggleTask.isMarked = cmd.equals("mark");

        String markedString = "Nice! I've marked this task as done: ";
        String unmarkedString = "OK, I've marked this task as not done yet: ";
        String outString = toggleTask.isMarked ? markedString : unmarkedString;

        System.out.println(divString);
        System.out.println(strPadding + outString);
        System.out.println(strPadding + "   " + toggleTask);
        System.out.println(divString);
    }

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

    public void deleteResponse(List<Task> taskList, String deletionIdx) throws DukeException {
        if (taskList.isEmpty()) { throw new DukeException("The list is empty!"); }
        if (!argumentsCheck(deletionIdx)) { throw new DukeException("Invalid number of arguments!"); }

        int index = isValidInt(deletionIdx) - 1;
        if (!isValidIndex(index, taskList.size())) { throw new DukeException("Entry out of range!"); }

        try {
            String ackString = "Noted. I've removed this task: ";
            Task removedTask = taskList.remove(index);
            String sizeString = String.format("Now you have %d tasks in the list.", taskList.size());

            System.out.println(divString);
            System.out.println(strPadding + ackString);
            System.out.println(strPadding + "   " + removedTask);
            System.out.println(strPadding + sizeString);
            System.out.println(divString);
        } catch (Exception e) {
            throw new DukeException("Enter which entry you would like to delete.");
        }
    }

    public void clearResponse(List<Task> taskList) {
        genericResponse("The list has been cleared!");
        taskList.clear();
    }

    private int isValidInt(String intStr) throws DukeException {
        try {
            return Integer.parseInt(intStr);
        } catch (Exception e) {
            throw new DukeException("Please enter the entry NUMBER.");
        }
    }

    private boolean isValidIndex(int index, int listSize) {
        return (index >= 0 && index < listSize);
    }

    private boolean argumentsCheck(String input) {
        return (input.split(" ").length == 1);
    }

}
