package duke;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> actions = new ArrayList<Task>();

    public boolean add(TaskType type, String[] inputs) throws EmptyDescriptionException {
        if (inputs[0].equals("")) {
            throw new EmptyDescriptionException("ToDo cannot be empty");
        }
//        System.out.println(inputs[0]);
//        System.out.println(inputs[1]);
//        System.out.println(inputs[2]);
        boolean isAddSuccess = false;
        switch (type) {
        case TODO :
            isAddSuccess = actions.add(new ToDo(inputs[0]));
            break;
        case DEADLINE:
            isAddSuccess = actions.add(new Deadline(inputs[0],inputs[1]));
            break;
        case EVENT:
            isAddSuccess = actions.add(new Event(inputs[0],inputs[1]));
            break;
        }

        return isAddSuccess;
    }

    public void load(TaskType type, String[] inputs) {
        Task task = null;
        switch (type) {
        case TODO :
            task = new ToDo(inputs[1]);
            break;
        case DEADLINE:
            task = new Deadline(inputs[1],inputs[2]);

            break;
        case EVENT:
            task = new Event(inputs[1],inputs[2]);
            break;
        }
        if (inputs[0].equals("1")) {
            task.markCompleted();
        } else {
            task.markIncompleted();
        }
        actions.add(task);
        return;
    }

    public void print(){
        for (int i = 0; i < actions.size(); i++) {
            System.out.print(i + 1);
            System.out.print(".");
            actions.get(i).print();
        }
    }

    public void print(int i){
        actions.get(i - 1).print();
    }

    public void markComplete(int index){
        this.actions.get(index - 1).markCompleted();
    }

    public void markIncomplete(int index){
        this.actions.get(index - 1).markIncompleted();
    }

    public int getLength() {
        return this.actions.size();
    }

    public void delete(int i) {
        actions.remove(i-1);
    }

    public Task getTask(int i) {
        return actions.get(i - 1);
    }
}
