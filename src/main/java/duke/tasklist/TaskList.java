package duke.tasklist;

import duke.exception.DukeException;
import duke.ui.Ui;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> list = null;

    public TaskList() {
        if (list == null) {
            list = new ArrayList<>();
        }
    }

    public void add(Task task) {
        list.add(task);
        new Ui().echo("Got it. I have added this task:\n" + task
                + "\nNow you have " + list.size() + " tasks in the list.");
    }

    public void delete(int pos) {
        String description = list.get(pos).toString();
        list.remove(pos);
        new Ui().echo("Noted. I've removed this task:\n" + description + "\nNow you have " + list.size() + " tasks in the list.");
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public void markTask(int pos) {
        list.get(pos).markTask(true, true);
    }

    public void unMarkTask(int pos) {
        list.get(pos).markTask(false, true);
    }

    public void readList() {
        new Ui().showLine();
        System.out.println("Here are the task in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).toString());
        }
        new Ui().showLine();
    }

    public void fetchData(ArrayList<Task> data) {
        list.clear();
        list.addAll(data);
    }

    /**
     * Finds a task by searching for a keyword.
     * @param input Keyword specified by the user.
     */
    public void find(String input) {
        int i = 0;
        new Ui().showLine();

        try {
            if (input.equals("")) {
                throw new DukeException("Empty body");
            }
            if (list.size() != 0) {
                System.out.println("Here are the matching tasks in your list:\n");
                for (Task task: list) {
                    LocalDate date = task.getDate();

                    if (task.getUserInput().contains(input)) {
                        System.out.println(++i + ". " + task);
                        continue;
                    }

                    if (date != null) {
                        if (date.toString().contains(input)
                                || date.format(DateTimeFormatter.ofPattern("MMM d yyyy")).contains(input)) {
                            System.out.println(++i + ".  " + task);
                        }
                    }
                }
            }
            if (i == 0) {
                System.out.println("☹ OOPS!!! Duke searched high and low but could not find the task that you want!");
            }
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! The description of the find command cannot be empty!");
        }

        new Ui().showLine();
    }
}
