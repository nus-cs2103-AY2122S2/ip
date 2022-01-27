package com.duke.util;

import com.duke.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A storage that can save and load the record of user's todo list.
 */
public class Storage {

    private final String path;

    public Storage(String path) {
        this.path = path;
    }

    /**
     * Load the record of user's task list
     * @return List of Task
     */
    public ArrayList<Task> load() {
        File f = new File(path);
        ArrayList<Task> arr = new ArrayList<>();
        try {
            if(!f.createNewFile()) {
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    String[] list = s.nextLine().split(" - ");
                    Task ts;
                    if ("T".equals(list[0])) {
                        ts = new Todo(list[2]);
                    } else if ("D".equals(list[0])) {
                        ts = new Deadline(list[2], list[3]);
                    } else {
                        ts = new Event(list[2], list[3]);
                    }
                    if (list[1].equals("1")) {
                        ts.markAsDone();
                    }
                    arr.add(ts);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }

    /**
     * Save the user's task list into a file.
     * @param arr List of Task
     */
    public void save(TaskList arr) {
        try {
            FileWriter fw = new FileWriter(path);
            StringBuilder text = new StringBuilder();
            for (int i=0; i<arr.getCount(); i++) {
                if (arr.get(i) instanceof Todo) {
                    if (arr.get(i).getIsDone()) {
                        text.append("T").append(" - 1 - ").append(arr.get(i).getDescription()).append("\n");
                    } else {
                        text.append("T").append(" - 0 - ").append(arr.get(i).getDescription()).append("\n");
                    }
                } else if (arr.get(i) instanceof Deadline) {
                    if (arr.get(i).getIsDone()) {
                        text.append("D").append(" - 1 - ").append(arr.get(i).getDescription()).append(" - ")
                                .append(((Deadline) arr.get(i)).getDate()).append("\n");
                    } else {
                        text.append("D").append(" - 0 - ").append(arr.get(i).getDescription()).append(" - ")
                                .append(((Deadline) arr.get(i)).getDate()).append("\n");
                    }
                } else {
                    if (arr.get(i).getIsDone()) {
                        text.append("E").append(" - 1 - ").append(arr.get(i).getDescription()).append(" - ")
                                .append(((Event) arr.get(i)).getDate()).append("\n");
                    } else {
                        text.append("E").append(" - 0 - ").append(arr.get(i).getDescription()).append(" - ")
                                .append(((Event) arr.get(i)).getDate()).append("\n");
                    }
                }
            }
            fw.write(text.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
