import java.io.IOException;

public class DeleteCommand extends Command{
    DeleteCommand(String directory, String filePath) {
        super(directory, filePath);
    }


   public void deleteCommand (String des) throws IOException {
        taskList.delete(des);
    }
}
