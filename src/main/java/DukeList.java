import java.util.ArrayList;

public class DukeList {
    protected ArrayList<Task> tasks = new ArrayList<>();

    //Add items
    public String add(Task t) {
        StringBuilder res = new StringBuilder();
        String line = "____________________________________________________________ \n";
        tasks.add(t);
        res.append(line).append("Got it. I've added this task: \n");
        res.append(t.toString()).append("\n");
        res.append("Now you have ").append(tasks.size());
        res.append(" tasks in the list.\n").append(line);
        return res.toString();
    }

    //show items in list
    public String printTasks() {
        StringBuilder res = new StringBuilder();
        String line = "____________________________________________________________ \n";
        res.append(line);
        res.append("Here are the tasks in your list: \n");
        for (int i = 0; i < tasks.size(); i ++) {
            res.append(String.format("%o.", i + 1));
            res.append(tasks.get(i).toString());
        }
        res.append(line);
        return res.toString();
    }

    //mark an item in the list
    public String mark(int i) {
        StringBuilder res = new StringBuilder();
        String line = "____________________________________________________________ \n";
        res.append(line).append("Nice! I've marked this task as done: \n");
        Task t = tasks.get(i - 1);
        t.setDone(true);
        res.append(t.toString()).append(line);
        return res.toString();
    }
    //unmark an item in the list
    public String unmark(int i) {
        StringBuilder res = new StringBuilder();
        String line = "____________________________________________________________ \n";
        res.append(line).append("OK, I've marked this task as not done yet: \n");
        Task t = tasks.get(i - 1);
        t.setDone(false);
        res.append(t.toString()).append(line);
        return res.toString();
    }
}
