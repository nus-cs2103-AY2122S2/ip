package istjbot.storage;

import istjbot.exception.BotException;
import istjbot.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private final File file;

    public Storage(String filePath) throws BotException {
        this.file = new File(filePath);
        File directory = new File(this.file.getParentFile().getAbsolutePath());
        directory.mkdirs();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new BotException(e.getMessage());
            }
        }
    }

    public File load() {
        return this.file;
    }

    // Assumption: IstjBox.txt is not tampered with by a user
    public void save(TaskList tasks) throws BotException {
        try {
            FileWriter fw = new FileWriter(this.file);
            fw.write(tasks.tasksToTxtString());
            fw.close();

        } catch (IOException e) {
            throw new BotException(e.getMessage());
        }
    }
}



