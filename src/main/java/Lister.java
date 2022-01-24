import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

import java.time.LocalDate;

import java.nio.file.Paths;
import java.nio.file.Files;

public class Lister {
    ArrayList<Task> tasks;
    HashMap<LocalDate, NotableDate> dateMap;
    File data;

    public Lister(File data) {
        tasks = new ArrayList<>();
        this.data = data;
        dateMap = new HashMap<>();
    }

    public Lister(ArrayList<Task> tasks, HashMap<LocalDate, NotableDate> dateMap, File data) {
        this.tasks = tasks;
        this.data = data;
        this.dateMap = dateMap;
    }

    public void add(Task task) throws IOException {
        try {
            FileWriter fw = new FileWriter("../../../data/paggro.txt", true);
            fw.write(task.parseTask() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw e;
        }
        tasks.add(task);
        if (task instanceof Event) {
            Event e = (Event) task;
            e.date.addTask(task);
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            d.date.addTask(task);
        }
        System.out.println("   ________________________________________");
        System.out.println("    Fine I'll add this task in:\n      " + task);
        if (tasks.size() == 1) {
            System.out.println("    Now you have 1 task in the list. =.=");
        } else {
            System.out.println("    Now you have " + tasks.size() + " tasks in the list. =.=");
        }
        System.out.println("   ________________________________________");
    }

    public void list(ArrayList<Task> taskList) {
        System.out.println("   ________________________________________");
        if (taskList.size() == 0) {
            System.out.println("    Nothing to look at here... =.=");
        }
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.isDone) {
                System.out.println("    " + Integer.toString(i + 1) + "." + task);
            } else {
                System.out.println("    " + Integer.toString(i + 1) + "." + task);
            }
        }
        System.out.println("   ________________________________________");
    }

    public void mark(int i) throws IOException {
        Task task = tasks.get(i - 1);
        System.out.println("   ________________________________________");
        if (i < 0 || i > tasks.size()) {
            System.out.println("    Invalid entry number entered! =.=");
        } else {
            task.setDone();
            System.out.println("    You've finished this task. Good for you... =.=\n      " + task);
        }
        System.out.println("   ________________________________________");

        File updated = new File("../../../data/updated_paggro.txt");
        try {
            Scanner sc = new Scanner(data);
            updated.createNewFile();
            FileWriter fw = new FileWriter(updated);
            int j = 1;
            while (sc.hasNext()) {
                String currLine = sc.nextLine();
                if (j != i) {
                    fw.write(currLine + System.lineSeparator());
                } else {
                    fw.write(task.parseTask());
                }
                j++;
            }
            fw.close();
            Files.delete(Paths.get("../../../data/paggro.txt"));
            Files.copy(Paths.get("../../../data/updated_paggro.txt"), Paths.get("../../../data/paggro.txt"));
            Files.delete(Paths.get("../../../data/updated_paggro.txt"));
        } catch (IOException e) {
            throw e;
        }
    }

    public void unmark(int i) throws IOException {
        Task task = tasks.get(i - 1);
        System.out.println("   ________________________________________");
        if (i < 0 || i > tasks.size()) {
            System.out.println("    Invalid entry number entered! =.=");
        } else {
            task.setUndone();
            System.out.println("    Marked undone. Stop slacking off... =.=\n      " + task);
        }
        System.out.println("   ________________________________________");

        File updated = new File("../../../data/updated_paggro.txt");
        try {
            Scanner sc = new Scanner(data);
            updated.createNewFile();
            FileWriter fw = new FileWriter(updated);
            int j = 1;
            while (sc.hasNext()) {
                String currLine = sc.nextLine();
                if (j != i) {
                    fw.write(currLine + System.lineSeparator());
                } else {
                    fw.write(task.parseTask());
                }
                j++;
            }
            fw.close();
            Files.delete(Paths.get("../../../data/paggro.txt"));
            Files.copy(Paths.get("../../../data/updated_paggro.txt"), Paths.get("../../../data/paggro.txt"));
            Files.delete(Paths.get("../../../data/updated_paggro.txt"));
        } catch (IOException e) {
            throw e;
        }
    }

    public void delete(int i) throws IOException {
        File updated = new File("../../../data/updated_paggro.txt");
        try {
            Scanner sc = new Scanner(data);
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
            Files.delete(Paths.get("../../../data/paggro.txt"));
            Files.copy(Paths.get("../../../data/updated_paggro.txt"), Paths.get("../../../data/paggro.txt"));
            Files.delete(Paths.get("../../../data/updated_paggro.txt"));
        } catch (IOException e) {
            throw e;
        }

        System.out.println("   ________________________________________");
        if (i < 0 || i > tasks.size()) {
            System.out.println("    Invalid entry number entered! =.=");
        } else {
            Task task = tasks.get(i - 1);
            if (task instanceof Event) { // remove task from NotableDate tasklist
                Event e = (Event) task;
                e.date.tasks.remove(task);
            } else if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                d.date.tasks.remove(task);
            }
            String des = task.toString();
            tasks.remove(i - 1);
            System.out.println("    Fine. I've removed this task:\n      " + des);
            if (tasks.size() == 1) {
                System.out.println("    Now you have 1 task in the list. =.=");
            } else {
                System.out.println("    Now you have " + tasks.size() + " tasks in the list. =.=");
            }
        }
        System.out.println("   ________________________________________");
    }

    public NotableDate checkDate(LocalDate lDate) {
        NotableDate nDate;
        if (!dateMap.containsKey(lDate)) { // checks if NotableDate has already been initialised
            nDate = new NotableDate(lDate);
            dateMap.put(lDate, nDate);
        } else  {
            nDate = dateMap.get(lDate);
        }
        return nDate;
    }
}
