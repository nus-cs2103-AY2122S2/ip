import java.nio.file.*;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;


import java.io.IOException;
import java.util.Scanner;

public class Lister {
    ArrayList<Task> tasks;
    File data;

    public Lister(File data) {
        tasks = new ArrayList<>();
        this.data = data;
    }

    public Lister(ArrayList<Task> tasks, File data) {
        this.tasks = tasks;
        this.data = data;
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
        System.out.println("   ________________________________________");
        System.out.println("    Fine I'll add this task in:\n      " + task);
        if (tasks.size() == 1) {
            System.out.println("    Now you have 1 task in the list. =.=");
        } else {
            System.out.println("    Now you have " + tasks.size() + " tasks in the list. =.=");
        }
        System.out.println("   ________________________________________");
    }

    public void list() {
        System.out.println("   ________________________________________");
        if (tasks.size() == 0) {
            System.out.println("    Nothing to look at here... =.=");
        }
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
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
            String des = tasks.get(i - 1).toString();
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
}
