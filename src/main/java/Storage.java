import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Storage {
    protected String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(filePath);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                List<String> input = Arrays.asList(sc.nextLine().split(" \\| "));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                switch (input.get(0)) {
                    case "T":
                        ToDo newTodo = new ToDo(input.get(2).trim());
                        list.add(newTodo);
                        if (input.get(1).equals("1")) {
                            list.set(list.indexOf(newTodo), newTodo.mark());
                        }
                        break;
                    case "D":
                        Deadline newDeadline = new Deadline(input.get(2).trim(), LocalDate.parse(input.get(3).trim(), formatter));
                        list.add(newDeadline);
                        if (input.get(1).equals("1")) {
                            list.set(list.indexOf(newDeadline), newDeadline.mark());
                        }
                        break;
                    case "E":
                        Event newEvent = new Event(input.get(2).trim(), LocalDate.parse(input.get(3).trim(), formatter));
                        list.add(newEvent);
                        if (input.get(1).equals("1")) {
                            list.set(list.indexOf(newEvent), newEvent.mark());
                        }
                        break;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            Ui ui = new Ui();
            if (file.getParentFile().mkdirs()){
                try {
                    if (file.createNewFile()) {
                        ui.showFileCreated();
                    }
                } catch (IOException err) {
                    ui.showIOException();
                }
            }
        }
        return list;
    }

    public void saveTaskList(TaskList taskList) {
        String FILE_PATH = "data/TaskList.txt";
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : taskList.tasks) {
                writer.write(task.saveData());
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error happened cannot save to file");
        }
    }
}
