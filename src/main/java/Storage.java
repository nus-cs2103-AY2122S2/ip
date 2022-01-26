import java.io.File;
import java.io.IOException;
import java.util.List;
import java.io.FileWriter;
import java.util.Scanner;

public class Storage {

    File file;

    Storage(String filePath) throws DukeException {
        this.file = new File(filePath);

    }

    public void loadFile(List<Task> tasks) throws IOException, DukeException {
        if (!file.exists()) {
            throw new DukeException("Please create a file under directory data");
        } else {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String s = sc.nextLine();
                Task t = parseTextFile(s);
                tasks.add(t);
            }
        }
    }

    private static Task parseTextFile(String s) {
        Task t;
        String[] strarr = s.split(" ");
        if (s.contains("[X]")) {
            if (s.contains("[T]")) {
                t = new Todo(strarr[1]);
            } else if (s.contains("[E]")){
                t = new Event(strarr[1], strarr[2] + " " + strarr[3]);
            } else {
                t = new Deadline(strarr[1], strarr[2] + " " + strarr[3]);
            }
            t.markAsDone();
        } else {
            if (s.contains("[T]")) {
                t = new Todo(strarr[2]);
            } else if (s.contains("[E]")){
                t = new Event(strarr[2], strarr[3] + " " + strarr[4]);
            } else {
                t = new Deadline(strarr[2], strarr[3] + " " + strarr[4]);
            }

        }
        return t;
    }

    public void writeToFile(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(this.file);
        String s = "";
        for (Task t: tasks) {
            s += t.storageString() + "\n";
        }


        fw.write(s);
        fw.close();
    }
}