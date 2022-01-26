package narcibot;
import java.io.*;

public class Storage {
    private File file;
    private File path;
    Storage(String fileName, String path) {
        this.path = new File(path);
        file = new File(path + "/" + fileName);
    }

    public BufferedReader load() throws FileNotFoundException {
        return new BufferedReader((new FileReader(file)));
    }

    public FileWriter store() throws IOException {
        if (!path.exists()) {
            path.mkdir();
        }
        return new FileWriter(file);
    }
}
