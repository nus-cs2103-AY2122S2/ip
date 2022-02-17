package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Container class to hold a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Returns the list of tasks that matches the specified date. <br>
     * by performing date comparison based on the specified type.<br><br>
     *
     * comparisonType is defined as follows:
     * <li>0 : Tasks happening on the specified date. </li>
     * <li>1 : Tasks happening before the specified date.</li>
     * <li>2 : Tasks happening after the specified date.</li>
     *
     * @param date The date that is to be compared with.
     * @param comparisonType The type of comparison to be performed.
     * @return A list of tasks that matches the specified date and comparisonType.
     */
    public ArrayList<Task> getTasksBasedOnDate(LocalDate date, int comparisonType) {
        assert comparisonType > -1 && comparisonType < 3 : "comparisonType must be between 0 and 2";

        ArrayList<Predicate<Task>> taskTypePredicateList = new ArrayList<>();
        ArrayList<Predicate<Task>> taskDatePredicateList = new ArrayList<>();

        taskTypePredicateList.add(t -> t instanceof DeadlineTask);
        taskTypePredicateList.add(t -> t instanceof EventTask);

        taskDatePredicateList.add(t -> ((DeadlineTask) t).getDueDate().equals(date));
        taskDatePredicateList.add(t -> ((EventTask) t).getDate().equals(date));
        taskDatePredicateList.add(t -> ((DeadlineTask) t).getDueDate().isBefore(date));
        taskDatePredicateList.add(t -> ((EventTask) t).getDate().isBefore(date));
        taskDatePredicateList.add(t -> ((DeadlineTask) t).getDueDate().isAfter(date));
        taskDatePredicateList.add(t -> ((EventTask) t).getDate().isAfter(date));

        ArrayList<Task> result = new ArrayList<>();
        int listIndex = comparisonType * 2;
        for (Predicate<Task> p : taskTypePredicateList) {
            result.addAll(this.tasks.stream()
                    .filter(p)
                    .filter(taskDatePredicateList.get(listIndex++))
                    .collect(Collectors.toList()));
        }
        return result;
    }

    /**
     * Returns the Task that is located at the highest index.
     *
     * @return Task located at last index.
     */
    public Task getLast() {
        return tasks.get(tasks.size() - 1);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Creates a Task object as specified and adds it into the list of tasks.
     * by performing date comparison based on the specified type.<br><br>
     *
     * taskType is defined as follows:
     * <li>0 : TodoTask </li>
     * <li>1 : DeadlineTask</li>
     * <li>2 : EventTask</li>
     *
     * @param taskName The name of the Task to be added.
     * @param isMarked If the Task that is being added is marked.
     * @param date The date of the Task (null for TodoTask).
     * @param taskCode The type task to be added.
     */
    public int addTask(String taskName, boolean isMarked, LocalDate date, int taskCode) {
        assert !taskName.equals("") : "taskName must be specified";
        assert taskCode > -1 && taskCode < 3 : "taskType must be between 0 and 2";

        switch (taskCode) {
        case 0:
            this.tasks.add(new ToDoTask(taskName, isMarked));
            break;
        case 1:
            this.tasks.add(new DeadlineTask(taskName, isMarked, date));
            break;
        case 2:
            this.tasks.add(new EventTask(taskName, isMarked, date));
            break;
        default:
            break;
        }
        return 0;
    }

    /**
     * Returns a boolean value indicating if a task with the specified taskName already exists.
     * @param taskName The name of the Task to be checked
     * @return A boolean value indicating if the taskName has a duplicate.
     */
    public boolean hasDuplicate(String taskName) {
        if (this.tasks.stream().filter((t) -> t.getTaskName().equals(taskName)).count() > 0) {
            return true;
        }
        return false;
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    /**
     * Converts the Task List to a String to be used for File Saving.
     */
    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        for (Task t : this.tasks) {
            sb.append(t.toFileString());
            sb.append(";");
        }
        sb.setLength((sb.length() - 1));
        return sb.toString();
    }

    /**
     * Returns a list of tasks that contains the specified keyword.
     *
     * @param keyword The keyword to be searched.
     * @return Returns a list of task that contains the specified keyword
     */
    public ArrayList<Task> findTasksContaining(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getTaskName().contains(keyword)) {
                result.add(t);
            }
        }
        return result;
    }

    public int getSize() {
        return this.tasks.size();
    }
}
