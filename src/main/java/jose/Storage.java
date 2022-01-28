package jose;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import jose.task.*;

public class Storage {
    private File file;

    public Storage(String filePath) throws IOException {
        file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    public ArrayList<String> load() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<String> tasks = new ArrayList<>();
        String s;

        while ((s = br.readLine()) != null) {
            tasks.add(s);
        }

        br.close();
        return tasks;
    }

    public void update(TaskList tasks) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (Task t : tasks.getTasks()) {
            bw.write(t.formatData());
            bw.newLine();
        }
        bw.close();
    }
}
