import java.util.ArrayList;
import java.util.Comparator;

public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(Ui ui) {
        tasks = new ArrayList<>();
        this.ui = ui;
    }

    public TaskList(ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void markTask(int index) {
        if (tasks.size() == 0) {
            Ui.printDivider();
            System.out.println("    No tasks are in the list right now!");
            Ui.printDivider();
        } else if (index < 1 || index > tasks.size()) {
            try {
                throw new DukeInvalidArgumentException("index is not in the list");
            } catch (DukeInvalidArgumentException e) {
                System.out.println(e.toString());
            }
        }
        tasks.get(index - 1).mark();
    }

    public void unmarkTask(int index) {
        if (tasks.size() == 0) {
            Ui.printDivider();
            System.out.println("    No tasks are in the list right now!");
            Ui.printDivider();
        } else if (index < 1 || index > tasks.size()) {
            try {
                throw new DukeInvalidArgumentException("index is not in the list");
            } catch (DukeInvalidArgumentException e) {
                System.out.println(e.toString());
            }
        }
        tasks.get(index - 1).unmark();
    }

    public void addTask(Task task) {
        // divided in case I need to add other features like parsing date
        tasks.add(task);
        ui.showUiForAdd(task, tasks.size());
    }

    public void deleteTask(int index) {
        if (tasks.size() == 0) {
            Ui.printDivider();
            System.out.println("    No tasks are in the list right now!");
            Ui.printDivider();
        } else if (index < 1 || index > tasks.size()) {
            try {
                throw new DukeInvalidArgumentException("index is not in the list");
            } catch (DukeInvalidArgumentException e) {
                System.out.println(e.toString());
            }
        } else {
            tasks.remove(index - 1);
            ui.showUiForDelete(tasks.get(index - 1), tasks.size());
        }
    }

    public enum SortType {
        DATE,
        CONTENT;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }

        public boolean equals(String input) {
            if (input.equalsIgnoreCase(this.name())) {
                return true;
            } else {
                return false;
            }
        }

    }

    public void sortTaskList(SortType sortType) {
        Comparator<Task> comparator = new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                if (sortType.equals(SortType.DATE)) {
                    return task1.compareTo(task2);
                } else if (sortType.equals(SortType.CONTENT)) {
                    return task1.content.compareTo(task2.content);
                } else {
                    return -1;
                }
            }

        };
        tasks.sort(comparator);
        ui.showUiForSort(tasks, sortType);
    }

}
