import java.io.IOException;

public class AddCommand extends Command {



    AddCommand(String directory, String filePath) {
        super(directory, filePath);
    }


    public void addCommand (String cmd,String des) throws IOException {
        if (cmd.equals("todo")) {
        taskList.toDo(des);
        } else if (cmd.equals("deadline")) {
        taskList.deadLine(des);
        } else if (cmd.equals("event")) {
        taskList.event(des);
        }

}
}
