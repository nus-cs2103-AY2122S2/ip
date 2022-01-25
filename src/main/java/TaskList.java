import java.io.File;
import java.io.FileNotFoundException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    TaskList() {}

    // Assumption: IstjBox.txt is new or not tampered with by a user
    // FileNotFoundException will not be thrown as we only pass an existing file to the TaskList
    TaskList(File file) throws BotException, FileNotFoundException {
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] taskInfo = line.split(" / ");

            Task taskAdded;
            // Use static variable to address indexes
            CommandEnum commandEnum = CommandEnum.stringToCommandEnum(taskInfo[0]);
            boolean isMarked = Integer.parseInt(taskInfo[1]) == 1;

            switch (commandEnum) {
            case TODO:
                taskAdded = new Todo(taskInfo[2]);
                this.tasks.add(taskAdded);
                if (isMarked) {
                    taskAdded.mark();
                }
                break;

            case DEADLINE:
                taskAdded = new Deadline(taskInfo[2], taskInfo[3]);
                this.tasks.add(taskAdded);
                if (isMarked) {
                    taskAdded.mark();
                }
                break;

            case EVENT:
                taskAdded = new Event(taskInfo[2], taskInfo[3]);
                this.tasks.add(taskAdded);
                if (isMarked) {
                    taskAdded.mark();
                }
                break;
            }
        }
    }

    public void addTask(CommandEnum commandEnum, String description, String modifierMessage) throws
            DateTimeParseException {
        switch (commandEnum) {
        case TODO:
            this.tasks.add(new Todo(description));
            break;

        case DEADLINE:
            this.tasks.add(new Deadline(description, modifierMessage));
            break;

        case EVENT:
            this.tasks.add(new Event(description, modifierMessage));
            break;
        }
    }

    public int tasksSize() {
        return this.tasks.size();
    }

    public String TaskString(int taskNumber) {
        return this.tasks.get(taskNumber - 1).toString();
    }

    public String tasksToTxtString() {
        StringBuilder str = new StringBuilder();
        for (Task task : tasks) {
            if (str.length() == 0) {
                str.append(task.toTxtString());
            } else {
                str.append("\n" + task.toTxtString());
            }
        }
        return str.toString();
    }

    public String tasksToString() {
        StringBuilder str = new StringBuilder();
        int[] count = new int[]{1};

        for (Task task : tasks) {
            if (str.length() == 0) {
                str.append(count[0] + ". " + task.toString());
            } else {
                str.append("\n" + count[0] + ". " + task.toString());
            }
            count[0]++;
        }
        return str.toString();
    }

    public void markTask(int taskNumber) {
        this.tasks.get(taskNumber - 1).mark();
    }

    public void unmarkTask(int taskNumber) {
        this.tasks.get(taskNumber - 1).unmark();
    }

    public String deletedTaskString(int taskNumber) {
        Task deletedTask = this.tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        return deletedTask.toString();
    }

    public String searchByDateString(String dateString) throws DateTimeParseException {
        LocalDate dateGiven = LocalDate.parse(dateString);
        StringBuilder searchList = new StringBuilder();
        int[] count = new int[]{1};

        for (Task task : tasks) {
            task.getDate().ifPresent(date -> {
                if (date.isEqual(dateGiven)) {
                    if (searchList.length() == 0) {
                        searchList.append(count[0] + ". " + task);
                    } else {
                        searchList.append("\n" + count[0] + ". " + task);
                    }
                    count[0]++;
                }
            });
        }
        return searchList.toString();
    }
}
