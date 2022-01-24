import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String dataFilePath;
    private String dataFolderPath;
    private File dataFile;


    public Storage(String path) {
        this.dataFilePath = path;
        String[] rPath = path.split("/");
        StringBuilder s = new StringBuilder("./");
        for (int i = 0; i < rPath.length  - 1;i++) {
            s.append(rPath[i]);
        }
        this.dataFolderPath = s.toString();
    }

    private Task manageFileData(String task) {
        // We assume the task stored inside is correct
        String[] splitTask = task.split("\\|");
        for (int i = 0; i < splitTask.length;i++) {
            splitTask[i] = splitTask[i].trim();
        }
        if (splitTask[0].equals("T")) {
            if (splitTask[1].equals("1")) {
                return new Todo(splitTask[2], true);
            }
            return new Todo(splitTask[2], false);
        } else if (splitTask[0].equals("D")) {
            return new Deadline(splitTask[2], LocalDate.parse(splitTask[3], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    LocalTime.parse(splitTask[4], DateTimeFormatter.ofPattern("HH:mm")), splitTask[1].equals("1"));
        } else {
            return new Event(splitTask[2], LocalDate.parse(splitTask[3], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    LocalTime.parse(splitTask[4], DateTimeFormatter.ofPattern("HH:mm")),
                    LocalTime.parse(splitTask[5], DateTimeFormatter.ofPattern("HH:mm")), splitTask[1].equals("1"));
        }
    }

    public ArrayList<Task> load() throws IOException, ChiException {
        try {
            ArrayList<Task> loadedTasks = new ArrayList<>();
            this.dataFile = new File(dataFilePath);
            Scanner s = new Scanner(dataFile);
            while (s.hasNext()) {
                String task = s.nextLine();
                Task t = manageFileData(task);
                loadedTasks.add(t);
            }
            return loadedTasks;
        } catch (FileNotFoundException e) {
            File dataFolder = new File(dataFolderPath);
            if (!dataFolder.isDirectory()) {
                dataFolder.mkdir();
            }
            File newDataFile = new File(dataFilePath);
            newDataFile.createNewFile();
            throw new ChiException("File not found, restart program nyan!");
        }

    }

    public void store(List<Task> savedTasks) throws IOException {
        FileWriter fw = new FileWriter(dataFilePath);
        for (Task t: savedTasks) {
            String des = t.writeToFile() + "\n";
            fw.write(des);
        }
        fw.close();
    }

}
