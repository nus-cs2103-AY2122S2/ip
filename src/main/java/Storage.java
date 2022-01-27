import java.io.*;
import java.util.*;

public class Storage {
    private String dataPath;
    private File file;


    public Storage(String dataPath) {
        this.dataPath = dataPath;
        checkFile();
    }

    public void checkFile() {
        try {
            this.file = new File(this.dataPath);
            if (file.createNewFile()) {
                System.out.println("File is not found. A new file has been created for you.");
            } else {
                System.out.println("File is found.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeToFile(ArrayList<Task> taskList) {
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(this.file, false));
            for (Task task : taskList) {
                if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    printWriter.println("D-" + deadline.description + "-" + deadline.dateAndTime);
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    printWriter.println("E-" + event.description + "-" + event.dateAndTime);
                } else if (task instanceof Todo) {
                    Todo todo = (Todo) task;
                    printWriter.println("T-" + todo.description);
                }
            }
            printWriter.close();
        } catch (IOException e) {
            System.out.println("An error has occured.");
        }
    }

    public ArrayList<Task> readFromFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.file));
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                String[] taskDetails = currentLine.split("-");
                Task task;
                if (currentLine.charAt(0) == 'D') {
                    task = new Deadline(taskDetails[1], taskDetails[2]);
                } else if (currentLine.charAt(0) == 'E') {
                    task = new Event(taskDetails[1], taskDetails[2]);
                } else {
                    task = new Todo(taskDetails[1]);
                }
                taskList.add(task);
                currentLine = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
    }
}
