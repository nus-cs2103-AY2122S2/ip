import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StorageFile {

    public static final String DEFAULT_PATH = "data/luke.txt";
    private final File file;

    StorageFile(String filePath) throws IOException {
        this.file = new File(filePath);
        if (this.file.getParentFile() != null && !this.file.getParentFile().exists()) {
            this.file.getParentFile().mkdirs();
        }

        if (!this.file.exists()) {
            this.file.createNewFile();
        }

        if (!this.file.canRead() && !this.file.canWrite()) {
            throw new IOException("Unable to read or write to file.");
        }
    }

    public void save(Storable storable) throws IOException {
        FileWriter fw = new FileWriter(file);
        for (String str : storable.getData()) {
            fw.write(str + System.lineSeparator());
        }
        fw.close();
    }

    public List<String> load() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        List<String> list = new ArrayList<>();
        while (sc.hasNextLine()) {
            list.add(sc.nextLine());
        }
        return list;
    }
}
