package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class DukeStorage {

    private File filePath;

    public DukeStorage() {

    }

    public void startup(String directory, String fileName) throws IOException {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "Duke");
        String filePath = String.valueOf(path);

        // Check if parent and child directory exists else create them
        File f1 = new File(filePath + "\\" + directory);
        f1.mkdirs();

        // Check if duke.txt exits else create it
        File f2 = new File(filePath + "\\" + directory + "\\" + fileName);
        f2.createNewFile();

        this.filePath = f2;
    }

    public void restore(DukeHistory history) throws FileNotFoundException {
        Scanner s = new Scanner(this.filePath);
        while (s.hasNext()) {
            String temp = s.nextLine();
            String[] arr = temp.split(">");
            int mark = Integer.parseInt(arr[1]);
            switch (arr[0]) {
            case "T":
                history.addToDo(mark, arr[2]);
                break;
            case "D":
                history.addDeadline(mark, arr[2], arr[3], arr[4]);
                break;
            case "E":
                history.addEvent(mark, arr[2], arr[3], arr[4]);
                break;
            default:
                System.out.println("load unsuccessful");
                break;
            }
        }
    }

    void update(DukeHistory history) throws IOException {
        String content = history.formatRecord();
        FileWriter fw = new FileWriter(this.filePath);
        fw.append(content);
        fw.flush();
        fw.close();
    }

}
