package taskservice;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

public class FileSystemTaskService implements TaskService {
    private static final String TASKS_FILENAME = "tasks";

    private final Path rootDir;
    private final Path tasksDir;
    private final ArrayList<Task> tasks;
    private boolean hasReadFromFileSystem;

    public FileSystemTaskService(String appPath) throws TaskServiceException {
        this.rootDir = Paths.get(".").resolve(appPath);
        this.tasksDir = this.rootDir.resolve(FileSystemTaskService.TASKS_FILENAME);
        this.tasks = new ArrayList<>();
        this.hasReadFromFileSystem = false;
        this.get();
    }

    @Override
    public Optional<Task> getById(int id) throws TaskServiceException {
        if (!this.isValidTaskId(id)) {
            return Optional.empty();
        }
        return Optional.of(this.tasks.get(id).clone());
    }

    @Override
    public Task[] get() throws TaskServiceException {
        if (!this.hasReadFromFileSystem) {
            BufferedReader reader = null;
            try {
                Files.createDirectories(this.rootDir);
                this.tasksDir.toFile().createNewFile();

                reader = Files.newBufferedReader(this.tasksDir);
                while (true) {
                    final String currentLine = reader.readLine();
                    if (currentLine == null) {
                        break;
                    }
                    this.tasks.add(this.parseStringToTask(currentLine));
                }
            } catch (IOException ex) {
                throw new TaskServiceException();
            } finally {
                this.closeReader(reader);
            }
            this.hasReadFromFileSystem = true;
        }

        return this.tasks.stream().map((task) -> task.clone()).toArray(Task[]::new);
    }

    @Override
    public void create(Task taskToCreate) throws TaskServiceException {
        this.tasks.add(taskToCreate.clone());
        this.saveTasksToFileSystem();
    }

    @Override
    public void update(int id, Task taskToUpdate) throws TaskServiceException {
        if (!this.isValidTaskId(id)) {
            throw new TaskServiceException();
        }
        this.tasks.set(id, taskToUpdate.clone());
        this.saveTasksToFileSystem();
    }

    @Override
    public void delete(int id) throws TaskServiceException {
        if (!this.isValidTaskId(id)) {
            throw new TaskServiceException();
        }
        this.tasks.remove(id);
        this.saveTasksToFileSystem();
    }

    private Task parseStringToTask(String str) throws TaskServiceException {
        final String[] tokens = str.split("\\|");
        if (tokens.length < 3 || (!tokens[1].trim().equals("0") && !tokens[1].trim().equals("1"))) {
            throw new TaskServiceException();
        }

        Task task;
        switch (tokens[0].trim()) {
        case "T":
            task = new Todo(tokens[2].trim());
            break;
        case "D":
            task = new Deadline(tokens[2].trim(), tokens[3].trim());
            break;
        case "E":
            task = new Event(tokens[2].trim(), tokens[3].trim());
            break;
        default:
            throw new TaskServiceException();
        }

        if (tokens[1].trim().equals("1")) {
            task.markAsDone();
        }

        return task;
    }

    private String formatTasksAsString() throws TaskServiceException {
        String formattedOutput = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            if (i > 0) {
                formattedOutput += "\n";
            }
            formattedOutput += this.formatTaskAsString(this.tasks.get(i));
        }
        return formattedOutput;
    }

    private String formatTaskAsString(Task task) throws TaskServiceException {
        final String statusAndDesc = " | " + (task.getIsDone() ? "1" : "0") + " | " + task.getDescription();
        if (task instanceof Todo) {
            return "T" + statusAndDesc;
        } else if (task instanceof Deadline) {
            final Deadline d = (Deadline) task;
            return "D" + statusAndDesc + " | " + d.getBy();
        } else if (task instanceof Event) {
            final Event e = (Event) task;
            return "E" + statusAndDesc + " | " + e.getAt();
        } else {
            throw new TaskServiceException();
        }
    }

    private void closeReader(BufferedReader reader) throws TaskServiceException {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException ex) {
                throw new TaskServiceException();
            }
        }
    }

    private void saveTasksToFileSystem() throws TaskServiceException {
        try {
            Files.write(this.tasksDir, this.formatTasksAsString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException ex) {
            throw new TaskServiceException();
        }
    }

    private boolean isValidTaskId(int id) {
        return id > -1 && id < this.tasks.size();
    }
}
