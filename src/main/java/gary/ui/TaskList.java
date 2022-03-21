package gary.ui;

import gary.exception.GaryException;
import gary.task.Deadline;
import gary.task.Event;
import gary.task.Task;
import gary.task.ToDo;

import java.util.ArrayList;

/**
 * Represents a list of tasks to be done by user
 * Contains a set of operations to be performed on the list
 */
public class TaskList {
    public static ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList from a List of String
     *
     * @param t List of String to be decoded
     */
    public TaskList(ArrayList<String> t) {
        tasks = new ArrayList<Task>();
        this.decode(t);
    }

    /**
     * Convert List of Strings into List of Tasks
     *
     * @param lst List of Strings to be decoded into commands
     */
    public void decode(ArrayList<String> lst) {
        for (String str : lst) {
            char type = str.charAt(3);
            boolean isDone = str.charAt(5) == 'X';
            switch (type) {
                case 'T':
                    this.tasks.add(new ToDo(str.substring(8)));
                    break;
                case 'E':
                    String[] e = str.split("on:", 5);
                    this.tasks.add(new Event(e[0].substring(8), e[1]));
                    break;
                case 'D':
                    String[] d = str.split("by:", 5);
                    this.tasks.add(new Deadline(d[0].substring(8), d[1]));
                    break;
            }
            if (isDone) {
                int len = this.tasks.size();
                this.tasks.get(len - 1).toMark();
            }
        }
    }

    /**
     * Calls the method corresponding to user input
     *
     * @param input operation to be performed
     * @throws GaryException if method is invalid
     */
    public String invoke(String input) throws GaryException {
        try {
            String[] type = input.split(" ");
            switch (type[0]) {
                case "list":
                    return this.showList(2);
                case "mark":
                    return this.mark(input);
                case "unmark":
                    return this.unmark(input);
                case "delete":
                    return this.delete(input);
                case "help":
                    return this.showHelp();
                case "refresh":
                    return this.deleteAll();
                default:
                    throw new GaryException(input);
            }
        } catch (AssertionError e) {
            return "Your To-Do List is empty! :0";
        }
    }

    /**
     * Prints out Task List
     *
     * @param x type of message to be printed
     */
    public String showList(int x) {
        String msg = x == 1 ? "This is your current to-do list:" : "to do list:";
        if (this.tasks.isEmpty()) {
            return "No history recorded, what would you like to do today?";
        } else {
            int i = 1;
            for (Task curr : this.tasks) {
                msg += System.lineSeparator();
                msg += i + ". " + curr.toString();
                i++;
            }
        }
        return msg;
    }

    /**
     * Adds a ToDo Task
     *
     * @param t task to do
     */
    public String addTodo(String t) {
        ToDo next = new ToDo(t);
        tasks.add(next);
        Storage.saveTask(tasks);
        return "task added!";
    }

    /**
     * Adds an Event Task
     *
     * @param e Event to add
     * @param date day of event
     */
    public String addEvent(String e, String date) {
        Event next = new Event(e,date);
        tasks.add(next);
        Storage.saveTask(tasks);
        return "task added!";
    }

    /**
     * Adds a Deadline Task
     *
     * @param d DeadLines to add
     * @param date due date
     */
    public String addDeadline(String d, String date) {
        Deadline next = new Deadline(d,date);
        tasks.add(next);
        Storage.saveTask(tasks);
        return "task added!";
    }

    /**
     * Deletes unnecessary tasks
     */
    public String delete(String input) {
        assert !tasks.isEmpty();
        String[] seq = input.split(" ");
        String out = "";
        try {
            int len = seq.length;
            int prev = len; // max
            for(int i = 1; i < len; i++) {
                int curr = Integer.parseInt(seq[i]) - 1;
                int check = prev - curr;
                // maintain descending order
                assert check > 0;
                tasks.remove(curr);
                prev = curr;
            }
            Storage.saveTask(tasks);
        } catch (IndexOutOfBoundsException e) {
            return "Please enter a valid number or sequence e.g. 5 3 1" + System.lineSeparator()
                    + this.showList(1);
        } catch (AssertionError e) {
            return "Something went wrong... please enter tasks in descending order! e.g. 5 3 1"
                    + System.lineSeparator() + this.showList(1);
        }
        return "Done!";
    }

    /**
     * Marks completed task as done
     */
    public String mark(String input) {
        assert !tasks.isEmpty();
        String[] seq = input.split(" ");
        int len = seq.length;
        for(int i = 1; i < len; i++) {
            tasks.get(Integer.parseInt(seq[i]) - 1).toMark();
        }
        Storage.saveTask(tasks);
        return "Alright, updated!";
    }

    /**
     * Undo marking
     */
    public String unmark(String input) {
        assert !tasks.isEmpty();
        String[] seq = input.split(" ");
        int len = seq.length;
        for(int i = 1; i < len; i++) {
            tasks.get(Integer.parseInt(seq[i]) - 1).toUnmark();
        }
        Storage.saveTask(tasks);
        return "Alright, updated!";
    }

    /**
     * Print out Tasks that contains keyword even partially
     *
     * @param keyword used to find related tasks
     */
    public String find(String keyword) {
        String end = "Here are the matching tasks in your list:" + System.lineSeparator();
        int i = 0;
        for(Task t : tasks) {
            if (t.contain(keyword)) {
                i++;
                end +=  i + ". " + t.toString() + System.lineSeparator();
            }
        }
        if (i == 0) {
            end = "no match found :(";
        }
        return end;
    }

    public String showHelp() {
        String help = "Chat with me!!!" + System.lineSeparator();
        help += System.lineSeparator();
        help += "add Tasks:" + System.lineSeparator();
        help += "[todo task_name]" + System.lineSeparator();
        help += "[event name / 19-01-2022,2359]" + System.lineSeparator();
        help += "[deadline name / 19-01-2022,2359]" + System.lineSeparator();
        help += System.lineSeparator();

        help += "more functions:" + System.lineSeparator();
        help += "[list]: list out current todo-list" + System.lineSeparator();
        help += "[delete 2 1]: delete tasks 2 and 1 (descending order) " + System.lineSeparator();
        help += "[refresh]: delete all tasks" + System.lineSeparator();
        help += "[mark 2 3]: indicating tasks 2 & 3 as done" + System.lineSeparator();
        help += "[unmark 2 3]: indicating tasks 2 & 3 as incomplete (default)" + System.lineSeparator();
        help += "[find key word]: find tasks related to keyword(s)" + System.lineSeparator();
        return help;
    }

    public String deleteAll() {
        assert !tasks.isEmpty();
        tasks.clear();
        Storage.saveTask(tasks);
        return "To-Do List cleared! What would you like to do today? ^^";
    }
}
