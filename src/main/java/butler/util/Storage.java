package butler.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import butler.ButlerInputException;
import butler.TaskList;
import butler.task.Deadline;
import butler.task.Event;
import butler.task.Task;
import butler.task.ToDo;

public class Storage {

    /** Default path for storage file. */
    private static final String DEFAULT_PATH_NAME = "src/main/resources/list.txt";
    /** Actual path for storage file. */
    private String pathName;

    /**
     * Constructs a Storage object and intialises actual path to given path.
     * If no path is given, actual path is default path.
     *
     * @param pathName Given path.
     */
    public Storage(String pathName) {
        assert pathName != null;
        if (pathName.equals("")) {
            this.pathName = DEFAULT_PATH_NAME;
        } else {
            this.pathName = pathName;
        }
    }

    private File createFile(String pathName) {
        File folder = new File("src/main/resources");
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        File file = new File(pathName);
        try {
            if (file.createNewFile()) {
                return file;
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        assert file != null;
        return file;
    }

    private File getFileObject() {
        File file = createFile(this.pathName);
        return file;
    }

    private void writeToFile(File file, String string) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(string);
            fileWriter.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private Stream<String> loadFromFile() {
        File file = getFileObject();
        try {
            Stream<String> lines = Files.lines(Paths.get(pathName));
            return lines;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Writes contents of taskList to the file at actual path.
     *
     * @param tasks A managed list of tasks.
     */
    public void writeTaskListToFile(TaskList tasks) {
        int i = 0;
        StringBuilder fileOutput = new StringBuilder();
        while (i < tasks.size()) {
            Task task = tasks.get(i);
            if (task instanceof ToDo) {
                ToDo toDo = (ToDo) task;
                char taskType = toDo.getTaskType().charAt(1);
                fileOutput
                        .append(taskType)
                        .append("|")
                        .append(toDo.getStatusIcon())
                        .append("|")
                        .append(toDo.getDescription())
                        .append("\n");
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                char taskType = deadline.getTaskType().charAt(1);
                fileOutput
                        .append(taskType)
                        .append("|")
                        .append(deadline.getStatusIcon())
                        .append("|")
                        .append(deadline.getDescription())
                        .append("|")
                        .append(deadline.getDateAndTime())
                        .append("\n");
            } else if (task instanceof Event) {
                Event event = (Event) task;
                char taskType = event.getTaskType().charAt(1);
                fileOutput
                        .append(taskType)
                        .append("|")
                        .append(event.getStatusIcon())
                        .append("|")
                        .append(event.getDescription())
                        .append("|")
                        .append(event.getDateAndTime())
                        .append("\n");
            }
            i++;
        }
        writeToFile(getFileObject(), fileOutput.toString());
    }

    /**
     * Loads tasks into taskList from the contents of the storage file at actual path.
     *
     * @param tasks A managed list of tasks.
     */
    public void loadTaskListFromFile(TaskList tasks) {
        Stream<String> stream = loadFromFile();
        if (stream == null) {
            return;
        }
        stream.forEach(line -> {
            String[] taskInfo = line.split("\\|");
            String type;
            String markedStatus;
            String description;
            boolean marked = false;
            type = taskInfo[0];
            markedStatus = taskInfo[1];
            description = taskInfo[2];
            if (markedStatus.equals("X")) {
                marked = true;
            }
            switch (type) {
            case "T":
                loadToDo(tasks, marked, description);
                break;
            case "D":
                String deadlineDateAndTime = taskInfo[3];
                loadDeadline(tasks, marked, description, deadlineDateAndTime);
                break;
            case "E":
                String eventDateAndTime = taskInfo[3];
                loadEvent(tasks, marked, description, eventDateAndTime);
                break;
            default:
                break;
            }
        });
    }

    private void loadToDo(TaskList tasks, boolean marked, String description) {
        try {
            tasks.addToDoToList(description);
            if (marked) {
                tasks.markAsDone(tasks.size() - 1);
            }
        } catch (ButlerInputException exception) {
            exception.printStackTrace();
        }
    }

    private void loadDeadline(TaskList tasks, boolean marked, String description, String dateAndTime) {
        try {
            tasks.addDeadlineToList(description, dateAndTime);
            if (marked) {
                tasks.markAsDone(tasks.size() - 1);
            }
        } catch (ButlerInputException exception) {
            exception.printStackTrace();
        }
    }

    private void loadEvent(TaskList tasks, boolean marked, String description, String dateAndTime) {
        try {
            tasks.addEventToList(description, dateAndTime);
            if (marked) {
                tasks.markAsDone(tasks.size() - 1);
            }
        } catch (ButlerInputException exception) {
            exception.printStackTrace();
        }
    }
}
