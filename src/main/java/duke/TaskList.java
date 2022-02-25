package duke;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class that contains all task related functions.
 */
public class TaskList {

    /**
     * Method to mark task as done.
     * @param task the task number as a string.
     * @param list list containing all the tasks.
     */
    public void mark(String task, ArrayList<Task> list, StringBuilder str) {
        str.append(Ui.lineOne());
        str.append("Good Job! ^_^\n");
        str.append("Task number ").append(task).append(" has been marked as done!\n");
        int tNum = Integer.parseInt(task);
        list.get(tNum - 1).done();
        str.append(list.get(tNum - 1)).append("\n");
        str.append(Ui.lineOne());
    }

    /**
     * Method to unmark task as done.
     * @param task the task number as a string.
     * @param list list containing all the tasks.
     */
    public void unmark(String task, ArrayList<Task> list, StringBuilder str) {
        str.append(Ui.lineOne());
        str.append("I've unmarked task number ").append(task).append("\n");
        str.append("Complete it soon! ^_^\n");
        int tNo = Parser.stringToInt(task);
        list.get(tNo - 1).undo();
        str.append(list.get(tNo - 1)).append("\n");
        str.append(Ui.lineOne());
    }

    /**
     * Method to delete a task from the list.
     * @param task the task number as a string.
     * @param list list containing all the tasks.
     */
    public void delete(String task, ArrayList<Task> list, StringBuilder str) {
        str.append(Ui.lineOne());
        str.append("Noted. I've removed this task:\n");
        int t2No = Parser.stringToInt(task);
        str.append(list.get(t2No - 1)).append("\n");
        list.remove(t2No - 1);
        str.append(Ui.lineOne());
    }

    /**
     * Method to create an event task and add it to the list.
     * @param desc description of the event.
     * @param at additional information.
     * @param list list containing all the tasks.
     */
    public void event(String desc, String at, ArrayList<Task> list, StringBuilder str) {
        str.append(Ui.lineTwo());
        str.append("New task added:\n");
        String[] date = Parser.splitSpace(at);
        Task t2 = new Event(desc, LocalDate.parse(date[1]));
        list.add(t2);
        str.append(t2).append("\n");
        str.append("You have ").append(list.size()).append(" tasks left now! ^_^\n");
        str.append(Ui.lineTwo());
    }

    /**
     * Method to create a todo task and add it to the list.
     * @param desc description of the event.
     * @param list list containing all the tasks.
     */
    public void todo(String desc, ArrayList<Task> list, StringBuilder str) {
        str.append(Ui.lineTwo());
        str.append("New task added:\n");
        Task t = new ToDo(desc);
        list.add(t);
        str.append(t).append("\n");
        str.append("You have ").append(list.size()).append(" tasks left now! ^_^\n");
        str.append(Ui.lineTwo());
    }

    /**
     * Method to create a deadline task and add it to the list.
     * @param desc description of the event.
     * @param by additional information
     * @param list list containing all the tasks.
     */
    public void deadline(String desc, String by, ArrayList<Task> list, StringBuilder str) {
        str.append(Ui.lineTwo());
        str.append("New task added:\n");
        String[] date = Parser.splitSpace(by);
        Task t2 = new Deadline(desc, LocalDate.parse(date[1]));
        list.add(t2);
        str.append(t2).append("\n");
        str.append("You have ").append(list.size()).append(" tasks left now! ^_^\n");
        str.append(Ui.lineTwo());
    }

    /**
     * Method that returns a string.
     * @return String
     */
    public String returnMatching() {
        String string = "Here are the matching tasks:\n";
        return string;
    }

    /**
     * Method to find a task.
     * @param desc description of the task to be found.
     * @param list list containing all the tasks.
     */
    public void find(String desc, ArrayList<Task> list, StringBuilder str) {
        str.append(Ui.lineTwo());
        str.append(returnMatching());
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).description.contains(desc)) {
                str.append(i+1).append(". ").append(list.get(i)).append("\n");
            }
        }
        str.append(Ui.lineTwo());
    }
}
