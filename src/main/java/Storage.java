import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> ls = new ArrayList<Task>();
        File f = new File(filePath);
        
        try {
            Scanner sc = new Scanner(f);

            while (sc.hasNext()) {
                boolean isDone = false;
                Task newTask;
                String[] substring;

                //split each line in text file into [task type, isDone, task description]
                String[] data = sc.nextLine().split(" ", 3);

                //assign isDone
                switch(data[1]) {
                case "":
                    isDone = false;
                case "1":
                    isDone = true;
                }

                //add task to array list
                switch(data[0]) {
                case "T":
                    ls.add(new ToDo(data[2], isDone));
                    break;
                case "D":
                    substring = data[2].split(" /by ", 2);
                    try {
                        newTask = new Deadline(substring[0], substring[1], isDone);
                        ls.add(newTask);
                    } catch (InvalidFormatException e) {
                        //Should not happen since it is read from saved file
                    }
                    break;
                case "E":
                    substring = data[2].split(" /at ", 2);
                    newTask = new Event(substring[0], substring[1], isDone);
                    ls.add(newTask);
                    break;
                }
            }
            return ls;
        } catch (FileNotFoundException e) {
            return ls;
        }
    }

    public void save(TaskList taskList) {
        File f = new File(filePath);
        String directoryName = filePath.replace(f.getName(), "");
        File directory = new File(directoryName);
        directory.mkdirs();
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : taskList.toArrayList()) {
                fw.write(task.getTaskData());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Task list cannot be saved!");
        }
    }
}
