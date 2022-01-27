import java.io.File;
import java.io.PrintWriter;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

class Storage {
    private final File file;

    public Storage(Path path) {
        this.file = path.toFile();
    }

    public List<Task> load() throws UltoiException {
        ArrayList<Task> tasks = new ArrayList<Task>();

        try {
            Scanner sc = new Scanner(this.file);

            while (sc.hasNextLine()) {
                String taskStr = sc.nextLine();
                tasks.add(((AddCommand) Parser.parse(taskStr)).getTask());
            }
        } catch (Exception e) {
            throw UltoiException.failToLoadFileException();
        }

        return tasks;
    }

    public void save(TaskList tasks) throws UltoiException {
        PrintWriter pw;

        try {
            pw = new PrintWriter(this.file);
        } catch (Exception e) {
            throw UltoiException.failToSaveFileException();
        }

        pw.println(tasks.toInputString());

        return;
    }
}
