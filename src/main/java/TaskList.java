import java.io.*;
import java.util.ArrayList;

public class TaskList<T extends Task> {
    static final String TASK_FILE = "";
    private ArrayList<T> tasks;

    @SuppressWarnings("unchecked")
    public TaskList() {;
        this.tasks = new ArrayList<>();

        try {
            FileInputStream fileInputStream = new FileInputStream("tasks.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            // Read objects into tasks list
            this.tasks = (ArrayList<T>) loadTask(objectInputStream, 0);

            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
           System.out.println("LOADING SAVED TASKS: No tasks found!");
        }
    }

    public static ArrayList<Task> loadTask(ObjectInputStream objectInputStream, int size) {
        ArrayList<Task> tasks = new ArrayList<>();
        boolean nextTask = true;

        while (nextTask) {
            Task t = null;
            try {
                t = (Task) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                nextTask = false;
            } catch (IOException e) {
                nextTask = false;
            }
            if (t!=null) {
                tasks.add(t);
            }
        }

        return tasks;
    }

    public void add(T task) {
        this.tasks.add(task);
    }

    public void updateTask() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("tasks.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            // Write updated tasks to file
            for (int i=0; i<this.tasks.size(); i++) {
                objectOutputStream.writeObject(this.tasks.get(i));
            }

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("UPDATE TASKS: File not found");
        } catch (IOException e) {
            System.out.println("UPDATE TASKS: Error initializing stream");
        }
    }

    public int size() {
        return this.tasks.size();
    }

    public T get(int i) {
        return this.tasks.get(i);
    }

    public void remove(int i) {
        this.tasks.remove(i-1);
    }
}