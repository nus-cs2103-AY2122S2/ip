import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Store {

    public static final String PATH = String.join(File.separator,"data","duke.txt");
    private File file;

    public Store() {
        this.file = new File(PATH);
        if (!this.file.getParentFile().exists())  {
            this.file.getParentFile().mkdir();
        }
    }

    public void writeToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(PATH);
        StringBuffer result = new StringBuffer();
        for (Task task : tasks.getTaskList()) {
            result.append(task.formatToSave()).append("\n");
        }
        fw.write(result.toString());
        fw.close();
    }

    public TaskList importTasks() throws IOException {
        TaskList taskList = new TaskList();
        try {
            Scanner s = new Scanner(this.file);
            while (s.hasNext()) {
                String[] inputLine = s.nextLine().split("\\|");
                String type = inputLine[0];
                boolean isDone = inputLine[1].equals("1");
                switch (type){
                    case "T":
                        taskList.addToDo(inputLine[2],isDone);
                        break;
                    case "E":
                        taskList.addEvent(inputLine[2],isDone,inputLine[3]);
                        break;
                    case "D":
                        taskList.addDeadline(inputLine[2],isDone,inputLine[3]);
                }
            }
        } catch (FileNotFoundException e) {
            this.file.createNewFile();
        } catch (DukeException e) {
            new ResponePrinter(e.getMessage()).print();
        }
        return taskList;
    }
}
