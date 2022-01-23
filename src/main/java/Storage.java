import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    static TaskList readFile() throws DukeException, IOException{
        File f = new File("src/main/data/duke.txt");
        if (!f.exists()) {
            if (!f.createNewFile()) {
                throw new DukeException("Data file could not be created");
            }
        }
        Scanner sc = new Scanner(f);
        TaskList listOfSavedTasks = new TaskList();

        while (sc.hasNextLine()) {
            String currLine = sc.nextLine();
            addLineToTasks(currLine, listOfSavedTasks);
        }
        return listOfSavedTasks;
    }

    static void addLineToTasks(String currLine, TaskList listOfSavedTasks) {
        String[] currLineContents = currLine.split(" ~ ");
        String taskType = currLineContents[0];
        boolean isDone = currLineContents[1].equals("X") ? true : false ;
        String description = currLineContents[2];
        if (taskType.equals("T")) {
            listOfSavedTasks.add(new ToDo(description, isDone));
        } else if (taskType.equals("D")) {
            listOfSavedTasks.add(new Deadline(description, isDone, currLineContents[3]));
        } else if (taskType.equals("E")) {
            listOfSavedTasks.add(new Event(description, isDone, currLineContents[3]));
        }
    }

    static void saveToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter("src/main/data/duke.txt", false);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String currTask = String.format("%s ~ %s ~ %s ~ %s\n",
                    task.getTaskType(),
                    task.getStatusIcon(),
                    task.getDescription(),
                    task.getDate());
            fw.write(currTask);
        }
        fw.close();
    }
}
