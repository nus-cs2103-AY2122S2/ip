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
    public void mark(String task, ArrayList<Task> list) {
        Ui.lineOne();
        System.out.println("Good Job! ^_^");
        System.out.println("Task number " + task + " has been marked as done!");
        int tNum = Integer.parseInt(task);
        list.get(tNum - 1).done();
        System.out.println(list.get(tNum - 1));
        Ui.lineOne();
    }

    /**
     * Method to unmark task as done.
     * @param task the task number as a string.
     * @param list list containing all the tasks.
     */
    public void unmark(String task, ArrayList<Task> list) {
        Ui.lineOne();
        System.out.println("I've unmarked task number " + task);
        System.out.println("Complete it soon! ^_^");
        int tNo = Parser.stringToInt(task);
        list.get(tNo - 1).undo();
        System.out.println(list.get(tNo - 1));
        Ui.lineOne();
    }

    /**
     * Method to delete a task from the list.
     * @param task the task number as a string.
     * @param list list containing all the tasks.
     */
    public void delete(String task, ArrayList<Task> list) {
        Ui.lineOne();
        System.out.println("Noted. I've removed this task:");
        int t2No = Parser.stringToInt(task);
        System.out.println(list.get(t2No - 1));
        list.remove(t2No - 1);
        Ui.lineOne();
    }

    /**
     * Method to create an event task and add it to the list.
     * @param desc description of the event.
     * @param at additional information.
     * @param list list containing all the tasks.
     */
    public void event(String desc, String at, ArrayList<Task> list) {
        Ui.lineTwo();
        System.out.println("New task added:");
        String[] date = Parser.splitSpace(at);
        Task t2 = new Event(desc, LocalDate.parse(date[1]));
        list.add(t2);
        System.out.println(t2);
        System.out.println("You have " + list.size() + " tasks left now! ^_^");
        Ui.lineTwo();
    }

    /**
     * Method to create a todo task and add it to the list.
     * @param desc description of the event.
     * @param list list containing all the tasks.
     */
    public void todo(String desc, ArrayList<Task> list) {
        Ui.lineTwo();
        System.out.println("New task added:");
        Task t = new ToDo(desc);
        list.add(t);
        System.out.println(t);
        System.out.println("You have " + list.size() + " tasks left now! ^_^");
        Ui.lineTwo();
    }

    /**
     * Method to create a deadline task and add it to the list.
     * @param desc description of the event.
     * @param by additional information
     * @param list list containing all the tasks.
     */
    public void deadline(String desc, String by, ArrayList<Task> list) {
        Ui.lineTwo();
        System.out.println("New task added:");
        String[] date = Parser.splitSpace(by);
        Task t2 = new Deadline(desc, LocalDate.parse(date[1]));
        list.add(t2);
        System.out.println(t2);
        System.out.println("You have " + list.size() + " tasks left now! ^_^");
        Ui.lineTwo();
    }

    /**
     * Method that returns a string.
     * @return String
     */
    public String returnMatching() {
        String string = "Here are the matching tasks:";
        System.out.println(string);
        return string;
    }

    /**
     * Method to find a task.
     * @param desc description of the task to be found.
     * @param list list containing all the tasks.
     */
    public void find(String desc, ArrayList<Task> list) {
        Ui.lineTwo();
        returnMatching();
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).description.contains(desc)) {
                System.out.println(i + 1 + ". " + list.get(i));
            }
        }
        Ui.lineTwo();
    }
}
