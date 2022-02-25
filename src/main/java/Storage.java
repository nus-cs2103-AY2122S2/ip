import java.io.*;

public class Storage {
    private static final String FILEPATH = "tasks.txt";

    public TaskList<Task> load() throws IOException {
        TaskList<Task> tasks = new TaskList<>();

        try {
            FileInputStream fileInputStream = new FileInputStream(FILEPATH);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            // Read objects into tasks list
            loadTask(tasks, objectInputStream);

            objectInputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Creating tasks.txt now...");
            new File(FILEPATH).createNewFile();
        } catch (IOException e) {
            System.out.println("Error occurred reading tasks.txt. Try again later.");
        }

        return tasks;
    }

    public void loadTask(TaskList<Task> tasks, ObjectInputStream objectInputStream) {
        boolean nextTask = true;

        while (nextTask) {
            Task t = null;
            try {
                t = (Task) objectInputStream.readObject();
            } catch (ClassNotFoundException|IOException e) {
                nextTask = false;
            }
            if (t!=null) {
                tasks.add(t);
            }
        }
    }

    public void updateStorage(TaskList<Task> tasks) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(FILEPATH);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            // Write updated tasks to file
            for (int i=1; i<=tasks.size(); i++) {
                objectOutputStream.writeObject(tasks.get(i));
            }

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("UPDATE TASKS: File not found");
        } catch (IOException e) {
            System.out.println("UPDATE TASKS: Error initializing stream");
        }
    }
}
