import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TasksList {
    private List<Task> tasks;

    public TasksList() {
        this.tasks = new ArrayList<Task>();
    }

    public String addTask(List<String> instructions) throws InvalidArgumentException {
        Task newTask = Task.createTask(instructions);
        tasks.add(newTask);
        String response;
        response = String.format("Got it. I've added this task\n" + newTask + "\n" +
                "You have %d tasks in the list", this.tasks.size());
        return response;
    }

    public String deleteTask(int index) throws InvalidIndexException {
        if (index > this.tasks.size()) {
            throw new InvalidIndexException();
        }

        String response = String.format("Ok, I will remove this task: \n %s", tasks.get(index - 1));
        tasks.remove(index - 1);
        return response;
    }

    public String list() {
        StringBuilder response = new StringBuilder("");

        for (int i = 0; i < tasks.size(); i++) {
            response.append(String.format((i + 1) + ". " + tasks.get(i) + "\n"));
        }
        return response.toString();
    }

    public String mark(int index) throws InvalidIndexException {
        if (index > this.tasks.size()) {
            throw new InvalidIndexException();
        }
        String response = tasks.get(index - 1).markAsDone();
        return response;
    }

    public String unmark(int index) throws InvalidIndexException {
        if (index > this.tasks.size()) {
            throw new InvalidIndexException();
        }
        String response = tasks.get(index - 1).markAsNotDone();
        return response;
    }

    public List<String> toStorageStrings() {
        List<String> responses = new ArrayList<>();
        for (Task task : tasks) {
            responses.add(task.toStorageString() + "\n");
        }
        return responses;
    }

    public String importStorageStrings(List<String> tasksStrings) throws InvalidArgumentException{
        for (String taskString : tasksStrings) {
            List<String> description = Arrays.asList(taskString.split(" "));
            Boolean isDone = description.get(0).equals("X");
            Task task = Task.createTask(description.subList(1, description.size()));
            if (isDone) {
                task.markAsDone();
            }
            tasks.add(task);
        }
        String response;
        if (tasks.size() != 0) {
            response = "Hi, I managed to retrieve your previous data!\nHere it is: \n" + this.list();
        } else {
            response = "As a new user, try to add task and I will store them for you!";
        }
        return response;
    }
}
