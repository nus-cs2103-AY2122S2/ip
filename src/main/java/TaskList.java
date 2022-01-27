import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    TaskList() {
        tasks = new ArrayList<>();
    }

    public int getNoOfTasks() {
        return tasks.size();
    }

    public boolean isValidTask(int taskNo) {
        return taskNo >= 1 && taskNo <= this.getNoOfTasks();
    }

    public String listTasks() {
        if (this.getNoOfTasks() == 0) {
            return "You have no task right now!\n";
        }

        String result = "Here are the tasks you have:";
        int i = 1;
        for (Task t : tasks) {
            result += "\n" + i + "." + t.toString();
            i++;
        }
        return result;
    }

    public String markTask(int taskNo) throws PukeException {
        if (!this.isValidTask(taskNo)) {
            throw new PukeException(String.format("%d is not a valid task number!", taskNo));
        }

        Task t = tasks.get(taskNo - 1);
        if (t.isDone()) {
            throw new PukeException(String.format("Task #%d is already marked as done!", taskNo));
        }

        t.mark();
        return "Kudos! I've marked this task as done:\n  " + t;
    }

    public String unmarkTask(int taskNo) throws PukeException {
        if (!this.isValidTask(taskNo)) {
            throw new PukeException(String.format("%d is not a valid task number!", taskNo));
        }

        Task t = tasks.get(taskNo - 1);
        if (!t.isDone()) {
            throw new PukeException(String.format("Task #%d is not yet marked as done!", taskNo));
        }

        t.unmark();
        return "Alright, I've marked this task as not done yet:\n  " + t;
    }

    public String addTask(String type, String args) throws PukeException {
        if (args == null) {
            throw new PukeException("I'll need a description for the task..");
        }

        Task t;
        if (type.equals("todo")) {
            t = new Todo(args.trim());
        } else {
            String[] taskDetail = args.split("/");

            if (taskDetail.length < 2) {
                throw new PukeException("I'll need a date/time for this task..");
            }

            String dateTimeStr = taskDetail[1].split(" ", 2)[1];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            try {
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);

                if (dateTime.isBefore(LocalDateTime.now())) {
                    throw new PukeException("Why are you trying to create a task in the past?");
                }

                if (type.equals("deadline")) {
                    t = new Deadline(taskDetail[0], dateTime);
                } else {
                    t = new Event(taskDetail[0], dateTime);
                }
            } catch (DateTimeParseException e) {
                throw new PukeException("I'll need a valid date/time in the format yyyy-mm-dd hh:mm");
            }
        }

        tasks.add(t);

        return "Got it. I've added this task:\n  " + t
                + "\nNow you have " + this.getNoOfTasks()
                + (this.getNoOfTasks() <= 1 ? " task" : " tasks") + " in the list.";
    }

    public void addTaskToList(Task t) {
        this.tasks.add(t);
    }

    public String deleteTask(int taskNo) throws PukeException {
        if (!this.isValidTask(taskNo)) {
            throw new PukeException(String.format("%d is not a valid task number!", taskNo));
        }

        String taskInfo = tasks.get(taskNo - 1).toString();
        tasks.remove(taskNo - 1);
        return "There's no going back now. I've removed this task:\n  " + taskInfo
                + "\nNow you have " + this.getNoOfTasks()
                + (this.getNoOfTasks() <= 1 ? " task" : " tasks") + " in the list.";
    }

    public String generateStorageData() {
        String result = "";

        for (int i = 0; i < this.getNoOfTasks(); i++) {
            result += tasks.get(i).toSaveString();
            if (i != tasks.size() - 1) {
                result += "\n";
            }
        }

        return result;
    }
}
