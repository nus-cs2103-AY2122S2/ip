package paggro.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import paggro.exception.PaggroException;
import paggro.lister.Lister;
import paggro.notabledate.NotableDate;
import paggro.task.Deadline;
import paggro.task.Event;
import paggro.task.Task;
import paggro.task.ToDo;

/**
 * This class encapsulates a Storage object that stores previously input tasks.
 */
public class Storage {
    private static final String FOUR_SPACE = "    ";

    /**
     * The file in which the task data is stored.
     */
    private File paggroData;
    /**
     * The path in which the file is stored
     */
    private String filePath;

    /**
     * Constructor of Storage.
     *
     * @param filePath The path of the file to be used for storage.
     */
    public Storage(String filePath) {
        paggroData = new File(filePath);
        this.filePath = filePath;
    }

    /**
     * Loads the list of stored tasks into the Lister object.
     *
     * @return The Lister object with uploaded tasks from storage.
     * @throws PaggroException
     */
    public Lister loadTasks() throws PaggroException {
        if (!paggroData.exists()) {
            try {
                new File(Paths.get("").toAbsolutePath() + "/data/").mkdir();
                paggroData.createNewFile();
            } catch (IOException e) {
                throw new PaggroException(FOUR_SPACE + "Could not create paggro.txt");
            }
            final String missingDataFile = "paggro.txt not found. Initializing new file... =.=";
            throw new PaggroException(FOUR_SPACE + missingDataFile);
        } else {
            Scanner sc;
            try {
                sc = new Scanner(paggroData);
            } catch (FileNotFoundException e) {
                throw new PaggroException(FOUR_SPACE + "Could not find paggro.txt");
            }
            ArrayList<Task> tasks = new ArrayList<>();
            HashMap<LocalDate, NotableDate> dateMap = new HashMap<>();
            while (sc.hasNextLine()) {
                String taskString = sc.nextLine();
                char type = taskString.charAt(0);
                String[] details;
                boolean isDone;
                String des;
                LocalDate lDate;
                LocalTime lTime;
                NotableDate nDate;
                Task t;
                switch (type) {
                    case 'T':
                        details = taskString.split(" \\| ", 3);
                        isDone = Boolean.parseBoolean(details[1]);
                        des = details[2];
                        tasks.add(new ToDo(des, isDone));
                        break;
                    case 'E':
                        details = taskString.split(" \\| ");
                        isDone = Boolean.parseBoolean(details[1]);
                        des = details[2];
                        lDate = LocalDate.parse(details[3]);
                        if (!dateMap.containsKey(lDate)) { // checks if NotableDate has already been initialised
                            nDate = new NotableDate(lDate);
                            dateMap.put(lDate, nDate);
                        } else {
                            nDate = dateMap.get(lDate);
                        }
                        if (details.length > 4) {
                            lTime = LocalTime.parse(details[4]);
                            t = new Event(des, nDate, lTime, isDone);
                        } else {
                            t = new Event(des, nDate, isDone);
                        }
                        tasks.add(t);
                        nDate.addTask(t);
                        break;
                    case 'D':
                        details = taskString.split(" \\| ");
                        isDone = Boolean.parseBoolean(details[1]);
                        des = details[2];
                        lDate = LocalDate.parse(details[3]);
                        if (!dateMap.containsKey(lDate)) { // checks if NotableDate has already been initialised
                            nDate = new NotableDate(lDate);
                            dateMap.put(lDate, nDate);
                        } else {
                            nDate = dateMap.get(lDate);
                        }
                        if (details.length > 4) {
                            lTime = LocalTime.parse(details[4]);
                            t = new Deadline(des, nDate, lTime, isDone);
                        } else {
                            t = new Deadline(des, nDate, isDone);
                        }
                        tasks.add(t);
                        nDate.addTask(t);
                        break;
                    default:
                        throw new PaggroException(FOUR_SPACE + "File format error!");
                }
            }
            return new Lister(tasks, dateMap);
        }
    }

    /**
     * Adds a given task to the data file to be stored.
     *
     * @param task Task to be stored.
     * @throws IOException
     */
    public void addToStorage(Task task) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(task.parseTask() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Removes a tasks at a given line of the file from the storage.
     *
     * @param lineNum The line number containing the task to be removed.
     * @throws IOException
     */
    public void deleteFromStorage(int lineNum) throws IOException {
        final String updatedFilePath = "./data/updated_paggro.txt";
        File updatedFile = new File(updatedFilePath);
        Scanner sc = new Scanner(paggroData);
        updatedFile.createNewFile();
        FileWriter fw = new FileWriter(updatedFile);
        int j = 1;
        while (sc.hasNext()) {
            String currLine = sc.nextLine();
            if (j != lineNum) {
                fw.write(currLine + System.lineSeparator());
            }
            j++;
        }
        fw.close();
        Files.delete(Paths.get(filePath));
        Files.copy(Paths.get(updatedFilePath), Paths.get(filePath));
        Files.delete(Paths.get(updatedFilePath));
    }

    /**
     * Marks the task at a given line of the file as done in storage.
     *
     * @param lineNum The line number containing the task to be marked.
     * @param task    The task to be marked as done.
     * @throws IOException
     */
    public void markInStorage(int lineNum, Task task) throws IOException {
        final String updatedFilePath = "./data/updated_paggro.txt";
        File updatedFile = new File(updatedFilePath);
        Scanner sc = new Scanner(paggroData);
        updatedFile.createNewFile();
        FileWriter fw = new FileWriter(updatedFile);
        int j = 1;
        while (sc.hasNext()) {
            String currLine = sc.nextLine();
            if (j != lineNum) {
                fw.write(currLine + System.lineSeparator());
            } else {
                fw.write(task.parseTask() + System.lineSeparator());
            }
            j++;
        }
        fw.close();
        Files.delete(Paths.get(filePath));
        Files.copy(Paths.get(updatedFilePath), Paths.get(filePath));
        Files.delete(Paths.get(updatedFilePath));
    }

    /**
     * Unmarks the task at a given line of the file as not done in storage.
     *
     * @param lineNum The line number containing the task to be unmarked.
     * @param task    The task to be unmarked as done.
     * @throws IOException
     */
    public void unmarkInStorage(int lineNum, Task task) throws IOException {
        File updatedFile = new File("./data/updated_paggro.txt");
        Scanner sc = new Scanner(paggroData);
        updatedFile.createNewFile();
        FileWriter fw = new FileWriter(updatedFile);
        int j = 1;
        while (sc.hasNext()) {
            String currLine = sc.nextLine();
            if (j != lineNum) {
                fw.write(currLine + System.lineSeparator());
            } else {
                fw.write(task.parseTask() + System.lineSeparator());
            }
            j++;
        }
        fw.close();
        Files.delete(Paths.get(filePath));
        Files.copy(Paths.get("./data/updated_paggro.txt"), Paths.get(filePath));
        Files.delete(Paths.get("./data/updated_paggro.txt"));
    }
}
