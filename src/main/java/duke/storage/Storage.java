package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

/**
 * Class which handles saving and loading of data from text
 */
public class Storage {
    private final String filePath;
    private final TaskList tasks = new TaskList();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates the required directory and file if it does not exist
     * if not, loads the correct task data stored in the text file and returns the tasklist
     *
     * @return Tasklist loaded from the text file
     * @throws IOException error from loading file
     */
    public TaskList load() throws IOException {
        //load files
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        } else {
            Scanner s = new Scanner(file);
            String type;
            String mark;
            String detail;
            String date;
            Task toAdd = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime parsedDate;
            while (s.hasNext()) {
                String input = s.nextLine();
                String[] inputSplit = input.split(" \\| ", 3);
                type = inputSplit[0];
                mark = inputSplit[1];
                switch (type) {
                case "T":
                    detail = inputSplit[2];
                    toAdd = new ToDo(detail);
                    break;
                case "E":
                    inputSplit = inputSplit[2].split(" \\| ");
                    detail = inputSplit[0];
                    date = inputSplit[1];
                    parsedDate = LocalDateTime.parse(date, formatter);
                    toAdd = new Event(detail, parsedDate);
                    break;
                case "D":
                    inputSplit = inputSplit[2].split(" \\| ");
                    detail = inputSplit[0];
                    date = inputSplit[1];
                    parsedDate = LocalDateTime.parse(date, formatter);
                    toAdd = new Deadline(detail, parsedDate);
                    break;
                default:
                    break;
                }
                System.out.println(mark);
                if (mark.equals("1")) {
                    toAdd.mark();
                }
                tasks.add(toAdd);
            }
        }
        return tasks;
    }

    /**
     * Saves the input task list into a text file to be loaded in future runs
     *
     * @param tasks list of tasks to be saved
     * @throws IOException
     */
    public void saveFile(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter("data/tasks.txt");
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                String type = t.getType();
                String mark = t.getMark();
                String detail = t.getDetail();
                switch (type) {
                case "T":
                    fw.write(type + " | " + mark + " | " + detail + "\n");
                    break;
                case "E":
                case "D":
                    String date = t.getDate();
                    fw.write(type + " | " + mark + " | " + detail + " | " + date + "\n");
                    break;
                default:
                    break;
                }
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
