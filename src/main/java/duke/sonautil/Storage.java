package duke.sonautil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


/**
 * Works on loading, changing and maintaining the contents in the hard disk file
 */
public class Storage {
    private final String path;

    public Storage(String path) {
        this.path = path;
    }

    /**
     * Converts the command into hard disk file for record.
     *
     * @param command details of command retrieved from Parser
     * @throws IOException if writeToFile/changeMarking fails
     */
    public void executeCommand(String[] command) throws IOException {

        String keyword = command[0];
        switch (keyword) {
        case "todo":
            writeToFile("T | 0 | " + command[1]);
            break;
        case "event":
            writeToFile("E | 0 | " + command[1] + " | " + command[2]);
            break;
        case "deadline":
            writeToFile("D | 0 | " + command[1] + " | " + command[2]);
            break;
        case "delete":
            deleteFromFile(Integer.parseInt(command[1]));
            break;
        case "mark":
            changeMarking("0", "1", Integer.parseInt(command[1]));
            break;
        case "unmark":
            changeMarking("1", "0", Integer.parseInt(command[1]));
            break;
        default:
        }
    }

    /**
     * deletes the Duke.task from local file by creating a temp file
     * and copy every Duke.task except the one to be deleted
     *
     * @param taskToDelete the index of Duke.task to delete
     */
    public void deleteFromFile(int taskToDelete) {
        String oldFileName = "src/main/data/duke.txt";
        String tempFileName = "src/main/data/tempDuke.txt";
        File tempFile = new File(tempFileName);

        int indexAccessed = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(oldFileName));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (indexAccessed != taskToDelete) {
                    if (first) {
                        bw.write(line);
                        first = false;
                    } else {
                        bw.write("\n" + line);
                    }
                }
                indexAccessed++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        File oldFile = new File(oldFileName);
        oldFile.delete();

        File newFile = new File(tempFileName);
        newFile.renameTo(oldFile);
    }

    /**
     * Appends the string representation Duke.task into the local file
     *
     * @param taskToAdd string to be added into the local file
     */
    public void writeToFile(String taskToAdd) throws IOException {
        FileWriter fw = new FileWriter(path, true);
        long line = Files.lines(Path.of(path)).count();
        if (line == 0) {
            fw.write(taskToAdd);
        } else {
            fw.write(System.lineSeparator() + taskToAdd);
        }
        fw.close();
    }

    /**
     * loads Duke.task from local file
     *
     * @return Arraylist of tasks
     * @throws IOException if create new file fails
     */
    public ArrayList<Task> load() throws IOException {
        try {
            return fileContentProcess();
        } catch (FileNotFoundException e) {
            createNewFile();
            return new ArrayList<>();
        }
    }

    /**
     * process the string in local file and converts to tasks in arraylist
     *
     * @return Arraylist of tasks
     * @throws FileNotFoundException if file is not found
     */
    public ArrayList<Task> fileContentProcess() throws FileNotFoundException {
        File f = new File(path);
        Scanner s = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>(100);
        while (s.hasNext()) {
            String task = s.nextLine();
            String[] taskDetail = task.split(" \\| ");

            switch (taskDetail[0]) {
            case "T":
                tasks.add(new Todo(taskDetail[2], Integer.parseInt(taskDetail[1])));
                break;

            case "D":
                tasks.add(new Deadline(taskDetail[2],
                        Integer.parseInt(taskDetail[1]), LocalDateTime.parse(taskDetail[3])));
                break;

            case "E":
                tasks.add(new Event(taskDetail[2],
                        Integer.parseInt(taskDetail[1]), LocalDateTime.parse(taskDetail[3])));
                break;

            default:
            }
        }
        return tasks;
    }

    /**
     * creates a new local file for Duke.Duke to work
     *
     * @throws IOException if fails to make new directory and create new file
     */
    public void createNewFile() throws IOException {
        File f = new File("src/main/data/duke.txt");
        f.getParentFile().mkdirs();
        f.createNewFile();
    }

    /**
     * Changes the marking of 1 single Duke.task in the local file
     *
     * @param init initial string of the marking ("1" for done, "0" for not done).
     * @param goal final string of the marking ("1" for mark, "0" for unmark).
     * @param taskToMark the index of Duke.task to be marked or unmarked
     * @throws IOException if fails access local file
     */
    private void changeMarking(String init, String goal, int taskToMark) throws IOException {
        String oldFileName = path;
        String tempFileName = "src/main/data/tempDuke.txt";
        File tempFile = new File(tempFileName);
        long lineCount = Files.lines(Path.of(path)).count();
        int taskToChange = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(oldFileName));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                if (taskToChange == lineCount - 1) { //if it is the last line
                    if (taskToChange == taskToMark) {
                        line = line.replaceFirst(init, goal);
                    }
                    bw.write(line);
                } else if (taskToChange == taskToMark) {
                    line = line.replaceFirst(init, goal);
                    bw.write(line + "\n");
                } else {
                    bw.write(line + "\n");
                }
                taskToChange++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        File oldFile = new File(oldFileName);
        oldFile.delete();

        File newFile = new File(tempFileName);
        newFile.renameTo(oldFile);
    }


}
