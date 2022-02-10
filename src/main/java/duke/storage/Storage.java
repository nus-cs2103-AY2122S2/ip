package duke.storage;

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

import duke.data.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

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
                    Deadline newDeadline = new Deadline(input.get(2).trim(),
                            LocalDate.parse(input.get(3).trim(), formatter));
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
                default:
                    assert false : input.get(0);
                    throw new DukeException("Unknown type");
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            Ui ui = new Ui();
            if (file.getParentFile().mkdirs()) {
                try {
                    if (file.createNewFile()) {
                        ui.showFileCreated();
                    }
                } catch (IOException err) {
                    ui.showIoException();
                }
            }
        }
        return list;
    }

    public void saveTaskList(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : taskList.getTasks()) {
                writer.write(task.saveData());
                writer.write(System.lineSeparator());
            }
            writer.close();
            return;
        } catch (IOException e) {
            System.out.println("Error happened cannot save to file");
        }
        assert false;
    }
}
