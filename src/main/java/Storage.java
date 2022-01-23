import java.io.File;

public class Storage {

    private final File file;

    public Storage(String directoryPath, String fileName) {
        File dir = new File(directoryPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File f = new File(dir, fileName);
        try {
            f.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.file = f;
        }
    }

    public void saveChanges(TaskList taskList) throws ChatBotException {
        taskList.save(file);
    }

    public void loadData(TaskList taskList) throws ChatBotException {
        taskList.load(file);
    }
}
