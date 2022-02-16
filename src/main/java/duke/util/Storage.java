package duke.util;

import duke.task.*;

import java.io.*;
import java.util.*;

public class Storage {

    protected String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public void createFile() {
        try {
            File file = new File(filepath);
            if (file.createNewFile()) {
                System.out.println("New File created\n");
            }
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void save(TaskList list) {
        try {
            FileWriter writer = new FileWriter(filepath);
            for (int i = 0; i < list.size(); i++) {
                Task tsk = list.get(i);
                writer.write(tsk.infoString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }


    public void load(TaskList list) {
        try {
            File file = new File(filepath);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                String[] details = task.split("/");

                Task constructed;

                if (details[0].equals("T")) {
                    constructed = new ToDo(details[2]);
                } else if (details[0].equals("D")) {
                    constructed = new Deadline(details[2], details[3]);
                } else {
                    constructed = new Event(details[2], details[3]);
                }

                if (details[1].equals("1")) {
                    constructed.mark();
                }

                list.add(constructed);
            }
            sc.close();
            System.out.println("File Loaded\n");
        } catch (FileNotFoundException e) {
            createFile();
        }
    }
}