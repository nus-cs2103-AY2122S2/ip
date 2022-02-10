
package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;




public class Storage {

    private final File file;

    /**
     * @param filePath String of path to file
     */
    public Storage(String filePath) {
        this.file = new File(filePath);

    }

    /**
     * read the text file and converts the content into an ArrayList of Tasks
     * @param tasks ArrayList of tasks
     */

    public void loadFile(List<Task> tasks) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            Task t = parseTextFile(s);
            tasks.add(t);
        }
    }

    private static Task parseTextFile(String s) {
        Task t;
        String[] strarr = s.split(" ");
        if (s.contains("[X]")) {
            if (s.contains("[T]")) {
                t = new Todo(strarr[1]);
            } else if (s.contains("[E]")) {
                t = new Event(strarr[1], strarr[2] + " " + strarr[3]);
            } else {
                t = new Deadline(strarr[1], strarr[2] + " " + strarr[3]);
            }
            t.markAsDone();
        } else {
            if (s.contains("[T]")) {
                t = new Todo(strarr[2]);
            } else if (s.contains("[E]")) {
                t = new Event(strarr[2], strarr[3] + " " + strarr[4]);
            } else {
                t = new Deadline(strarr[2], strarr[3] + " " + strarr[4]);
            }

        }
        return t;
    }

    /**
     * writes the tasks into text file before ending the program
     * @param tasks ArrayList of tasks
     */

    public void writeToFile(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(this.file);
        StringBuilder s = new StringBuilder();
        for (Task t: tasks) {
            s.append(t.storageString()).append("\n");
        }


        fw.write(s.toString());
        fw.close();
    }
}
