package duke;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import duke.tasks.*;
import duke.ui.UI;

public class Pikachu {
    private ArrayList<Task> inputList;

    //Constructor
    public Pikachu() {
        inputList = new ArrayList<Task>();
    }

    //Constructor for existing tasklist
    public Pikachu(ArrayList<Task> inputList) {
        this.inputList = inputList;
    }

    //Accessor
    public ArrayList<Task> getInputList() {
        return this.inputList;
    }

    /**
     * Parses input from the user. This is the main command for Pikachu.
     *
     * @param str Input from user.
     * @return String containing the appropriate response based on the user's input.
     */
    public String parseInput(String str) {
        String result;
        String[] split = str.split(" ");
        switch (split[0].toLowerCase()) {
        case "commands":
            result = listCommands();
            break;
        case "list":
            result = listTasks();
            break;
        case "sort":
            result = sortTasks();
            break;
        case "mark":
            result = markTask(str);
            break;
        case "unmark":
            result = unmarkTask(str);
            break;
        case "delete":
            result = deleteTask(str);
            break;
        case "todo":
            result = createToDoTask(str);
            break;
        case "deadline":
            result = createDeadlineTask(str);
            break;
        case "event":
            result = createEventTask(str);
            break;
        case "find":
            result = findTasks(str);
            break;
        default:
            //For non-recognizable inputs
            result = "Pikachu does not understand...";
            break;
        }

        return result;
    }

    /**
     * Returns list of available commands.
     * Syntax: commands
     *
     * @return String containing list of available commands.
     */
    public String listCommands() {
        return UI.printCommands();
    }

    /**
     * Returns list of tasks in tasklist.
     * Syntax: list
     *
     * @return String containing current list of tasks in tasklist.
     */
    public String listTasks() {
        String result;
        result = "Here are the tasks in your list:\n";
        int count = 1;
        for (Task t : inputList) {
            result += ("   " + count + ". " + t + "\n");
            count += 1;
        }
        count -= 1;
        result += "Now you have " + count + " task(s) in the list.";
        return result;
    }

    /**
     * Returns list of tasks in chronologically sorted order.
     * Syntax: sort
     *
     * @return String containing current list of tasks in chronologically sorted order.
    */
    public String sortTasks() {
        //Sorting chronologically, with todo's at the end
        PriorityQueue<Task> taskPrioQueue = new PriorityQueue<Task>(new TaskComparator());
        ArrayList<ToDo> toDos = new ArrayList<ToDo>();
        for (Task t : inputList) {
            //Separating the todo's from the rest
            if (t.getClass().getSimpleName().equals("ToDo")) {
                toDos.add((ToDo) t);
            } else {
                assert t instanceof Deadline || t instanceof Event;
                taskPrioQueue.add(t);
            }
        }

        String result;
        result = "Here are the tasks in your list:\n";
        int count = 1;
        //Printing the deadlines and events, sorted chronologically
        while (!taskPrioQueue.isEmpty()) {
            result += ("   " + count + ". " + taskPrioQueue.poll() + "\n");
            count += 1;
        }
        //Printing the todos
        for (ToDo t : toDos) {
            result += ("   " + count + ". " + t + "\n");
            count += 1;
        }
        count -= 1;
        result += "Now you have " + count + " task(s) in the list.";
        return result;
    }

    /**
     * Marks a given task as done.
     * Syntax: mark <index of task>
     *
     * @param str Input from user.
     * @return String containing confirmation of the marked task.
     */
    public String markTask(String str) {
        String[] split = str.split(" ");
        int index = 0;
        try {
            index = Integer.parseInt(split[1]) - 1;
        } catch (Exception e) {
            return "Invalid input for mark!";
        }
        //Prevent invalid array accesses
        if (index >= inputList.size() || index <= -1) {
            return "Invalid task number!";
        }
        Task t = inputList.get(index);
        t.mark();
        assert t.getStatus();
        return "Pikachu has marked this task as done!\n   > " + t;
    }

    /**
     * Marks a given task as undone.
     * Syntax: unmark <index of task>
     *
     * @param str Input from user.
     * @return String containing confirmation of the unmarked task.
     */
    public String unmarkTask(String str) {
        String[] split = str.split(" ");
        int index = 0;
        try {
            index = Integer.parseInt(split[1]) - 1;
        } catch (Exception e) {
            return "Invalid input for unmark!";
        }
        if (index >= inputList.size() || index <= -1) {
            return "Invalid task number!";
        }
        Task t = inputList.get(index);
        t.unmark();
        assert t.getStatus() == false;
        return "Pikachu has marked this task as not done yet!\n   > " + t;
    }

    /**
     * Deletes a given task from the tasklist.
     * Syntax: delete <index of task>
     *
     * @param str Input from user.
     * @return String containing confirmation of the deleted task.
     */
    public String deleteTask(String str) {
        String[] split = str.split(" ");
        int index = 0;
        try {
            index = Integer.parseInt(split[1]) - 1;
        } catch (Exception e) {
            return "Invalid input for delete!";
        }
        //Prevent invalid array accesses
        if (index >= inputList.size() || index <= -1) {
            return "Invalid task number!";
        }
        Task t = inputList.remove(index);
        assert !inputList.contains(t);

        return "Pikachu has deleted this task!\n   > " + t + "\n"
                + "You now have " + inputList.size() + " tasks in the list.";
    }

