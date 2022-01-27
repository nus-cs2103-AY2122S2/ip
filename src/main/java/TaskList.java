import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;
    private int numItems;

    public TaskList() {
        list = new ArrayList<>();
        numItems = 0;
    }

    public TaskList(ArrayList<String> tasks) {
        this();
        addTasksFromFile(tasks);
    }

    public void addTask(Task task) {
        list.add(task);
        numItems++;
    }

    public String removeTaskAtIndex(int index) {
        if(numItems == 0) {
            return "Oops! Currently, you have no tasks :)";
        } else if(index < 1 || index > numItems) {
            return "Oops! Please enter a number between 1 and " + numItems + "!";
        } else {
            Task task = list.get(index);
            list.remove(index);
            numItems--;
            return "Alright! I've removed this task:\n" + task.toString() + "\n" + getNumTasksString();
        }
    }

    public String markTaskAtIndex(int index) {
        if(numItems == 0) {
            return "Oops! Currently, you have no tasks :)";
        } else if(index < 1 || index > numItems) {
            return "Oops! Please enter a number between 1 and " + numItems + "!";
        } else {
            Task task = this.list.get(index - 1);
            task.mark();
            return "Alright! I've marked this task as done:\n" + task.toString();
        }
    }

    public String unmarkTaskAtIndex(int index) {
        if(numItems == 0) {
            return "Oops! Currently, you have no tasks :)";
        } else if(index < 1 || index > numItems) {
            return "Oops! Please enter a number between 1 and " + numItems + "!";
        } else {
            Task task = this.list.get(index - 1);
            task.unmark();
            return "Alright! I've marked this task as not done yet:\n" + task.toString();
        }
    }

    public void addTasksFromFile(ArrayList<String> tasks) {
        tasks.forEach(task -> {
            this.list.add(parseFileString(task));
            this.numItems++;
        });

    }

    private Task parseFileString(String task) {
        // Assumption: one of the three cases is satisfied
        String[] components = task.split(", ");
        if(components[0].equals("T")) {
            Task t = new Task(components[2], components[1].equals("1"));
            return t;
        } else if(components[0].equals("D")) {
            Deadline d = Deadline.createDeadlineFromStorage(components[2], components[3], components[1].equals("1"));
            return d;
        } else {
            Event e = Event.createEventFromStorage(components[2], components[3], components[1].equals("1"));
            return e;
        }
    }

    public String getNumTasksString() {
        return numItems == 1 ? "Now there is 1 task in your list."
                :"Now there are " + numItems + " tasks in your list.";
    }

    public String getTasksString() {
        String tasks;
        if (numItems == 0) {
            tasks = "Currently, you have no tasks :)";
        } else {
            tasks = "Here are the tasks in your list:\n";
            int i = 1;
            for (Task task : this.list) {
                tasks += i + ". " + task.toString();
                i++;
                if(i <= numItems) {
                    tasks += "\n";
                }
            }
        }
        return tasks;
    }

    public String getFileString() {
        String fileString = "";
        for (int i = 0; i < numItems; i++) {
            fileString += list.get(i).toFileString();
            if(i != this.numItems - 1) {
                fileString += "\n";
            }
        }
        return fileString;
    }
}
