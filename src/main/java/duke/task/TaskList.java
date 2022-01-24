package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Container class to hold a list of tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    public ArrayList<Task> getTasksBasedOnDate(LocalDate date, int type) {
        ArrayList<Predicate<Task>> taskTypePredicateList = new ArrayList<>();
        ArrayList<Predicate<Task>> taskDatePredicateList = new ArrayList<>();

        taskTypePredicateList.add(t -> t instanceof DeadlineTask);
        taskTypePredicateList.add(t -> t instanceof EventTask);

        taskDatePredicateList.add(t -> ((DeadlineTask)t).getDueDate().equals(date));
        taskDatePredicateList.add(t -> ((EventTask)t).getDate().equals(date));
        taskDatePredicateList.add(t -> ((DeadlineTask)t).getDueDate().isBefore(date));
        taskDatePredicateList.add(t -> ((EventTask)t).getDate().isBefore(date));
        taskDatePredicateList.add(t -> ((DeadlineTask)t).getDueDate().isAfter(date));
        taskDatePredicateList.add(t -> ((EventTask)t).getDate().isAfter(date));

        ArrayList<Task> result = new ArrayList<>();
        int listIndex = type * 2;
        for(Predicate<Task> p : taskTypePredicateList) {
            result.addAll(this.tasks.stream()
                    .filter(p)
                    .filter(taskDatePredicateList.get(listIndex++))
                    .collect(Collectors.toList()));
        }
        return result;
    }

    /**
     * Returns the Task that is located at the highest index
     *
     * @return Task located at last index
     */
    public Task getLast() {
        return tasks.get(tasks.size() - 1);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    public ArrayList<Task> findTasksContaining(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for(Task t : tasks) {
            if(t.getTaskName().contains(keyword)) {
                result.add(t);
            }
        }
        return result;
    }

    public int getSize() {
        return this.tasks.size();
    }
}
