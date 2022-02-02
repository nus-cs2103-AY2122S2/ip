import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private static String buffer = " xxx ";

    public TaskList(String[] data) throws DukeException {
        try {
            tasks = new ArrayList<>();

            for (String task : data) {
                String[] splitTask = task.split(buffer);

                String type = splitTask[0];
                Boolean marked = (Integer.parseInt(splitTask[1]) > 0);
                String description = splitTask[2];

                switch (type) {
                    case "T":
                        tasks.add(new ToDos(description, marked));
                        break;
                    case "D":
                        tasks.add(new Deadlines(description, marked));
                        break;
                    case "E":
                        tasks.add(new Events(description, marked));
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            throw new DukeException("");
        }
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);

    }

    public void delete(int idx) {
        tasks.remove(idx);
    }

    public void list() {
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + "." + tasks.get(i - 1).toString());
        }
    }

    public int length() {
        return tasks.size();
    }

    public Task at(int idx) {
        return tasks.get(idx - 1);
    }
}
