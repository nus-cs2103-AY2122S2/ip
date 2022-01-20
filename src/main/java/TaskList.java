import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public void addTo(String item, boolean marked) {
        Task newItem = new Task(item, marked);
        list.add(newItem);
    }

    public String listOut() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output += i+1 + "." + list.get(i).getStatusIcon()
                    + " " + list.get(i) + "\n";
        }
        return output;
    }

    public Task getItem(int itemNo) {
        return list.get(itemNo);
    }
}

