package seedu.duke;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<Task>(tasks);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public String add(Task t) {
        tasks.add(t);

        return "Got it. I've added this task:\n      " + t + count();
    }

    public String list() {
        String tempStr = "Here are the tasks in your list:\n    ";
        for (int i = 0; i < tasks.size(); i++) {
            if (i != 0) {
                tempStr += "\n    ";
            }
            tempStr += (i + 1) + ". " + tasks.get(i);
        }

        return tempStr;
    }

    public String mark(int idx) throws DukeException {
                
        if (idx < 1 || idx > tasks.size()) {
            throw new DukeIdxOOBException();
        }
        
        tasks.get(idx - 1).mark();
        
        return "Nice! I've marked this task as done:\n      " + tasks.get(idx - 1);

    }

    public String unmark(int idx) throws DukeException {
                
        if (idx < 1 || idx > tasks.size()) {
            throw new DukeIdxOOBException();
        }
        
        tasks.get(idx - 1).unmark();
        
        return "OK, I've marked this task as not done yet:\n      " + tasks.get(idx - 1);

    }

    public String delete(int idx) throws DukeException {

        if (tasks.size() == 0) {
            throw new DukeException("OOPS!!! There is nothing on the list to delete!");
        }

        if (idx < 1 || idx > tasks.size()) {
            throw new DukeIdxOOBException();
        }

        return "Noted. I've removed this task:\n      " + tasks.remove(idx - 1) + count();

    }

    //Returns a string that describes the number of tasks in the list
    public String count() {
        String isSingular = "s";
        
        if (tasks.size() == 1) {
            isSingular = "";
        }
        
        return "\n    Now you have " + tasks.size() + " task" + isSingular + " in your list.";
    }

}