    /**
     * Creates a task of type "ToDo".
     * Syntax: todo <taskname>
     *
     * @param str Input from user.
     * @return String containing information of the created ToDo task.
     */
    public String createToDoTask(String str) {
        String[] split2 = str.split(" ", 2);
        try {
            ToDo t = new ToDo(split2[1]);
            inputList.add(t);
            return "Pikachu has added this task to the list!\n" + "   > " + t + "\n"
                    + "You now have " + inputList.size() + " tasks in the list.";
        } catch (Exception e) {
            return "Task description is empty!";
        }
    }

    /**
     * Creates a task of type "Deadline".
     * Syntax: deadline <taskname> /<yyyy-mm-dd hhmm of deadline>
     *
     * @param str Input from user.
     * @return String containing information of the created Deadline task.
     */
    public String createDeadlineTask(String str) {
        try {
            String[] split2 = str.split("/");
            String[] split3 = split2[1].split(" ");
            String[] split4 = split2[0].split(" ", 2);
            String name = split4[1];
            String date = split3[0];
            String time = split3[1];
            String[] yymmdd = date.split("-");
            LocalDateTime deadline = LocalDateTime.of(Integer.parseInt(yymmdd[0]), Integer.parseInt(yymmdd[1]),
                    Integer.parseInt(yymmdd[2]), Integer.parseInt(time.substring(0, 2)),
                    Integer.parseInt(time.substring(2, 4)));
            Deadline d = new Deadline(name, deadline);
            inputList.add(d);
            return "Pikachu has added this task to the list!\n" + "   > " + d + "\n"
                    + "You now have " + inputList.size() + " tasks in the list.";
        } catch (Exception e) {
            return "Task description is empty/Empty or invalid deadline timing has been specified!";
        }
    }

    /**
     * Creates a task of type "Event".
     * Syntax: event <taskname> /<yyyy-mm-dd hhmm of start> <yymmdd hhmm of end>
     *
     * @param str Input from user.
     * @return String containing information of the created Event task.
     */
    public String createEventTask(String str) {
        try {
            String[] split2 = str.split("/");
            String[] split3 = split2[1].split(" ");
            String[] split4 = split2[0].split(" ", 2);
            String name = split4[1];
            String dateStart = split3[0];
            String timeStart = split3[1];
            String dateEnd = split3[2];
            String timeEnd = split3[3];
            String[] yymmddStart = dateStart.split("-");
            String[] yymmddEnd = dateEnd.split("-");
            LocalDateTime start = LocalDateTime.of(Integer.parseInt(yymmddStart[0]),
                    Integer.parseInt(yymmddStart[1]), Integer.parseInt(yymmddStart[2]),
                    Integer.parseInt(timeStart.substring(0, 2)), Integer.parseInt(timeStart.substring(2, 4)));
            LocalDateTime end = LocalDateTime.of(Integer.parseInt(yymmddEnd[0]), Integer.parseInt(yymmddEnd[1]),
                    Integer.parseInt(yymmddEnd[2]), Integer.parseInt(timeEnd.substring(0, 2)),
                    Integer.parseInt(timeEnd.substring(2, 4)));
            if (end.isBefore(start)) {
                return "End time cannot be earlier than start time!";
            }
            Event e = new Event(name, start, end);
            inputList.add(e);
            return "Pikachu has added this task to the list!\n" + "   > " + e + "\n"
                    + "You now have " + inputList.size() + " tasks in the list.";
        } catch (Exception e) {
            return "Task description is empty/Empty or invalid event duration has been specified!";
        }
    }

    /**
     * Returns a list of all tasks containing the given keyword.
     * Syntax: find <keyword>
     *
     * @param str Input from user.
     * @return String containing all tasks containing the given keyword in its name.
     */
    public String findTasks(String str) {
        String result = "Here are the matching tasks in your list:\n";
        int count = 1;
        try {
            String[] split2 = str.split(" ", 2);
            for (Task t : inputList) {
                //Current task does not contain the keyword
                if (!t.getName().contains(split2[1])) continue;
                result += ("   " + count + ". " + t + "\n");
                count += 1;
            }
            return result;
        } catch (Exception e) {
            return "Please specify a keyword!";
        }
    }
}

class TaskComparator implements Comparator<Task> {
    /**
     * Overrides compare function, used by parser for sorting of tasks by chronological order.
     *
     * @param int t1: First task to be compared.
     * @param int t2: Second task to be compared.
     * @return int Returns -1 if t1 is earlier than t2, and 1 if vice versa. If equal, return 0.
     */
    public int compare(Task t1, Task t2) {
        LocalDateTime t1Val, t2Val;

        //Checking if t1 and t2 are deadlines or events, and casting them accordingly.
        if (t1.getClass().getSimpleName().equals("Deadline")) {
            Deadline t1Deadline = (Deadline) t1;
            t1Val = t1Deadline.getDeadline();
        } else {
            Event t1Event = (Event) t1;
            t1Val = t1Event.getStartTime();
        }

        if (t2.getClass().getSimpleName().equals("Deadline")) {
            Deadline t2Deadline = (Deadline) t2;
            t2Val = t2Deadline.getDeadline();
        } else {
            Event t2Event = (Event) t2;
            t2Val = t2Event.getStartTime();
        }

        return t1Val.compareTo(t2Val);
    }
}
