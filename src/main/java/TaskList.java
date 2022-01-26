import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    public int addToDo(String description) {
        this.tasks.add(new ToDo((description)));
        return tasks.size() - 1;
    }

    public int addDeadline(String description, String by) throws DukeException {
        String[] input = by.split(" ");
        if (input.length != 2) {
            throw new DukeException(Templates.invalidDateTimeFormatMsg);
        }
        LocalDate date = formatDate(input[0]);
        this.tasks.add(new Deadline(description, date, formatTime(date, input[1])));
        return tasks.size() - 1;
    }

    public int addEvent(String description, String at) throws DukeException {
        String[] input = at.split(" ");
        if (input.length != 2) {
            throw new DukeException(Templates.invalidDateTimeFormatMsg);
        }
        LocalDate date = formatDate(input[0]);
        this.tasks.add(new Event(description, date, formatTime(date, input[1])));
        return tasks.size() - 1;
    }

    private LocalDate formatDate(String sDate) throws DateTimeParseException, DukeException {
        LocalDate inputDate = LocalDate.parse(sDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        if (inputDate.isBefore(LocalDate.now())) {
            throw new DukeException(Templates.invalidDateTimeMsg);
        } else {
            return inputDate;
        }
    }

    private LocalTime formatTime(LocalDate date, String sTime) throws DateTimeParseException, DukeException {
        LocalTime inputTime = LocalTime.parse(sTime, DateTimeFormatter.ofPattern("HH:mm"));
        if (date.equals(LocalDate.now()) && inputTime.isBefore(LocalTime.now())) {
            throw new DukeException(Templates.invalidDateTimeMsg);
        } else {
            return inputTime;
        }
    }

    public int deleteTask(int task) {
        tasks.remove(task);
        return tasks.size() - 1;
    }

    public boolean isDone (int i) {
        return tasks.get(i).getIsDone();
    }

    public void completeTask(int i) {
        tasks.get(i).markAsDone();
    }

    public Task getTask(int i) {
        return this.tasks.get(i);
    }

    public int getTotalTasks() {
        return tasks.size();
    }

    public boolean isEmpty() { return tasks.size() == 0; }

    @Override
    public String toString(){
        return Templates.taskListMsg(this.tasks);
    }



}
