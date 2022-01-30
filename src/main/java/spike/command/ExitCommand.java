package spike.command;

import java.io.FileWriter;
import java.io.IOException;

import spike.task.Task;
import spike.task.TaskList;

/**
 * Handles data before exiting.
 */
public class ExitCommand extends Command {
    /**
     * Save changes to list to the hard disk.
     *
     * @param tasks current task list
     * @return result of attempt to save changes
     */
    @Override
    public String execute(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter("data/Spike.txt");
            String latestList = "";
            for (Task task : tasks.getTasks()) {
                latestList = latestList + task.toFileFormat() + "\n";
            }
            fw.write(latestList);
            fw.close();
        } catch (IOException e) {
            return "Oops, something went wrong with saving your file :(";
        }
        return "See you soon! ﾍ(=￣∇￣)ﾉ";
    }
}
