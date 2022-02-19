package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 *
 * @author sibinhho99-nus
 */
public class Storage {
    private TaskList taskList = new TaskList();

    /**
     * Returns the current task list.
     * @return the current task list.
     */
    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Loads the task list from storage.
     */
    public void load() throws IOException {
        FileReader fr = new FileReader("storage.txt");
        BufferedReader bufferedReader = new BufferedReader(fr);

        String line;

        while ((line = bufferedReader.readLine()) != null) {

            if (line.split("\\.")[1].charAt(1) == 'D') {
                boolean isDone = line.charAt(6) == 'X';
                String name = line.split(" \\(by: ")[0].substring(9);

                String doneBy;
                doneBy = line.split(" \\(by: ")[1];
                doneBy = doneBy.substring(0, doneBy.length() - 1); // removes end bracket

                taskList.add(new DeadlineTask(isDone, name, doneBy));
            } else if (line.split("\\.")[1].charAt(1) == 'E') {
                boolean isDone = line.charAt(6) == 'X';

                String name = line.split(" \\(at: ")[0].substring(9);

                String startAt;
                startAt = line.split(" \\(at: ")[1];
                startAt = startAt.substring(0, startAt.length() - 1); // removes end bracket

                taskList.add(new EventTask(isDone, name, startAt));
            } else { // T
                boolean isDone = line.charAt(6) == 'X';
                taskList.add(new TodoTask(isDone, line.substring(9)));
            }
        }
    }

    /**
     * Saves the current task list to storage.
     */
    public void save() throws IOException {
        FileWriter fw = new FileWriter("storage.txt", false);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(this.toString());
        bw.close();
    }

    /**
     * Returns String representation of the duke.TaskList.
     */
    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            list.append(String.format("%d.%s\n", i + 1, taskList.get(i)));
        }

        return list.toString().trim();
    }
}
