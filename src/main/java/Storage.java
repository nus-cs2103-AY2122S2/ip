import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Storage {
    private static final String FILELOCATION = "data/version0/duke.txt";
    private File file;

    public Storage() {
        this.file = new File(FILELOCATION);
        if (!file.exists()) {
            try {
                File directory = new File(file.getParent());
                if (!directory.exists()) {  //data file not in specific folder, need to handle
                    directory.mkdirs();
                }
                file.createNewFile();
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    //insert java docs here. save tasks into file
    public void save(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(FILELOCATION);
        for (int i = 0; i < tasks.getSize(); i++) {
            fileWriter.write(tasks.get(i).getTaskData() + System.lineSeparator());
        }
        fileWriter.close();
    }

    public TaskList load() throws FileNotFoundException, IOException {
        TaskList tasks = new TaskList();
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNext()) {
                String task = sc.nextLine();
                char letter = task.charAt(0);
                char isMarked = task.charAt(4);
                boolean isDone = isMarked == ('1');
                String taskLine = task.substring(8);
                Task taskToAdd = null;
                switch (letter) {
                    case 'T':
                        taskToAdd = new Todo(taskLine, isDone);
                        break;
                    case 'D':
                        String[] theTask = taskLine.split(" \\| ", 2);
                        taskToAdd = new Deadline(theTask[0], theTask[1], isDone);
                        break;
                    case 'E':
                        String[] eventTasking = taskLine.split(" \\| ", 2);
                        taskToAdd = new Event(eventTasking[0], eventTasking[1], isDone);
                        break;
                }
                tasks.add(taskToAdd);
            }
        } catch (FileNotFoundException e) { //required file doesnt exist in their computer, create new file
            this.file.createNewFile();
        }
        return tasks;
    }
}
