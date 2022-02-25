package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class concerning storage of tasks
 */
public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks recorded on data file to task list
     * @return list of tasks
     * @throws DukeException when an error occurs during loading
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            File folder = new File("data");
            File file = new File("data/tasks.txt");
            if(!folder.exists()) {
                folder.mkdir();
                if(!file.exists()) {
                    file.createNewFile();
                }
            }
            Scanner scanner = new Scanner(file);
            boolean doneOrNot = false;
            ArrayList<Task> tasks = new ArrayList<>();
            while (scanner.hasNext()) {
                String fullCommand = scanner.nextLine();
                String[] arrOfStr = fullCommand.split(" ", 5);
                String command = arrOfStr[0];
                if (Integer.parseInt(arrOfStr[2]) == 1) {
                    doneOrNot = true;
                }
                switch (command) {
                    case "D" : {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
                        // split description and date/time
                        String[] arrOfStr2 = arrOfStr[4].split("\\|", 2);
                        // split within date and time
                        String[] arrOfStr3 = arrOfStr2[1].split(" ", 5);
                        assert(arrOfStr3[0].equals("")):"Error occurred in parsing";
                        // extracting date from data received
                        LocalDate by = LocalDate.parse(arrOfStr3[1] + " "
                                + arrOfStr3[2] + " " + arrOfStr3[3], formatter);
                        tasks.add(new Deadline(arrOfStr2[0], by, arrOfStr3[4], doneOrNot));
                        break;
                    }
                    case "E" : {
                        String[] arrOfStr2 = arrOfStr[4].split("\\|", 2);
                        String at = arrOfStr2[1];
                        tasks.add(new Event(arrOfStr2[0], at, doneOrNot));
                        break;
                    }
                    case "T" : {
                        tasks.add(new Todo(arrOfStr[4], doneOrNot));
                        break;
                    }
                    default : {
                        break;
                    }
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Saves list of tasks to the system
     * @param tasks list of tasks
     * @throws DukeException when error occurs during saving of tasks
     */
    public void save(TaskList tasks) throws DukeException {
        String toWrite = "";
        for (int i = 0; i < tasks.getSize(); i++){
            toWrite = toWrite + tasks.get(i).toSave();
        }
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(toWrite);
            fw.close();
        } catch(IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}

