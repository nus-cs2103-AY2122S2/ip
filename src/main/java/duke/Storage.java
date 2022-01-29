package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Storage {

    protected File dataFile;

    public Storage(String filePath) {
        dataFile = new File(filePath);

        // dir/file check should not throw unless no r/w permission
        dataFile.getParentFile().mkdirs();
        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> read() throws DukeException {
        List<String> dataList;

        // try block should populate dataList
        try (Scanner sc = new Scanner(dataFile)) {
            dataList = sc.useDelimiter("\\n")
                    .tokens()
                    .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            throw new DukeException("Unable to locate/read data file.");
        }

        // if dataList is empty, dataFile has no entry
        return dataList;
    }

    public boolean write(TaskList tasks) throws DukeException {
        try (FileWriter fw = new FileWriter(dataFile, false)) {
            for (int i = 0; i < tasks.size(); i++) {
                try {
                    fw.write(tasks.get(i).toFile());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return true;
        } catch (IOException e) {
            throw new DukeException("Unable to locate/write to data file.");
        }
    }
}