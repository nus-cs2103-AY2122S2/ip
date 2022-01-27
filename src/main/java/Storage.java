import java.io.File;
import java.io.IOException;

public class Storage {
    protected final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public File load() throws RonException {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new LoadException();
        }
        return file;
    }
}
