import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> ls = new ArrayList<>();

    private void add(Task task) {
        ls.add(task);
        System.out.println("OK...");
        System.out.println("The following task has been added to the list: ");
        System.out.println(task);
        System.out.println("Now you have " + this.numOfTasks() + " tasks in the list.");
    }

    public void add(String[] str, Command command) throws EmptyDescriptionException {
        if (str.length < 2) {
            throw new EmptyDescriptionException(str[0]);
        } else {
            Task newTask;
            String[] substring;

            switch (command) {
            case TODO:
                newTask = new ToDo(str[1]);
                this.add(newTask);
                break;

            case DEADLINE:
                substring = str[1].split("/by ", 2);
                if (substring.length < 2) {
                    newTask = new Deadline(substring[0]);
                } else {
                    newTask = new Deadline(substring[0], substring[1]);
                }
                this.add(newTask);
                break;

            case EVENT:
                substring = str[1].split("/at ", 2);
                if (substring.length < 2) {
                    newTask = new Event(substring[0]);
                } else {
                    newTask = new Event(substring[0], substring[1]);
                }
                this.add(newTask);
                break;
            }
        }
    }

    public void markTask(String[] str) throws InvalidTaskNumberException {
        if (str.length < 2 || !isNumeric(str[1]) || Integer.parseInt(str[1]) > ls.size()) {
            throw new InvalidTaskNumberException(str[0]);
        }
        Task task = this.get(Integer.parseInt(str[1]));
        task.markAsDone();
    }

    public void unmarkTask(String[] str) throws InvalidTaskNumberException {
        if (str.length < 2 || !isNumeric(str[1]) || Integer.parseInt(str[1]) > ls.size()) {
            throw new InvalidTaskNumberException(str[0]);
        }
        Task task = this.get(Integer.parseInt(str[1]));
        task.markAsUndone();
    }

    private void delete(int taskNumber) {
        System.out.println("OK...");
        System.out.println("The following task has been removed from the list: ");
        System.out.println(ls.get(taskNumber - 1));
        ls.remove(taskNumber - 1);
        System.out.println("Now you have " + this.numOfTasks() + " tasks in the list.");
    }

    public void delete(String[] str) throws InvalidTaskNumberException {
        if (str.length < 2 || !isNumeric(str[1]) || Integer.parseInt(str[1]) > ls.size()) {
            throw new InvalidTaskNumberException(str[0]);
        }
        this.delete(Integer.parseInt(str[1]));
    }

    public Task get(int item) {
        return ls.get(item - 1);
    }

    public int numOfTasks() {
        return ls.size();
    }

    public void list() {
        if (numOfTasks() == 0) {
            System.out.println("There are no tasks in your list!");
        } else {
            System.out.println(this);
        }
    }

    @Override
    public String toString() {
        int counter = 1;
        String output = "Here are the tasks in your list:\n";
        for (Task task : ls) {
            output += counter + "." + task + "\n";
            counter += 1;
        }
        return output.substring(0,output.length() - 1);
    }

    private boolean isNumeric(String string) {
        int intValue;

        if(string == null || string.equals("")) {
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
