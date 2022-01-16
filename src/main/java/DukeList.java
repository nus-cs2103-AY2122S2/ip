import java.util.ArrayList;

public class DukeList {
    private ArrayList<Task> tasks = new ArrayList<>();

    //Add items
    public String add(Task t) {
        StringBuilder res = new StringBuilder();
        String line = "____________________________________________________________ \n";
        tasks.add(t);
        res.append(line);
        res.append("Added: ").append(t.getName()).append("\n");
        res.append(line);
        return res.toString();
    }

    //show items in list
    public String printTasks() {
        StringBuilder res = new StringBuilder();
        String line = "____________________________________________________________ \n";
        res.append(line);
        res.append("Here are the tasks in your list: \n");
        for (Task t : tasks) {
            res.append(t.toString());
        }
        res.append(line);
        return res.toString();
    }

    //mark an item in the list
    public String mark(int i) {
        StringBuilder res = new StringBuilder();
        res.append("Nice! I've marked this task as done: \n");
        for (Task t : tasks) {
            if (t.getId() == i) {
                t.setDone(true);
                res.append(t.toString());
            }
        }
        return res.toString();
    }
    //unmark an item in the list
    public String unmark(int i) {
        StringBuilder res = new StringBuilder();
        res.append("OK, I've marked this task as not done yet: \n");
        for (Task t : tasks) {
            if (t.getId() == i) {
                t.setDone(false);
                res.append(t.toString());
            }
        }
        return res.toString();
    }
}
