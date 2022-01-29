package duke;

import duke.file_management.CreateFile;
import duke.file_management.WriteFile;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Storage {
    private TaskList taskList;
    private Parser parser;

    public Storage(TaskList taskList) {
        this.parser = new Parser();
        this.taskList = taskList;
        CreateFile file = new CreateFile();
        boolean isCreated = file.createFile();
        if (!isCreated) {
            load(file.getFileName());
        }
    }

    public void save() {

        writeTasksToFile();
        System.out.println("Your Tasks has been saved into your device!");
    }

    public void writeTasksToFile() {
        WriteFile writeFile = new WriteFile();
        writeFile.clearFile();
        int leng = taskList.getTasks().toArray().length;
        for (int i = 0; i < leng; i++) {
            Task task = taskList.getTasks().get(i);
            int num = i + 1;
            writeFile.writeToFile(num + ": " + task.toString() + System.lineSeparator());
        }
    }

    public void load(String fileName) {
        String command = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while((command = reader.readLine()) != null){
                // 9: [E][ ] something  (at: Sep 11 2011, 12:12)
                String[] commandArr = command.split("]");
                String cmdTemp = commandArr[0];
                String firstWord = cmdTemp.substring(cmdTemp.length() - 1);
                String taskName = command.substring(10);
                String isMarked = commandArr[1].substring(1, 2);
                boolean isMarkedBool = isMarked.equals("X");

                switch (firstWord) {
                    case "T": {
                        ToDo task = new ToDo(taskName, isMarkedBool, "T");
                        taskList.add(task);

                        break;
                    } case "D": {
                        String[] detailsArr = taskName.split(" \\(by: ");
                        String detail = detailsArr[1].substring(0, detailsArr[1].length() - 1);
                        String detailsFormat = parser.dateFormatHelper(detail);
                        Deadline task = new Deadline(detailsArr[0], isMarkedBool, "D", detailsFormat);
                        taskList.add(task);
                        break;
                    } case "E": {
                        String[] detailsArr = taskName.split(" \\(at: ");
                        String detail = detailsArr[1].substring(0, detailsArr[1].length() - 1);
                        String detailsFormat = parser.dateFormatHelper(detail);
                        Event task = new Event(detailsArr[0], isMarkedBool, "E", detailsFormat);
                        taskList.add(task);
                        break;
                    } default: {
                        System.out.println("Should not be in the default block for load function");
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            e.printStackTrace();
        }
    }

}
