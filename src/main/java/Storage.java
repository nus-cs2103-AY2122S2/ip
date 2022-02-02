import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        File dukeFile = new File(this.filePath);

        if (!Files.exists(Path.of(this.filePath))) {
            if (dukeFile.mkdir()) {
                System.out.println("File \"data\" has been created.");
            } else {
                System.out.println("Failed to create file \"data\".");
            }
        }

        File tasksPath = new File(this.filePath + "/tasks.txt");

        try {
            if (tasksPath.createNewFile()) {
                System.out.println("Duke set up successfully");
            } else {
                return read(tasksPath);
            }
        } catch (IOException e){
            throw new DukeException("Failed to create tasks file!");
        }
        return new ArrayList<Task>();
    }

    public ArrayList<Task> read(File filePath) throws DukeException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            int numOfTasks = Integer.parseInt(br.readLine());

            ArrayList<Task> tasks = new ArrayList<>();

            while (numOfTasks != 0) {
                // convert taskAsText into its string array form,
                // with 4 elements, being the prefix, completedState, name and date
                numOfTasks--;
                String taskAsText = br.readLine();
                Task currentTask = create(taskAsText);
                tasks.add(currentTask);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found!");
        } catch (IOException e) {
            throw new DukeException("Error with tasks.txt! Clear tasks.txt and run Duke again!");
        }
    }

    public Task create(String taskAsText) {
        String[] taskAsArray = taskAsText.split("/");

        char prefix = taskAsArray[0].charAt(0);
        boolean isCompleted = taskAsArray[1].equals("X");
        String name = taskAsArray[2];
        String date = taskAsArray[3];
        String time = taskAsArray[4];

        TaskCreator taskCreator = new TaskCreator(prefix,
                isCompleted,
                name,
                date,
                time);
        return taskCreator.createTask();
    }

    public void save(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter("data/tasks.txt", false);
            PrintWriter pw = new PrintWriter(fw);

            pw.write(tasks.getNumOfTasks() + "\n");
            pw.write(tasks.taskAsData());

            pw.flush();
            pw.close();

        } catch (IOException e) {
            throw new DukeException("Failed to save data!");
        }
    }

}
