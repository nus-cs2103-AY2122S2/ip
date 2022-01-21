import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ListTask {
    private List<Task> tasks;

    public ListTask() {
        this.tasks = new ArrayList<Task>();
    }

    public ListTask(List<Task> tasks) {
        this.tasks = new ArrayList<Task>(tasks);
    }

    public List<Task> getList(){
        return this.tasks;
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public int size() {
        return this.tasks.size();
    }

    public void addTaskList(List<Task> tasks){
        this.tasks.addAll(tasks);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int index) throws IndexOutOfBoundsException {
        this.tasks.remove(index - 1);
    }

    public Task get(int index) throws IndexOutOfBoundsException {
        return this.tasks.get(index-1);
    }

    public void printTaskList() {
        int i = 1;
        for (Task task : tasks) {
            System.out.println("     " + i + ".  " + task.toString());
            i++;
        }
    }

    public void printTaskList(LocalDateTime date) {
        int i = 1;
        for (Task task : tasks) {
            if(task instanceof Deadline){
                if(((Deadline) task).getDate().isBefore(date)){
                    System.out.println("     " + i + ".  " + task.toString());
                }
            }else if(task instanceof Event) {
                if (((Event) task).getDate().isBefore(date)) {
                    System.out.println("     " + i + ".  " + task.toString());
                }
            }
            i++;
        }
    }

    public String generateTaskList() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.lineSeparator());
        int i = 1;
        for (Task task : tasks) {
            stringBuilder.append("     " + i + ".  " + task.toString()+System.lineSeparator());
            i++;
        }
        return stringBuilder.toString();
    }

    public String generateTaskList(LocalDateTime date) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.lineSeparator());
        int i = 1;
        for (Task task : tasks) {
            if(task instanceof Deadline){
                if(((Deadline) task).getDate().isBefore(date)){
                    stringBuilder.append("     " + i + ".  " + task.toString()+System.lineSeparator());
                }
            }else if(task instanceof Event) {
                if (((Event) task).getDate().isBefore(date)) {
                    stringBuilder.append("     " + i + ".  " + task.toString()+System.lineSeparator());
                }
            }
            i++;
        }
        return stringBuilder.toString();
    }


}
