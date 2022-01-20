import java.util.List;

public enum Command {
    GREET,
    EXIT, LIST, MARK, UNMARK,
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

    public void subtaskResponse(List<Task> taskList, String taskName) throws DukeException {
        if (taskList.size() >= 100) {
            throw new DukeException("Too many items in the list! Please consider deleting old items.");
        }
             if (this.equals(TODO))  { taskList.add(new ToDo(taskName)); }
        else if (this.equals(EVENT)) { taskList.add(new Event(taskName.split(" /at "))); }
        else                         { taskList.add(new Deadline(taskName.split(" /by "))); }

        String ackString = "Got it. I've added this task: ";
        String sizeString = String.format("Now you have %d tasks in the list.", taskList.size());

        System.out.println(divString);
        System.out.println(strPadding + ackString);
        System.out.println(strPadding + "   " + taskList.get(taskList.size() - 1));
        System.out.println(strPadding + sizeString);
        System.out.println(divString);
    }

    public void deleteResponse(List<Task> taskList, String[] inputArr) throws DukeException {
        int deleteIdx = validIdx(taskList, inputArr);
        String ackString = "Noted. I've removed this task: ";
        String sizeString = String.format("Now you have %d tasks in the list.", taskList.size());

        System.out.println(divString);
        System.out.println(strPadding + ackString);
        System.out.println(strPadding + "   " + taskList.remove(deleteIdx));
        System.out.println(strPadding + sizeString);
        System.out.println(divString);
    }

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
