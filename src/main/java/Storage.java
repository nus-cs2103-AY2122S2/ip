import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static String filePath = "/data/taskInfo.txt";
    private static String dirPath = "/data";

    public static void initDataInfo() {
        try {

            File file = new File(System.getProperty("user.dir") + filePath);
            File dir = new File(System.getProperty("user.dir") + dirPath);

            dir.mkdir();
            file.createNewFile();

        } catch (IOException e) {
            Console.println("Something went wrong!");
        }
    }

    public static void saveTaskList(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(System.getProperty("user.dir") + filePath);

        for (int i = 0; i < taskList.size(); i++) {
            Task curTask = taskList.get(i);

            fw.write(curTask.toData());
            fw.write(System.lineSeparator());
        }

        fw.close();
    }

    public static TaskList loadTaskList(){

        initDataInfo();
        try {
            ArrayList<Task> taskArr = new ArrayList<>();
            File file = new File(System.getProperty("user.dir") + filePath);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] dataArgs = data.split("\\|");

                Task curTask = dataToTask(dataArgs);
                taskArr.add(curTask);
            }

            return new TaskList(taskArr);

        } catch (FileNotFoundException e) {

        } catch (DukeException e) {
            Console.println(e.getMessage());
        }
        return new TaskList();
    }

    private static Task dataToTask(String[] dataArgs) throws DukeException {

        try {
            int taskDone = Integer.parseInt(dataArgs[1]);
            boolean isTaskDoneValid = taskDone == 1 || taskDone == 0;
            boolean isTaskDone = taskDone == 1;

            if (!isTaskDoneValid) {
                throw new DukeException("Invalid File");
            }

            switch (dataArgs[0]) {
            case "T":
                return new Todo(dataArgs[2], isTaskDone);

            case "D":
                return new Deadline(dataArgs[2], isTaskDone, dataArgs[3]);

            case "E":
                return new Event(dataArgs[2], isTaskDone, dataArgs[3]);
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid File");
        }

        throw new DukeException("Invalid File");
    }

}
