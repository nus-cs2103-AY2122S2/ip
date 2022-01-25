import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    public static boolean doesFileExist(String filePath) {
        return Files.exists(Paths.get(filePath));
    }

    public static ArrayList<Task> fileToArrayList(File f) throws FileNotFoundException {
        Scanner sc = new Scanner(f);
        ArrayList<Task> arrList = new ArrayList<>();

        while (sc.hasNextLine()) {
            String taskStr = sc.nextLine();
            char taskType = taskStr.charAt(1);
            boolean isTaskDone = (taskStr.charAt(4) == 'X');
            taskStr = taskStr.substring(7);

            if (taskType == 'D') {
                String[] taskDetails = taskStr.split("\\(by: ", 2);
                String description = taskDetails[0];
                String date = taskDetails[1].substring(0, taskDetails[1].length()-1);
                Deadline deadline = new Deadline(description, date);
                if (isTaskDone) {
                    deadline.markAsDone();
                }
                arrList.add(deadline);
            } else if (taskType == 'E') {
                String[] taskDetails = taskStr.split("\\(at: ", 2);
                String description = taskDetails[0];
                String date = taskDetails[1];
                Event event = new Event(description, date);
                if (isTaskDone) {
                    event.markAsDone();
                }
                arrList.add(event);
            } else {
                Todo todo = new Todo(taskStr);
                if (isTaskDone) {
                    todo.markAsDone();
                }
                arrList.add(todo);
            }
        }
        return arrList;
    }

    public static void ArrayListToFile(ArrayList<Task> arrList, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : arrList) {
            fw.write(task.toString() + "\n");
        }
        fw.close();
    }

    public static void appendToFile(Task taskToAdd, String filePath) throws IOException{
        if (! doesFileExist("data/tasks.txt")) {
            String[] pathDetails = filePath.split("/", 2);
            String folderStr = pathDetails[0];
            File folder = new File(folderStr);
            folder.mkdir();
        }
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(taskToAdd.toString() + "\n");
        fw.close();
    }
}
