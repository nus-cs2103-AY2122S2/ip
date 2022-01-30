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

import paggro.lister.Lister;
import paggro.exception.PaggroException;
import paggro.task.*;
import paggro.task.Deadline;
import paggro.notableDate.NotableDate;

public class Storage {
    File paggroData;
    String filePath;

    public Storage(String filePath) {
        paggroData = new File(filePath);
        this.filePath = filePath;
    }

    public Lister loadTasks() throws PaggroException {
        if (!paggroData.exists()) {
            try {
                new File(Paths.get("").toAbsolutePath() + "/data/").mkdir();
                paggroData.createNewFile();
            } catch (IOException e) {
                throw new PaggroException("    Could not create paggro.txt");
            }
            throw new PaggroException("    paggro.txt not found. Initializing new file... =.=");
        } else {
            Scanner sc;
            try {
                sc = new Scanner(paggroData);
            } catch (FileNotFoundException e) {
                throw new PaggroException("    Could not find paggro.txt");
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
                    details = taskString.split(" ");
                    isDone = Boolean.parseBoolean(details[2]);
                    des = details[4];
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
                    } else  {
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
                    } else  {
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
                    throw new PaggroException("    File format error!");
                }
            }
            return new Lister(tasks, dateMap);
        }
    }

    public void addToStorage(Task task) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(task.parseTask() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw e;
        }
    }

    public void deleteFromStorage(int i) throws IOException {
        File updated = new File("../../../data/updated_paggro.txt");
        Scanner sc = new Scanner(paggroData);
        updated.createNewFile();
        FileWriter fw = new FileWriter(updated);
        int j = 1;
        while (sc.hasNext()) {
            String currLine = sc.nextLine();
            if (j != i) {
                fw.write(currLine + System.lineSeparator());
            }
            j++;
        }
        fw.close();
        Files.delete(Paths.get(filePath));
        Files.copy(Paths.get("../../../data/updated_paggro.txt"), Paths.get(filePath));
        Files.delete(Paths.get("../../../data/updated_paggro.txt"));
    }

    public void markInStorage(int i, Task task) throws IOException {
        File updated = new File("../../../data/updated_paggro.txt");
        Scanner sc = new Scanner(paggroData);
        updated.createNewFile();
        FileWriter fw = new FileWriter(updated);
        int j = 1;
        while (sc.hasNext()) {
            String currLine = sc.nextLine();
            if (j != i) {
                fw.write(currLine + System.lineSeparator());
            } else {
                fw.write(task.parseTask() + System.lineSeparator());
            }
            j++;
        }
        fw.close();
        Files.delete(Paths.get(filePath));
        Files.copy(Paths.get("../../../data/updated_paggro.txt"), Paths.get(filePath));
        Files.delete(Paths.get("../../../data/updated_paggro.txt"));
    }

    public void unmarkInStorage(int i, Task task) throws IOException {
        File updated = new File("../../../data/updated_paggro.txt");
        Scanner sc = new Scanner(paggroData);
        updated.createNewFile();
        FileWriter fw = new FileWriter(updated);
        int j = 1;
        while (sc.hasNext()) {
            String currLine = sc.nextLine();
            if (j != i) {
                fw.write(currLine + System.lineSeparator());
            } else {
                fw.write(task.parseTask() + System.lineSeparator());
            }
            j++;
        }
        fw.close();
        Files.delete(Paths.get("../../../data/paggro.txt"));
        Files.copy(Paths.get("../../../data/updated_paggro.txt"), Paths.get("../../../data/paggro.txt"));
        Files.delete(Paths.get("../../../data/updated_paggro.txt"));
    }
}
