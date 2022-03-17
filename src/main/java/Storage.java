import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Scanner;

public class Storage {
    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    private Task convertStringToTask(String description) {
        String[] splitDescription = description.split("\\|");
        String taskType = splitDescription[0];
        Task tempTask = new Task("Temp task");
        switch (taskType) {
        case "T":
            Task newToDo = new ToDos(splitDescription[2]);
            if (splitDescription[1].equals("1")) {
                newToDo.markAsDone();
            }
            tempTask = newToDo;
            break;

        case "D":
            Task newDeadline = new Deadlines(splitDescription[2], new DateTime(splitDescription[3].split("[- ]")));
            if (splitDescription[1].equals("1")) {
                newDeadline.markAsDone();
            }
            tempTask = newDeadline;
            break;

        case "E":
            Task newEvent = new Events(splitDescription[2], new DateTime(splitDescription[3].split("[- ]")));
            if (splitDescription[1].equals("1")) {
                newEvent.markAsDone();
            }
            tempTask = newEvent;
            break;

        default:
            System.out.println("All tasks loaded!");

        }
        return tempTask;
    }

    public ArrayList<Task> load() throws IOException {
        String home =  System.getProperty("user.home"); // base directory
        // following code should give me [HOME_DIRECTORY]/Desktop/iP/data
        java.nio.file.Path path = java.nio.file.Paths.get(home,"Desktop", "iP", "data");
        ArrayList<Task> masterList = new ArrayList<>();
        try {
            File dukeStore = new File(path + this.filePath);
            Scanner fileReader = new Scanner(dukeStore);
            while (fileReader.hasNextLine()) {
                masterList.add(convertStringToTask(fileReader.nextLine()));
            }
        } catch (IOException e) {
            Path filePath = Paths.get("data");
            boolean isDirectoryExists = Files.exists(filePath);
            if (!isDirectoryExists) {
                new File("data").mkdir();
            }
            new File(path + "/duke.txt").createNewFile();
        }

        return masterList;
    }

    public void saveAllTask(TaskList tasklist) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (int i = 0; i < tasklist.size(); i++) {
            fw.write(taskToString(tasklist.get(i)));
            fw.write("\n");
        }
        fw.close();
    }

    private final String taskToString(Task task) {
        String toReturn = "";
        if (task instanceof ToDos) {
            toReturn += "T|";
        } else if (task instanceof Deadlines) {
            toReturn += "D|";
        } else if (task instanceof Events) {
            toReturn += "E|";
        }
        if (task.isDone) {
            toReturn += "1|";
        } else {
            toReturn += "0|";
        }
        toReturn += task.description.trim();
        if (task instanceof Deadlines || task instanceof Events) {
            String[] durationArr = task.toString().split("[:)]");
            String duration = durationArr[1].split(" ")[1];
            toReturn += "|" + duration;
        }
        return toReturn;
    }

}
