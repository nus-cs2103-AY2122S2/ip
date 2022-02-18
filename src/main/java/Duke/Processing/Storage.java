package Duke.Processing;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import Duke.Exception.DukeException;
import Duke.tasks.Event;
import Duke.tasks.Task;
import Duke.tasks.todo;
import Duke.tasks.Deadline;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Tasks in a file and loads up the tasks already present inside
     *
     * @param tasks current Tasklist to add new tasks to
     */

    public void load(TaskList tasks) throws DukeException {
        File previous = new File(this.filePath);
        System.out.println(previous.exists());
        if(previous.exists()) {
            try {
                File file  = new File(this.filePath);
                Scanner sc = new Scanner(file);
                while(sc.hasNextLine()) {
                    String task = sc.nextLine();
                    String[] different = task.split(" \\| ", 4);

                    Task item;
                    switch (different[0]) {
                        case "T":
                            item = new todo(different[2]);
                            break;
                        case "D":
                            item = new Deadline(different[2], Parser.convertDate2(different[3]));
                            break;
                        case "E":
                            item = new Event(different[2], Parser.convertDate2(different[3]));
                            break;
                        default:
                            throw new DukeException("Unknown type of mission");
                    }
                    if(different[1].equals("1")) {
                        item.isDone = true;
                    }
                    tasks.addTask(item);
                }
                sc.close();
            } catch (FileNotFoundException e) {
                throw new DukeException("No previous tasks, you sure you're alright?");
            }
        }   
    }
    /**
     * Writes to a file the new tasks after the end of the Duke session
     *
     * @param tasks the current Tasklist that contains the final state of all tasks
     */

    public void write(TaskList tasks) throws DukeException {
        try {
            File file = new File("Previously.txt");
            FileWriter fw = new FileWriter(file);
            BufferedWriter br = new BufferedWriter(fw);
            for (int i = 0; i < tasks.size(); i++) {
                br.write(tasks.get(i).toString());
                br.write("\n");
            }
            br.close();
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Can't add that");
        }
    }

}
